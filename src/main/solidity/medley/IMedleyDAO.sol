// SPDX-License-Identifier: MIT

pragma solidity ^0.7.0;

import "./../market/IPriceOracle.sol";
import "./../market/IMarketAdaptor.sol";

interface IMedleyDAO {
    /**
     * Create vault and transfer tokens.
     * Transfer should be allowed to MedleyDAO contract
     * @param token - user token address
     * @param stake - amount of CLGN token to stake
     * @param initialAmount - user token amount
     * @param tokenPrice - price of one user token in EAU
     */
    function createVault(address token, uint stake, uint initialAmount, uint tokenPrice) external returns (address);

    function mintEAU(address beneficiary, uint amount) external;

    function mintCLGN(address beneficiary, uint amount) external;

    function getClgnTokenAddress() external view returns (address);

    function getEauTokenAddress() external view returns (address);

    function listVaults() external view returns (address [] memory);

    function getClgnPriceOracle() external view returns (IPriceOracle);

    function getClgnMarket() external view returns (IMarketAdaptor);

    event VaultCreation(address indexed vault, address indexed owner);
}
