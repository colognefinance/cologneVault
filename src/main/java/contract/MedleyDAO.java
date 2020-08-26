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
    public static final String BINARY = "608060405234801561001057600080fd5b50604051611fb7380380611fb78339818101604052608081101561003357600080fd5b50805160208201516040830151606090930151600280546001600160a01b03199081166001600160a01b039586161790915560038054821693851693909317909255600480548316948416949094179093556005805490911691909216179055611f15806100a26000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063c698a1da1161005b578063c698a1da14610136578063c8593d121461013e578063cd26be1c14610146578063f97c6e111461014e5761007d565b806350cc258e1461008257806350d23c70146100da57806377701eb01461012e575b600080fd5b61008a61017c565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156100c65781810151838201526020016100ae565b505050509050019250505060405180910390f35b610112600480360360808110156100f057600080fd5b506001600160a01b0381351690602081013590604081013590606001356101de565b604080516001600160a01b039092168252519081900360200190f35b610112610464565b610112610473565b610112610482565b610112610491565b61017a6004803603604081101561016457600080fd5b506001600160a01b0381351690602001356104a0565b005b606060008054806020026020016040519081016040528092919081815260200182805480156101d457602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116101b6575b5050505050905090565b60008033858786866040516101f290610575565b80866001600160a01b03168152602001858152602001846001600160a01b0316815260200183815260200182815260200195505050505050604051809103906000f080158015610246573d6000803e3d6000fd5b50604080516323b872dd60e01b81523360048201526001600160a01b0380841660248301526044820188905291519293508892918316916323b872dd916064808201926020929091908290030181600087803b1580156102a557600080fd5b505af11580156102b9573d6000803e3d6000fd5b505050506040513d60208110156102cf57600080fd5b505161030c5760405162461bcd60e51b815260040180806020018281038252602e815260200180611eb2602e913960400191505060405180910390fd5b600254604080516323b872dd60e01b81523360048201526001600160a01b038581166024830152604482018a9052915191909216916323b872dd9160648083019260209291908290030181600087803b15801561036857600080fd5b505af115801561037c573d6000803e3d6000fd5b505050506040513d602081101561039257600080fd5b50516103cf5760405162461bcd60e51b815260040180806020018281038252602e815260200180611e84602e913960400191505060405180910390fd5b60008054600180820183557f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e56390910180546001600160a01b0319166001600160a01b03861690811790915580835260208290526040808420805460ff1916909317909255905133927f9990404fa50cbe447253514fc4ab52bb578ae0b3514a50714660a1e801a2f6d391a35095945050505050565b6003546001600160a01b031690565b6004546001600160a01b031690565b6002546001600160a01b031690565b6005546001600160a01b031690565b3360009081526001602052604090205460ff16610504576040805162461bcd60e51b815260206004820152601760248201527f4f6e6c79207661756c742063616e206d696e7420454155000000000000000000604482015290519081900360640190fd5b600354604080516340c10f1960e01b81526001600160a01b03858116600483015260248201859052915191909216916340c10f1991604480830192600092919082900301818387803b15801561055957600080fd5b505af115801561056d573d6000803e3d6000fd5b505050505050565b611901806105838339019056fe6080604052600060078190556009819055600a55600c805460ff191690553480156200002a57600080fd5b506040516200190138038062001901833981810160405260a08110156200005057600080fd5b5080516020808301516040808501516060860151608090960151600180546001600160a01b0319908116331791829055600280546001600160a01b03808b1691909316179055845163077701eb60e41b8152945197989597939692949116926377701eb092600480840193919291829003018186803b158015620000d357600080fd5b505afa158015620000e8573d6000803e3d6000fd5b505050506040513d6020811015620000ff57600080fd5b5051600380546001600160a01b0319166001600160a01b039283161790556001546040805163642c9e8960e11b81529051919092169163c8593d12916004808301926020929190829003018186803b1580156200015b57600080fd5b505afa15801562000170573d6000803e3d6000fd5b505050506040513d60208110156200018757600080fd5b5051600480546001600160a01b03199081166001600160a01b03938416179091556005805490911691851691909117905560068290556007819055620001cd84620001d8565b505050505062000351565b600160009054906101000a90046001600160a01b03166001600160a01b031663c698a1da6040518163ffffffff1660e01b815260040160206040518083038186803b1580156200022757600080fd5b505afa1580156200023c573d6000803e3d6000fd5b505050506040513d60208110156200025357600080fd5b50516001546040805163642c9e8960e11b815290516001600160a01b0393841693633ddac95393169163c8593d12916004808301926020929190829003018186803b158015620002a257600080fd5b505afa158015620002b7573d6000803e3d6000fd5b505050506040513d6020811015620002ce57600080fd5b5051604080516001600160e01b031960e085901b1681526001600160a01b03909216600483015260248201859052516044808301926020929190829003018186803b1580156200031d57600080fd5b505afa15801562000332573d6000803e3d6000fd5b505050506040513d60208110156200034957600080fd5b505160085550565b6115a080620003616000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c806359a87bc11161006657806359a87bc11461010957806398d5fdca1461013b578063a69bdf1614610143578063c5ebeaec1461014b578063f78be85a146101685761009e565b80631865c57d146100a3578063243582ff146100ad5780632da25de3146100ca57806343d726d6146100d2578063486501c5146100da575b600080fd5b6100ab610170565b005b6100ab600480360360208110156100c357600080fd5b5035610172565b6100ab610346565b6100ab610390565b6100f7600480360360208110156100f057600080fd5b50356106fc565b60408051918252519081900360200190f35b6100ab6004803603606081101561011f57600080fd5b50803590602081013590604001356001600160a01b0316610766565b6100f76109ac565b6100f76109b3565b6100ab6004803603602081101561016157600080fd5b5035610a07565b6100f7610b93565b565b600c5460ff16156101bc576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b600354604080516323b872dd60e01b81523360048201523060248201526044810184905290516001600160a01b03909216916323b872dd916064808201926020929091908290030181600087803b15801561021657600080fd5b505af115801561022a573d6000803e3d6000fd5b505050506040513d602081101561024057600080fd5b5051610293576040805162461bcd60e51b815260206004820152601b60248201527f5661756c743a2063616e6e6f74207472616e73666572204541552e0000000000604482015290519081900360640190fd5b61029f60018242610c38565b42600b5560006102ae82610c3d565b905060006009548211156102d0575060098054600090915590819003906102dc565b50600980548290039055805b60035460408051630852cd8d60e31b81526004810184905290516001600160a01b03909216916342966c689160248082019260009290919082900301818387803b15801561032957600080fd5b505af115801561033d573d6000803e3d6000fd5b50505050505050565b600c5460ff1615610170576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b6002546001600160a01b031633146103a757600080fd5b6103b0426106fc565b156103ec5760405162461bcd60e51b81526004018080602001828103825260378152602001806114816037913960400191505060405180910390fd5b600554600254604080516370a0823160e01b815230600482015290516001600160a01b039384169363a9059cbb93169184916370a0823191602480820192602092909190829003018186803b15801561044457600080fd5b505afa158015610458573d6000803e3d6000fd5b505050506040513d602081101561046e57600080fd5b5051604080516001600160e01b031960e086901b1681526001600160a01b03909316600484015260248301919091525160448083019260209291908290030181600087803b1580156104bf57600080fd5b505af11580156104d3573d6000803e3d6000fd5b505050506040513d60208110156104e957600080fd5b505060048054600254604080516370a0823160e01b81523094810194909452516001600160a01b039283169363a9059cbb939092169184916370a0823191602480820192602092909190829003018186803b15801561054757600080fd5b505afa15801561055b573d6000803e3d6000fd5b505050506040513d602081101561057157600080fd5b5051604080516001600160e01b031960e086901b1681526001600160a01b03909316600484015260248301919091525160448083019260209291908290030181600087803b1580156105c257600080fd5b505af11580156105d6573d6000803e3d6000fd5b505050506040513d60208110156105ec57600080fd5b5050600354600254604080516370a0823160e01b815230600482015290516001600160a01b039384169363a9059cbb93169184916370a0823191602480820192602092909190829003018186803b15801561064657600080fd5b505afa15801561065a573d6000803e3d6000fd5b505050506040513d602081101561067057600080fd5b5051604080516001600160e01b031960e086901b1681526001600160a01b03909316600484015260248301919091525160448083019260209291908290030181600087803b1580156106c157600080fd5b505af11580156106d5573d6000803e3d6000fd5b505050506040513d60208110156106eb57600080fd5b5050600c805460ff19166001179055565b600c5460009060ff1615610749576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b61075e61075583611188565b60095490611244565b90505b919050565b600c5460ff16156107b0576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b600554604080516370a0823160e01b815230600482015290516001600160a01b03909216916370a0823191602480820192602092909190829003018186803b1580156107fb57600080fd5b505afa15801561080f573d6000803e3d6000fd5b505050506040513d602081101561082557600080fd5b50518311156108655760405162461bcd60e51b81526004018080602001828103825260278152602001806115446027913960400191505060405180910390fd5b600061086f6109ac565b9050828111156108c6576040805162461bcd60e51b815260206004820152601b60248201527f5661756c743a3a62757928293a20507269636520746f6f206c6f770000000000604482015290519081900360640190fd5b8381026108d281610172565b6005546040805163a9059cbb60e01b81526001600160a01b038681166004830152602482018990529151919092169163a9059cbb9160448083019260209291908290030181600087803b15801561092857600080fd5b505af115801561093c573d6000803e3d6000fd5b505050506040513d602081101561095257600080fd5b50516109a5576040805162461bcd60e51b815260206004820181905260248201527f5661756c743a3a6275793a2063616e6e6f74207472616e73666572204541552e604482015290519081900360640190fd5b5050505050565b6007545b90565b600c5460009060ff1615610a00576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b5060095490565b600c5460ff1615610a51576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b6002546001600160a01b03163314610aa8576040805162461bcd60e51b81526020600482015260156024820152744f6e6c79206f776e65722063616e20626f72726f7760581b604482015290519081900360640190fd5b610ab0610b93565b811115610b04576040805162461bcd60e51b815260206004820152601a60248201527f437265646974206c696d69742069732065786861757374656420000000000000604482015290519081900360640190fd5b6001546002546040805163f97c6e1160e01b81526001600160a01b039283166004820152602481018590529051919092169163f97c6e1191604480830192600092919082900301818387803b158015610b5c57600080fd5b505af1158015610b70573d6000803e3d6000fd5b50506009805484019055505042600b819055610b90906002908390610c38565b50565b600c5460009060ff1615610be0576040805162461bcd60e51b815260206004820152600f60248201526e15985d5b1d081a5cc818db1bdcd959608a1b604482015290519081900360640190fd5b6000610beb426106fc565b90506000610c116004610c0b6007546006546112a790919063ffffffff16565b90611300565b9050818111610c25576000925050506109b0565b610c2f8183611342565b925050506109b0565b505050565b600080610c4942611188565b9050600083925081831115610c6957506000600a55908190039080610c74565b50818103600a556000915b6000610c81826002611300565b90506000610d8f600a610c0b6009600160009054906101000a90046001600160a01b03166001600160a01b031663c698a1da6040518163ffffffff1660e01b815260040160206040518083038186803b158015610cdd57600080fd5b505afa158015610cf1573d6000803e3d6000fd5b505050506040513d6020811015610d0757600080fd5b505160035460408051633ddac95360e01b81526001600160a01b039283166004820152602481018a905290519190921691633ddac953916044808301926020929190829003018186803b158015610d5d57600080fd5b505afa158015610d71573d6000803e3d6000fd5b505050506040513d6020811015610d8757600080fd5b5051906112a7565b604080516002808252606080830184529394509091602083019080368337505060035482519293506001600160a01b031691839150600090610dcd57fe5b6001600160a01b039283166020918202929092010152600454825191169082906001908110610df857fe5b6001600160a01b0392831660209182029290920181019190915260015460408051633349af8760e21b8152905161271042019460609493169263cd26be1c9260048082019391829003018186803b158015610e5257600080fd5b505afa158015610e66573d6000803e3d6000fd5b505050506040513d6020811015610e7c57600080fd5b50516040516338ed173960e01b8152600481018781526024820187905230606483018190526084830186905260a060448401908152875160a485015287516001600160a01b03909516946338ed1739948b948b948b9490938b9360c401906020878101910280838360005b83811015610eff578181015183820152602001610ee7565b505050509050019650505050505050600060405180830381600087803b158015610f2857600080fd5b505af1158015610f3c573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610f6557600080fd5b8101908080516040519392919084640100000000821115610f8557600080fd5b908301906020820185811115610f9a57600080fd5b8251866020820283011164010000000082111715610fb757600080fd5b82525081516020918201928201910280838360005b83811015610fe4578181015183820152602001610fcc565b50505050905001604052505050905060008160018151811061100257fe5b602002602001015190508160008151811061101957fe5b6020026020010151861461105e5760405162461bcd60e51b815260040180806020018281038252603981526020018061150b6039913960400191505060405180910390fd5b8085101561109d5760405162461bcd60e51b81526004018080602001828103825260328152602001806114d96032913960400191505060405180910390fd5b6004805460408051630852cd8d60e31b8152928301849052516001600160a01b03909116916342966c6891602480830192600092919082900301818387803b1580156110e857600080fd5b505af11580156110fc573d6000803e3d6000fd5b5050600354604080516391c05b0b60e01b81528a8c03600482015290516001600160a01b0390921693506391c05b0b92506024808201926020929091908290030181600087803b15801561114f57600080fd5b505af1158015611163573d6000803e3d6000fd5b505050506040513d602081101561117957600080fd5b50505050505050505050919050565b6000600b546000141561119d57506000610761565b600b548210156111f4576040805162461bcd60e51b815260206004820181905260248201527f43616e6e6f742063616c63756c6174652066656520696e207468652070617374604482015290519081900360640190fd5b620151806000611209620186a061016d611300565b600a54600b54919250905b8581101561123b57620f4240838360095401028161122e57fe5b0491909101908301611214565b50949350505050565b60008282018381101561129e576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b90505b92915050565b6000826112b6575060006112a1565b828202828482816112c357fe5b041461129e5760405162461bcd60e51b81526004018080602001828103825260218152602001806114b86021913960400191505060405180910390fd5b600061129e83836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250611384565b600061129e83836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250611426565b600081836114105760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b838110156113d55781810151838201526020016113bd565b50505050905090810190601f1680156114025780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b50600083858161141c57fe5b0495945050505050565b600081848411156114785760405162461bcd60e51b81526020600482018181528351602484015283519092839260449091019190850190808383600083156113d55781810151838201526020016113bd565b50505090039056fe5661756c743a3a636c6f736528293a20636c6f736520616c6c6f776564206f6e6c792069662064656274206973207061796564206f6666536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f775661756c743a3a7061794f666628293a204d444c5920626f75676874206973206c657373207468616e2065787065637465645661756c743a3a7061794f666628293a206e6f7420657861637420616d6f756e74206f662045415520736f6c6420746f20627579204d444c595661756c743a3a62757928293a204e6f7420656e6f75676820746f6b656e7320746f2073656c6ca2646970667358221220d2c34d7149ac2f5172bc60d7741f2899c4fa457e0033172f45a0ed85da2426ff64736f6c634300070000334d65646c657944414f3a205472616e73666572206f66204d444c5920746f6b656e73206e6f7420616c6c6f7765644d65646c657944414f3a205472616e73666572206f66207573657220746f6b656e73206e6f7420616c6c6f776564a2646970667358221220b248a9b20ba9bbe82f03c149bd868c14f4ed397a67e00de95e8bc745827c05ae64736f6c63430007000033";

    public static final String FUNC_CREATEVAULT = "createVault";

    public static final String FUNC_GETEAUTOKENADDRESS = "getEauTokenAddress";

    public static final String FUNC_GETMDLYMARKET = "getMdlyMarket";

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

    public RemoteFunctionCall<String> getMdlyMarket() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMDLYMARKET, 
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

    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String mdlyToken, String eauToken, String mdlyPriceOracle, String mdlyMarket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle), 
                new org.web3j.abi.datatypes.Address(160, mdlyMarket)));
        return deployRemoteCall(MedleyDAO.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String mdlyToken, String eauToken, String mdlyPriceOracle, String mdlyMarket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle), 
                new org.web3j.abi.datatypes.Address(160, mdlyMarket)));
        return deployRemoteCall(MedleyDAO.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String mdlyToken, String eauToken, String mdlyPriceOracle, String mdlyMarket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle), 
                new org.web3j.abi.datatypes.Address(160, mdlyMarket)));
        return deployRemoteCall(MedleyDAO.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MedleyDAO> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String mdlyToken, String eauToken, String mdlyPriceOracle, String mdlyMarket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, mdlyToken), 
                new org.web3j.abi.datatypes.Address(160, eauToken), 
                new org.web3j.abi.datatypes.Address(160, mdlyPriceOracle), 
                new org.web3j.abi.datatypes.Address(160, mdlyMarket)));
        return deployRemoteCall(MedleyDAO.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class VaultCreationEventResponse extends BaseEventResponse {
        public String vault;

        public String owner;
    }
}
