package acceptance

import contract.EAUToken
import contract.CLGNToken
import contract.UserToken
import contract.Vault
import helpers.ContractTestHelper
import helpers.VaultState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.web3j.crypto.Credentials
import org.web3j.protocol.exceptions.TransactionException
import java.math.BigInteger
import java.nio.file.Path

@Testcontainers
class ClgnStakeSlashingAcceptanceTest {

    @Container
    private val ganache: GenericContainer<Nothing> =
        GenericContainer<Nothing>(
            ImageFromDockerfile()
                .withDockerfile(Path.of(javaClass.getResource("/docker/ganache/Dockerfile").toURI()))
        )
            .withExposedPorts(8545)

    val initialAmount = BigInteger.valueOf(1000)
    val tokenPrice = BigInteger.valueOf(2)
    val initialStake = BigInteger.valueOf(100)
    lateinit var helper: ContractTestHelper
    lateinit var owner: Credentials
    lateinit var auctionInitiator: Credentials
    lateinit var slashingIntiator: Credentials
    lateinit var eauToken: EAUToken
    lateinit var clgnToken: CLGNToken
    lateinit var ownerVault: Vault
    lateinit var auctionIntiatorVault: Vault
    lateinit var slashingInitiatorVault: Vault

    @BeforeEach
    fun setUp() {
        helper = ContractTestHelper(ganache.host, ganache.firstMappedPort)
        owner = helper.credentialsAlice
        auctionInitiator = helper.credentialsBob
        slashingIntiator = helper.credentialsCharlie
        eauToken = helper.eauToken
        clgnToken = helper.clgnToken
    }

    /**
     * Deploy vault with Owner credentials
     */
    fun ownerCreatesVault(
        amount: BigInteger = initialAmount,
        price: BigInteger = tokenPrice,
        stake: BigInteger = initialStake
    ) {
        val vaultAddress = helper.createVault(owner, amount, price)
        ownerVault = Vault.load(vaultAddress, helper.web3, owner, helper.gasProvider)
        auctionIntiatorVault = Vault.load(ownerVault.contractAddress, helper.web3, auctionInitiator, helper.gasProvider)
        slashingInitiatorVault =
            Vault.load(ownerVault.contractAddress, helper.web3, slashingIntiator, helper.gasProvider)

        // Stake
        helper.addCLGN(owner.address, stake)
        val clgnTokenByOwner = UserToken.load(clgnToken.contractAddress, helper.web3, owner, helper.gasProvider)
        clgnTokenByOwner.approve(ownerVault.contractAddress, stake).send()
        ownerVault.stake(stake).send()
    }

    /**
     * Breach credit limit
     */
    fun breachVault() {
        helper.breachVault()
    }

    /**
     * Fail initial liquidity auction
     */
    fun failInitialAuction() {
        // Dutch auction has passed
        val time = helper.timeProvider.time.send().add(BigInteger.valueOf(180000))
        helper.timeProvider.setTime(time).send()
    }

    /**
     * @given the vault is closed
     * @when call slash
     * @then error returned - closed
     */
    @Test
    fun slashClosed() {
        ownerCreatesVault()
        ownerVault.close().send()

        assertThrows<TransactionException> {
            slashingInitiatorVault.slash().send()
        }
    }

    /**
     * @given the vault is open and has no debt
     * @when call slash
     * @then error returned - no debt
     */
    @Test
    fun slashNoBreach() {
        ownerCreatesVault()

        assertThrows<TransactionException> {
            slashingInitiatorVault.slash().send()
        }
    }

    /**
     * @given the vault is open and initial liquidity auction is in process
     * @when call slash
     * @then error returned - no debt
     */
    @Test
    fun slashAuctionNotOver() {
        ownerCreatesVault()
        breachVault()

        assertThrows<TransactionException> {
            slashingInitiatorVault.slash().send()
        }
    }

    /**
     * @given the vault is breached and initial liquidity auction is over and stake is 1000 CLGN
     * @when stake is slashed
     * @then penalty is distributed:
     *  2,5% of stake (25 CLGN) goes to Initial Liquidity Auction initiator
     *  2,5% of stake (25 CLGN) goes to slashing initiator
     *  5% of stake (50 CLGN) is burned
     */
    @Test
    fun slashBountyDistribution() {
        helper.addEAU(helper.marketAdaptor.contractAddress, BigInteger.valueOf(2000))
        ownerCreatesVault(amount = BigInteger.valueOf(16000), stake = BigInteger.valueOf(1000))
        val initialClgnSupply = clgnToken.totalSupply().send()
        breachVault()
        auctionIntiatorVault.startInitialLiquidityAuction().send()
        failInitialAuction()
        assertEquals(VaultState.WaitingForSlashing.toBigInteger(), ownerVault.state.send())

        slashingInitiatorVault.slash().send()

        assertEquals(BigInteger.valueOf(25), clgnToken.balanceOf(auctionInitiator.address).send())
        assertEquals(BigInteger.valueOf(25), clgnToken.balanceOf(slashingIntiator.address).send())
        assertEquals(initialClgnSupply.subtract(BigInteger.valueOf(50)), clgnToken.totalSupply().send())
    }

    /**
     * @given the vault has principal debt of 4000 EAU and some fee accrued and stake of 1000 CLGN (assessed as 2000
     * EAU) and initial liquidity auction failed
     * @when slashing called
     * @then principal is paid off partially (1800 EAU paid off and 2200 is current debt) and fees are forgiven and
     * penalty is distributed and stake is slashed
     */
    @Test
    fun slashPrincipalPartiallyCovered() {
        helper.addEAU(helper.marketAdaptor.contractAddress, BigInteger.valueOf(2000))
        ownerCreatesVault(amount = BigInteger.valueOf(16000), price = BigInteger.ONE, stake = BigInteger.valueOf(1000))
        breachVault()
        // wait 5 days for fees and ensure fees accrued
        helper.passTime(BigInteger.valueOf(5 * 24 * 3600))
        assertNotEquals(BigInteger.ZERO, ownerVault.fees.send())
        // start and fail Initial Liquidity Auction
        auctionIntiatorVault.startInitialLiquidityAuction().send()
        failInitialAuction()

        slashingInitiatorVault.slash().send()

        assertEquals(BigInteger.valueOf(2200), ownerVault.totalDebt.send())
        assertEquals(BigInteger.valueOf(2200), ownerVault.principal.send())
        assertEquals(BigInteger.ZERO, ownerVault.fees.send())
        assertEquals(BigInteger.ZERO, ownerVault.collateralInEau.send())
    }

    /**
     * @given the vault has principal debt of 10000 EAU and fees accrued more then 2 EAU and stake of 5600 CLGN
     * (assessed as 11200 EAU) and initial liquidity auction failed
     * @when slashing called
     * @then principal is paid off (10000 EAU) and penalty paid off (10000 EAU) and fees paid off partially (100 EAU) and
     * the rest of fees are forgiven
     */
    @Test
    fun slashPrincipalsCoveredAndFeesPatiallyCovered() {
        helper.addEAU(helper.marketAdaptor.contractAddress, BigInteger.valueOf(20000))
        ownerCreatesVault(amount = BigInteger.valueOf(40000), price = BigInteger.ONE, stake = BigInteger.valueOf(5550))
        breachVault()
        // ensure fees are not covered by stake
        helper.passTime(BigInteger.valueOf(100 * 24 * 3600))
        assertTrue(ownerVault.fees.send() > BigInteger.TWO)
        // start and fail Initial Liquidity Auction
        auctionIntiatorVault.startInitialLiquidityAuction().send()
        failInitialAuction()

        slashingInitiatorVault.slash().send()

        assertEquals(BigInteger.ZERO, ownerVault.fees.send())
        assertEquals(BigInteger.ZERO, ownerVault.totalDebt.send())
        assertEquals(BigInteger.ZERO, ownerVault.collateralInEau.send())
    }

    /**
     * @given the vault has principal debt of 10000 EAU and fees accrued are 1000 EAU and stake of 10000 CLGN
     * (assessed as 20000 EAU) and initial liquidity auction failed
     * @when slashing called
     * @then principal is paid off (10000 EAU) and penalty paid off (1010 EAU) and fees paid off (100 EAU) and stake
     * leftover in EAU is 4445 CLGN (8890 EAU
     */
    @Test
    fun slashCovered() {
        helper.addEAU(helper.marketAdaptor.contractAddress, BigInteger.valueOf(20000))
        ownerCreatesVault(amount = BigInteger.valueOf(40000), price = BigInteger.ONE, stake = BigInteger.valueOf(10000))
        breachVault()
        // ensure fees are not covered by stake
        helper.passTime(BigInteger.valueOf(50 * 24 * 3600))
        assertEquals(BigInteger.valueOf(100), ownerVault.fees.send())
        // start and fail Initial Liquidity Auction
        auctionIntiatorVault.startInitialLiquidityAuction().send()
        failInitialAuction()

        slashingInitiatorVault.slash().send()

        assertEquals(BigInteger.ZERO, ownerVault.fees.send())
        assertEquals(BigInteger.ZERO, ownerVault.totalDebt.send())
        assertEquals(BigInteger.valueOf(8890), ownerVault.collateralInEau.send())
    }
}
