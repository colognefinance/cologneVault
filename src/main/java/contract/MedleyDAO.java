package contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class MedleyDAO extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610df4380380610df48339818101604052606081101561003357600080fd5b5080516020820151604090920151600280546001600160a01b03199081166001600160a01b039485161790915560038054821694841694909417909355600480549093169116179055610d698061008b6000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c806350cc258e1461006757806350d23c70146100bf57806377701eb014610113578063c698a1da1461011b578063c8593d1214610123578063f97c6e111461012b575b600080fd5b61006f610159565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156100ab578181015183820152602001610093565b505050509050019250505060405180910390f35b6100f7600480360360808110156100d557600080fd5b506001600160a01b0381351690602081013590604081013590606001356101bb565b604080516001600160a01b039092168252519081900360200190f35b6100f7610441565b6100f7610450565b6100f761045f565b6101576004803603604081101561014157600080fd5b506001600160a01b03813516906020013561046e565b005b606060008054806020026020016040519081016040528092919081815260200182805480156101b157602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610193575b5050505050905090565b60008033858786866040516101cf90610543565b80866001600160a01b03168152602001858152602001846001600160a01b0316815260200183815260200182815260200195505050505050604051809103906000f080158015610223573d6000803e3d6000fd5b50604080516323b872dd60e01b81523360048201526001600160a01b0380841660248301526044820188905291519293508892918316916323b872dd916064808201926020929091908290030181600087803b15801561028257600080fd5b505af1158015610296573d6000803e3d6000fd5b505050506040513d60208110156102ac57600080fd5b50516102e95760405162461bcd60e51b815260040180806020018281038252602e815260200180610d06602e913960400191505060405180910390fd5b600254604080516323b872dd60e01b81523360048201526001600160a01b038581166024830152604482018a9052915191909216916323b872dd9160648083019260209291908290030181600087803b15801561034557600080fd5b505af1158015610359573d6000803e3d6000fd5b505050506040513d602081101561036f57600080fd5b50516103ac5760405162461bcd60e51b815260040180806020018281038252602e815260200180610cd8602e913960400191505060405180910390fd5b60008054600180820183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e56390910180546001600160a01b0319166001600160a01b03861690811790915580835260208290526040808420805460ff1916909317909255905133927f9990404fa50cbe447253514fc4ab52bb578ae0b3514a50714660a1e801a2f6d391a35095945050505050565b6003546001600160a01b031690565b6004546001600160a01b031690565b6002546001600160a01b031690565b3360009081526001602052604090205460ff166104d2576040805162461bcd60e51b815260206004820152601760248201527f4f6e6c79207661756c742063616e206d696e7420454155000000000000000000604482015290519081900360640190fd5b600354604080516340c10f1960e01b81526001600160a01b03858116600483015260248201859052915191909216916340c10f1991604480830192600092919082900301818387803b15801561052757600080fd5b505af115801561053b573d6000803e3d6000fd5b505050505050565b610787806105518339019056fe608060405260006005556000600755600060085534801561001f57600080fd5b50604051610787380380610787833981810160405260a081101561004257600080fd5b50805160208201516040830151606084015160809094015160018054336001600160a01b0319918216179091556002805482166001600160a01b03808816919091179091556003805490921690841617905560048590556005819055929391929091906100ae846100b8565b5050505050610228565b600160009054906101000a90046001600160a01b03166001600160a01b031663c698a1da6040518163ffffffff1660e01b815260040160206040518083038186803b15801561010657600080fd5b505afa15801561011a573d6000803e3d6000fd5b505050506040513d602081101561013057600080fd5b50516001546040805163642c9e8960e11b815290516001600160a01b0393841693633ddac95393169163c8593d12916004808301926020929190829003018186803b15801561017e57600080fd5b505afa158015610192573d6000803e3d6000fd5b505050506040513d60208110156101a857600080fd5b5051604080516001600160e01b031960e085901b1681526001600160a01b03909216600483015260248201859052516044808301926020929190829003018186803b1580156101f657600080fd5b505afa15801561020a573d6000803e3d6000fd5b505050506040513d602081101561022057600080fd5b505160065550565b610550806102376000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80638119c0651161005b5780638119c0651461008d57806398d5fdca146100b4578063c5ebeaec146100ce578063f78be85a146100eb57610088565b80631865c57d1461008d578063243582ff146100975780632da25de31461008d57806343d726d61461008d575b600080fd5b6100956100f3565b005b610095600480360360208110156100ad57600080fd5b50356100f5565b6100bc6100f8565b60408051918252519081900360200190f35b610095600480360360208110156100e457600080fd5b50356100ff565b6100bc61023e565b565b50565b6005545b90565b6002546001600160a01b03163314610156576040805162461bcd60e51b81526020600482015260156024820152744f6e6c79206f776e65722063616e20626f72726f7760581b604482015290519081900360640190fd5b61015e61023e565b8111156101b2576040805162461bcd60e51b815260206004820152601a60248201527f437265646974206c696d69742069732065786861757374656420000000000000604482015290519081900360640190fd5b6001546002546040805163f97c6e1160e01b81526001600160a01b039283166004820152602481018590529051919092169163f97c6e1191604480830192600092919082900301818387803b15801561020a57600080fd5b505af115801561021e573d6000803e3d6000fd5b5050600780548401905550504260098190556100f5906002908390610297565b60008061024a4261029c565b90506000610270600461026a6005546004546102b990919063ffffffff16565b90610319565b9050818111610284576000925050506100fc565b61028e818361035b565b925050506100fc565b505050565b60006102b36102aa8361039d565b600754906103a3565b92915050565b6000826102c8575060006102b3565b828202828482816102d557fe5b04146103125760405162461bcd60e51b81526004018080602001828103825260218152602001806104fa6021913960400191505060405180910390fd5b9392505050565b600061031283836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f0000000000008152506103fd565b600061031283836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f77000081525061049f565b50600090565b600082820183811015610312576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b600081836104895760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561044e578181015183820152602001610436565b50505050905090810190601f16801561047b5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b50600083858161049557fe5b0495945050505050565b600081848411156104f15760405162461bcd60e51b815260206004820181815283516024840152835190928392604490910191908501908083836000831561044e578181015183820152602001610436565b50505090039056fe536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f77a26469706673582212201029230167ace3ad9f11d4454d7531049f4f877b0c9d8d8d9c62f178ed9389a764736f6c634300070000334d65646c657944414f3a205472616e73666572206f66204d444c5920746f6b656e73206e6f7420616c6c6f7765644d65646c657944414f3a205472616e73666572206f66207573657220746f6b656e73206e6f7420616c6c6f776564a264697066735822122068cb442f04c1093ba667723d6234d555db147a966feb8303196c9412f1e7ce0f64736f6c63430007000033";

    public static final String FUNC_CREATEVAULT = "createVault";

    public static final String FUNC_GETEAUTOKENADDRESS = "getEauTokenAddress";

    public static final String FUNC_GETMDLYPRICEORACLE = "getMdlyPriceOracle";

    public static final String FUNC_GETMDLYTOKENADDRESS = "getMdlyTokenAddress";

    public static final String FUNC_LISTVAULTS = "listVaults";

    public static final String FUNC_MINTEAU = "mintEAU";

    public static final Event VAULTCREATION_EVENT = new Event("VaultCreation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected MedleyDAO(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MedleyDAO(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MedleyDAO(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MedleyDAO(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<VaultCreationEventResponse> getVaultCreationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VAULTCREATION_EVENT, transactionReceipt);
        ArrayList<VaultCreationEventResponse> responses = new ArrayList<VaultCreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VaultCreationEventResponse typedResponse = new VaultCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.vault = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VaultCreationEventResponse> vaultCreationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VaultCreationEventResponse>() {
            @Override
            public VaultCreationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VAULTCREATION_EVENT, log);
                VaultCreationEventResponse typedResponse = new VaultCreationEventResponse();
                typedResponse.log = log;
                typedResponse.vault = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.owner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VaultCreationEventResponse> vaultCreationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VAULTCREATION_EVENT));
        return vaultCreationEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createVault(String token, BigInteger stake, BigInteger initialAmount, BigInteger tokenPrice) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEVAULT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.generated.Uint256(stake), 
                new org.web3j.abi.datatypes.generated.Uint256(initialAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenPrice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getEauTokenAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEAUTOKENADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getMdlyPriceOracle() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMDLYPRICEORACLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getMdlyTokenAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMDLYTOKENADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> listVaults() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LISTVAULTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> mintEAU(String beneficiary, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINTEAU, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiary), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static MedleyDAO load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedleyDAO(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MedleyDAO load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedleyDAO(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MedleyDAO load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MedleyDAO(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MedleyDAO load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MedleyDAO(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String mdlyToken, String eauToken, String mdlyPriceOracle) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle)));
        return deployRemoteCall(MedleyDAO.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String mdlyToken, String eauToken, String mdlyPriceOracle) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle)));
        return deployRemoteCall(MedleyDAO.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String mdlyToken, String eauToken, String mdlyPriceOracle) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle)));
        return deployRemoteCall(MedleyDAO.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String mdlyToken, String eauToken, String mdlyPriceOracle) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle)));
        return deployRemoteCall(MedleyDAO.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class VaultCreationEventResponse extends BaseEventResponse {
        public String vault;

        public String owner;
    }
}
