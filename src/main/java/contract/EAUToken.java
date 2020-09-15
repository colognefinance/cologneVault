package contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class EAUToken extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604080518082018252601881527f5465737420454155206c697175696469747920746f6b656e0000000000000000602080830191825283518085019094526008845267544553545f45415560c01b908401528151919291839183916200007b91600391620000aa565b50805162000091906004906020840190620000aa565b50506005805460ff191660121790555062000146915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000ed57805160ff19168380011785556200011d565b828001600101855582156200011d579182015b828111156200011d57825182559160200191906001019062000100565b506200012b9291506200012f565b5090565b5b808211156200012b576000815560010162000130565b61157c80620001566000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c806348650338116100ad57806395d89b411161007157806395d89b41146103af578063981b24d0146103b7578063a457c2d7146103d4578063a9059cbb14610400578063dd62ed3e1461042c57610121565b806348650338146102ee5780634ee2cd7e1461031457806370a082311461034057806379cc67901461036657806391c05b0b1461039257610121565b806323b872dd116100f457806323b872dd14610223578063313ce56714610259578063395093511461027757806340c10f19146102a357806342966c68146102d157610121565b806306fdde0314610126578063095ea7b3146101a35780631386ad20146101e357806318160ddd1461021b575b600080fd5b61012e61045a565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610168578181015183820152602001610150565b50505050905090810190601f1680156101955780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101cf600480360360408110156101b957600080fd5b506001600160a01b0381351690602001356104f0565b604080519115158252519081900360200190f35b610209600480360360208110156101f957600080fd5b50356001600160a01b031661050e565b60408051918252519081900360200190f35b6102096105c3565b6101cf6004803603606081101561023957600080fd5b506001600160a01b038135811691602081013590911690604001356105c9565b610261610650565b6040805160ff9092168252519081900360200190f35b6101cf6004803603604081101561028d57600080fd5b506001600160a01b038135169060200135610659565b6102cf600480360360408110156102b957600080fd5b506001600160a01b0381351690602001356106a7565b005b6102cf600480360360208110156102e757600080fd5b50356106b5565b6102cf6004803603602081101561030457600080fd5b50356001600160a01b03166106c9565b6102096004803603604081101561032a57600080fd5b506001600160a01b0381351690602001356107c0565b6102096004803603602081101561035657600080fd5b50356001600160a01b0316610809565b6102cf6004803603604081101561037c57600080fd5b506001600160a01b038135169060200135610824565b610209600480360360208110156103a857600080fd5b503561087e565b61012e6108b0565b610209600480360360208110156103cd57600080fd5b5035610911565b6101cf600480360360408110156103ea57600080fd5b506001600160a01b038135169060200135610941565b6101cf6004803603604081101561041657600080fd5b506001600160a01b0381351690602001356109a9565b6102096004803603604081101561044257600080fd5b506001600160a01b03813581169160200135166109bd565b60038054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104e65780601f106104bb576101008083540402835291602001916104e6565b820191906000526020600020905b8154815290600101906020018083116104c957829003601f168201915b5050505050905090565b60006105046104fd6109e8565b84846109ec565b5060015b92915050565b600080600a54116105505760405162461bcd60e51b81526004018080602001828103825260248152602001806114276024913960400191505060405180910390fd5b6001600160a01b0382166000908152600c60205260408120546001015b600a5481116105ba576000818152600b60205260409020546105ad8161059284610911565b036105a76105a088866107c0565b8490610ad8565b90610b38565b909201915060010161056d565b5090505b919050565b60025490565b60006105d6848484610b7a565b610646846105e26109e8565b6106418560405180606001604052806028815260200161146c602891396001600160a01b038a166000908152600160205260408120906106206109e8565b6001600160a01b031681526020810191909152604001600020549190610b97565b6109ec565b5060019392505050565b60055460ff1690565b60006105046106666109e8565b8461064185600160006106776109e8565b6001600160a01b03908116825260208083019390935260409182016000908120918c168152925290205490610c2e565b6106b18282610c88565b5050565b6106c66106c06109e8565b82610ca3565b50565b60006106d48261050e565b905060008111610722576040805162461bcd60e51b8152602060048201526014602482015273139bc8191a5d9a59195b991cc81858d8dc9d595960621b604482015290519081900360640190fd5b6040805163a9059cbb60e01b81526001600160a01b0384166004820152602481018390529051309163a9059cbb9160448083019260209291908290030181600087803b15801561077157600080fd5b505af1158015610785573d6000803e3d6000fd5b505050506040513d602081101561079b57600080fd5b5050600a546001600160a01b039092166000908152600c602052604090209190915550565b6001600160a01b0382166000908152600660205260408120819081906107e7908590610cbe565b91509150816107fe576107f985610809565b610800565b805b95945050505050565b6001600160a01b031660009081526020819052604090205490565b600061085b82604051806060016040528060248152602001611494602491396108548661084f6109e8565b6109bd565b9190610b97565b905061086f836108696109e8565b836109ec565b6108798383610ca3565b505050565b600061088a30836109a9565b50610893610dbb565b600a8181556000918252600b602052604090912092909255505490565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104e65780601f106104bb576101008083540402835291602001916104e6565b6000806000610921846007610cbe565b9150915081610937576109326105c3565b610939565b805b949350505050565b600061050461094e6109e8565b846106418560405180606001604052806025815260200161152260259139600160006109786109e8565b6001600160a01b03908116825260208083019390935260409182016000908120918d16815292529020549190610b97565b60006105046109b66109e8565b8484610b7a565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b3390565b6001600160a01b038316610a315760405162461bcd60e51b81526004018080602001828103825260248152602001806114fe6024913960400191505060405180910390fd5b6001600160a01b038216610a765760405162461bcd60e51b81526004018080602001828103825260228152602001806113df6022913960400191505060405180910390fd5b6001600160a01b03808416600081815260016020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b600082610ae757506000610508565b82820282848281610af457fe5b0414610b315760405162461bcd60e51b815260040180806020018281038252602181526020018061144b6021913960400191505060405180910390fd5b9392505050565b6000610b3183836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250610dca565b610b8383610e2f565b610b8c82610e2f565b610879838383610e59565b60008184841115610c265760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610beb578181015183820152602001610bd3565b50505050905090810190601f168015610c185780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b600082820183811015610b31576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b610c9182610e2f565b610c99610fb4565b6106b18282610fc3565b610cac82610e2f565b610cb4610fb4565b6106b182826110b3565b60008060008411610d0f576040805162461bcd60e51b815260206004820152601660248201527504552433230536e617073686f743a20696420697320360541b604482015290519081900360640190fd5b610d1960096111af565b841115610d6d576040805162461bcd60e51b815260206004820152601d60248201527f4552433230536e617073686f743a206e6f6e6578697374656e74206964000000604482015290519081900360640190fd5b6000610d7984866111b3565b8454909150811415610d92576000809250925050610db4565b6001846001018281548110610da357fe5b906000526020600020015492509250505b9250929050565b6000610dc5611254565b905090565b60008183610e195760405162461bcd60e51b8152602060048201818152835160248401528351909283926044909101919085019080838360008315610beb578181015183820152602001610bd3565b506000838581610e2557fe5b0495945050505050565b6001600160a01b03811660009081526006602052604090206106c690610e5483610809565b6112a8565b6001600160a01b038316610e9e5760405162461bcd60e51b81526004018080602001828103825260258152602001806114d96025913960400191505060405180910390fd5b6001600160a01b038216610ee35760405162461bcd60e51b815260040180806020018281038252602381526020018061139a6023913960400191505060405180910390fd5b610eee838383610879565b610f2b81604051806060016040528060268152602001611401602691396001600160a01b0386166000908152602081905260409020549190610b97565b6001600160a01b038085166000908152602081905260408082209390935590841681522054610f5a9082610c2e565b6001600160a01b038084166000818152602081815260409182902094909455805185815290519193928716927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef92918290030190a3505050565b610fc16007610e546105c3565b565b6001600160a01b03821661101e576040805162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b61102a60008383610879565b6002546110379082610c2e565b6002556001600160a01b03821660009081526020819052604090205461105d9082610c2e565b6001600160a01b0383166000818152602081815260408083209490945583518581529351929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a35050565b6001600160a01b0382166110f85760405162461bcd60e51b81526004018080602001828103825260218152602001806114b86021913960400191505060405180910390fd5b61110482600083610879565b611141816040518060600160405280602281526020016113bd602291396001600160a01b0385166000908152602081905260409020549190610b97565b6001600160a01b03831660009081526020819052604090205560025461116790826112f4565b6002556040805182815290516000916001600160a01b038516917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050565b5490565b81546000906111c457506000610508565b82546000905b808210156112135760006111de8383611336565b9050848682815481106111ed57fe5b906000526020600020015411156112065780915061120d565b8060010192505b506111ca565b60008211801561123b57508385600184038154811061122e57fe5b9060005260206000200154145b1561124c5750600019019050610508565b509050610508565b6000611260600961135b565b600061126c60096111af565b6040805182815290519192507f8030e83b04d87bef53480e26263266d6ca66863aa8506aca6f2559d18aa1cb67919081900360200190a1905090565b60006112b460096111af565b9050806112c084611364565b1015610879578254600180820185556000858152602080822090930193909355938401805494850181558252902090910155565b6000610b3183836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250610b97565b6000600280830660028506018161134957fe5b04600283046002850401019392505050565b80546001019055565b8054600090611375575060006105be565b81548290600019810190811061138757fe5b906000526020600020015490506105be56fe45524332303a207472616e7366657220746f20746865207a65726f206164647265737345524332303a206275726e20616d6f756e7420657863656564732062616c616e636545524332303a20617070726f766520746f20746865207a65726f206164647265737345524332303a207472616e7366657220616d6f756e7420657863656564732062616c616e6365546865726520686173206e6f74206265656e20646973747269627574696f6e7320796574536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f7745524332303a207472616e7366657220616d6f756e74206578636565647320616c6c6f77616e636545524332303a206275726e20616d6f756e74206578636565647320616c6c6f77616e636545524332303a206275726e2066726f6d20746865207a65726f206164647265737345524332303a207472616e736665722066726f6d20746865207a65726f206164647265737345524332303a20617070726f76652066726f6d20746865207a65726f206164647265737345524332303a2064656372656173656420616c6c6f77616e63652062656c6f77207a65726fa2646970667358221220ef8f00a9af4ebe6dc9392d7ea22176ab02a8dd0fbeba31c768cdf10db024e2cd64736f6c63430007010033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BALANCEOFAT = "balanceOfAt";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_DISTRIBUTE = "distribute";

    public static final String FUNC_DIVIDENSACCRUED = "dividensAccrued";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TOTALSUPPLYAT = "totalSupplyAt";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_WITHDRAWDIVIDENDS = "withdrawDividends";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SNAPSHOT_EVENT = new Event("Snapshot", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected EAUToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EAUToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EAUToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EAUToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<SnapshotEventResponse> getSnapshotEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SNAPSHOT_EVENT, transactionReceipt);
        ArrayList<SnapshotEventResponse> responses = new ArrayList<SnapshotEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SnapshotEventResponse typedResponse = new SnapshotEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SnapshotEventResponse> snapshotEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SnapshotEventResponse>() {
            @Override
            public SnapshotEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SNAPSHOT_EVENT, log);
                SnapshotEventResponse typedResponse = new SnapshotEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SnapshotEventResponse> snapshotEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SNAPSHOT_EVENT));
        return snapshotEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> balanceOfAt(String account, BigInteger snapshotId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOFAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(snapshotId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> burnFrom(String account, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURNFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> distribute(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISTRIBUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> dividensAccrued(String holder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DIVIDENSACCRUED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String account, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupplyAt(BigInteger snapshotId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLYAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(snapshotId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawDividends(String holder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWDIVIDENDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EAUToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EAUToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EAUToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EAUToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EAUToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EAUToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EAUToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EAUToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EAUToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EAUToken.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<EAUToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EAUToken.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EAUToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EAUToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EAUToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EAUToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class SnapshotEventResponse extends BaseEventResponse {
        public BigInteger id;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
