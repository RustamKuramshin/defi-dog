package ru.javaboys.defidog.integrations;

import io.jmix.core.DataManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import ru.javaboys.defidog.entity.Cryptocurrency;
import ru.javaboys.defidog.entity.DeFiProtocol;
import ru.javaboys.defidog.entity.User;
import ru.javaboys.defidog.integrations.blockchain.BlockchainService;
import ru.javaboys.defidog.integrations.coinmarketcap.CoinMarketCapService;
import ru.javaboys.defidog.integrations.coinmarketcap.dto.*;
import ru.javaboys.defidog.integrations.dedaub.DedaubService;
import ru.javaboys.defidog.integrations.dedaub.dto.DecompilationDto;
import ru.javaboys.defidog.integrations.etherscan.EtherscanService;
import ru.javaboys.defidog.integrations.etherscan.dto.ContractAbiResponseDto;
import ru.javaboys.defidog.integrations.etherscan.dto.ContractSourceResponseDto;
import ru.javaboys.defidog.integrations.openai.OpenAiService;
import ru.javaboys.defidog.integrations.sourcify.SourcifyService;
import ru.javaboys.defidog.integrations.sourcify.dto.GetContract200Response;
import ru.javaboys.defidog.integrations.sourcify.dto.GetV2ContractsChainId200Response;
import ru.javaboys.defidog.integrations.sourcify.dto.VerifiedContractMinimal;
import ru.javaboys.defidog.integrations.telegram.TelegramBotService;
import ru.javaboys.defidog.test_support.AuthenticatedAsAdmin;
import ru.javaboys.defidog.utils.DotenvTestExecutionListener;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestExecutionListeners(
        listeners = DotenvTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@ExtendWith(AuthenticatedAsAdmin.class)
@TestPropertySource(properties = "defi-dog.scheduling.enable=false")
@Slf4j
public class IntegrationsTest {

    @Autowired
    private EtherscanService etherscanService;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @Autowired
    private DedaubService dedaubService;

    @Autowired
    private SourcifyService sourcifyService;

    @Test
    void shouldFetchContractSourceCodeFromEtherscan() {
        String chainId = "1"; // Ethereum Mainnet
        String contractAddress = "0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48"; // USDC

        ContractSourceResponseDto response = etherscanService.getContractSourceCode(chainId, contractAddress);

        log.info("Etherscan response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("1");
        assertThat(response.getResult()).isNotEmpty();
        assertThat(response.getResult().get(0).getSourceCode()).isNotBlank();
    }

    @Test
    void shouldFetchContractAbiFromEtherscan() {
        String chainId = "1"; // Ethereum Mainnet
        String contractAddress = "0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48"; // USDC

        ContractAbiResponseDto response = etherscanService.getContractAbi(chainId, contractAddress);

        log.info("Etherscan response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("1");
        assertThat(response.getResult()).isNotEmpty();
        assertThat(response.getResult()).isNotBlank();
    }

    @Test
    void shouldTalkToChatGPTSuccessfully() {
        String conversationId = UUID.randomUUID().toString();
        SystemMessage systemMessage = new SystemMessage("You are a helpful assistant.");
        UserMessage userMessage = new UserMessage("What's the capital of France?");

        String response = openAiService.talkToChatGPT(conversationId, systemMessage, userMessage);

        log.info("ChatGPT response: {}", response);

        assertThat(response).isNotBlank();
        assertThat(response.toLowerCase()).contains("paris");
    }

    @Test
    void shouldReturnLatestBlockNumber() {
        BigInteger blockNumber = blockchainService.getLastBlockNumber();

        log.info("Blockchain API response: {}", blockNumber);

        assertThat(blockNumber).isNotNull();
        assertThat(blockNumber.longValue()).isGreaterThan(0);
    }

    @Test
    void shouldReturnSmartContractBytecode() {
        String address = "0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48";
        String bytecode = blockchainService.getBytecode(address);

        log.info("Blockchain API response: {}", bytecode);

        assertThat(bytecode).isNotBlank().startsWith("0x");
    }

    @Test
    void shouldSendMessageToUser() {

        String message = "Привет из интеграционного теста!";

        var admin = dataManager.load(User.class)
                .query("e.username = ?1", "admin")
                .one();

        telegramBotService.sendMessageToUser(message, admin);
    }

    @Test
    void shouldFetchCryptocurrencyListingsLatest() {
        CoinMarketCapResponseDto response = coinMarketCapService.getCryptocurrencyListingsLatest();

        log.info("CoinMarketCap ListingsLatest response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();
        assertThat(response.getData().get(0).getName()).isNotBlank();
    }

    @Test
    void shouldFetchCryptocurrencyCoinMarketCapIDMap() {
        CoinMarketCapIdMapResponseDto response = coinMarketCapService.getCryptocurrencyCoinMarketCapIDMap();

        log.info("CoinMarketCap CryptocurrencyCoinMarketCapIDMap response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();
        assertThat(response.getData().get(0).getName()).isNotBlank();
    }

    @Test
    void shouldFetchCryptocurrencyQuotesLatest() {
        List<Integer> listSymbols = List.of(1839, 825, 1027, 4943, 3408, 5426);
        CoinMarketCapQuotesLatestResponseDto response = coinMarketCapService.getCryptocurrencyQuotesLatestByIds(listSymbols);

        log.info("CoinMarketCap CryptocurrencyCryptocurrencyQuotesLatest response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();
    }

    @Test
    void shouldFetchCryptocurrencyMetadata() {
        List<Integer> listSymbols = List.of(2, 825, 1975, 3408, 4943, 11419);
        CoinMarketCapMetadataDto response = coinMarketCapService.getCryptocurrencyMetadataByIds(listSymbols);

        log.info("CoinMarketCap CryptocurrencyCryptocurrencyMetadata response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();
    }

    @Test
    void shouldLoadCryptocurrencyLogo() {
        List<Integer> listSymbols = List.of(2, 825, 1975, 3408, 4943, 11419);
        CoinMarketCapMetadataDto response = coinMarketCapService.getCryptocurrencyMetadataByIds(listSymbols);

        log.info("CoinMarketCap CryptocurrencyLogo response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();

        for (Map.Entry<String, CryptocurrencyMetadataDto> cr : response.getData().entrySet()) {
            updateCryptoLogoByDto(cr.getValue());
        }
    }

    @Test
    void shouldLoadDexLogo() {
        List<Integer> listSymbols = List.of(7186, 8526, 7083, 4157, 5692, 7278);
        CoinMarketCapMetadataDto response = coinMarketCapService.getCryptocurrencyMetadataByIds(listSymbols);

        log.info("CoinMarketCap DexLogo response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getData()).isNotEmpty();

        for (Map.Entry<String, CryptocurrencyMetadataDto> cr : response.getData().entrySet()) {
            updateDexLogoByDto(cr.getValue());
        }
    }

    private void updateCryptoLogoByDto(CryptocurrencyMetadataDto dto) {

        Integer cmcId = dto.getId();

        // Загрузка существующей сущности по CMC_ID
        Cryptocurrency crypto = dataManager.load(Cryptocurrency.class)
                .query("select c from Cryptocurrency c where c.cmcId = :cmcId")
                .parameter("cmcId", cmcId)
                .optional()
                .orElse(null);

        if (crypto != null) {
            String logoUrl = dto.getLogo();

            try (InputStream in = new URL(logoUrl).openStream()) {
                crypto.setLogoImage(in.readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to download image: " + logoUrl, e);
            }

            // Сохраняем обновленную сущность
            dataManager.save(crypto);
        }
    }

    private void updateDexLogoByDto(CryptocurrencyMetadataDto dto) {

        Integer cmcId = dto.getId();

        // Загрузка существующей сущности по CMC_ID
        DeFiProtocol dex = dataManager.load(DeFiProtocol.class)
                .query("select c from DeFiProtocol c where c.cmcId = :cmcId")
                .parameter("cmcId", cmcId)
                .optional()
                .orElse(null);

        if (dex != null) {
            String logoUrl = dto.getLogo();

            try (InputStream in = new URL(logoUrl).openStream()) {
                dex.setLogoImage(in.readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to download image: " + logoUrl, e);
            }

            // Сохраняем обновленную сущность
            dataManager.save(dex);
        }
    }

    @Test
    void shouldFetchDexScanCoinMarketCapIDMap() {
        String response = coinMarketCapService.getDexScanCoinMarketCapIDMap();
        log.info("CoinMarketCap DexScanCoinMarketCapIDMap response: {}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void shouldFetchExchangeCoinMarketCapIDMap() {
        String response = coinMarketCapService.getExchangeCoinMarketCapIDMap();
        log.info("CoinMarketCap ExchangeCoinMarketCapIDMap response: {}", response);
        assertThat(response).isNotNull();
    }

    @Test
    void shouldDecompileBytecodeSuccessfully() {
        String bytecode = "0x60806040526004361061004e5760003560e01c80632d2c55651461008d578063819d4cc6146100de5780638980f11f146101005780638b21f170146101205780639342c8f41461015457600080fd5b36610088576040513481527f27f12abfe35860a9a927b465bb3d4a9c23c8428174b83f278fe45ed7b4da26629060200160405180910390a1005b600080fd5b34801561009957600080fd5b506100c17f0000000000000000000000003e40d73eb977dc6a537af587d48316fee66e9c8c81565b6040516001600160a01b0390911681526020015b60405180910390f35b3480156100ea57600080fd5b506100fe6100f93660046106bb565b610182565b005b34801561010c57600080fd5b506100fe61011b3660046106bb565b61024e565b34801561012c57600080fd5b506100c17f000000000000000000000000ae7ab96520de3a18e5e111b5eaab095312d7fe8481565b34801561016057600080fd5b5061017461016f3660046106f3565b610312565b6040519081526020016100d5565b6040518181526001600160a01b0383169033907f6a30e6784464f0d1f4158aa4cb65ae9239b0fa87c7f2c083ee6dde44ba97b5e69060200160405180910390a36040516323b872dd60e01b81523060048201526001600160a01b037f0000000000000000000000003e40d73eb977dc6a537af587d48316fee66e9c8c81166024830152604482018390528316906323b872dd90606401600060405180830381600087803b15801561023257600080fd5b505af1158015610246573d6000803e3d6000fd5b505050505050565b6000811161029a5760405162461bcd60e51b815260206004820152601460248201527316915493d7d49150d3d591549657d05353d5539560621b60448201526064015b60405180910390fd5b6040518181526001600160a01b0383169033907faca8fb252cde442184e5f10e0f2e6e4029e8cd7717cae63559079610702436aa9060200160405180910390a361030e6001600160a01b0383167f0000000000000000000000003e40d73eb977dc6a537af587d48316fee66e9c8c83610418565b5050565b6000336001600160a01b037f000000000000000000000000ae7ab96520de3a18e5e111b5eaab095312d7fe8416146103855760405162461bcd60e51b81526020600482015260166024820152754f4e4c595f4c49444f5f43414e5f574954484452415760501b6044820152606401610291565b478281116103935780610395565b825b91508115610412577f000000000000000000000000ae7ab96520de3a18e5e111b5eaab095312d7fe846001600160a01b0316634ad509b2836040518263ffffffff1660e01b81526004016000604051808303818588803b1580156103f857600080fd5b505af115801561040c573d6000803e3d6000fd5b50505050505b50919050565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b17905261046a90849061046f565b505050565b60006104c4826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166105419092919063ffffffff16565b80519091501561046a57808060200190518101906104e2919061070c565b61046a5760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b6064820152608401610291565b6060610550848460008561055a565b90505b9392505050565b6060824710156105bb5760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b6064820152608401610291565b843b6106095760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e74726163740000006044820152606401610291565b600080866001600160a01b03168587604051610625919061075e565b60006040518083038185875af1925050503d8060008114610662576040519150601f19603f3d011682016040523d82523d6000602084013e610667565b606091505b5091509150610677828286610682565b979650505050505050565b60608315610691575081610553565b8251156106a15782518084602001fd5b8160405162461bcd60e51b8152600401610291919061077a565b600080604083850312156106ce57600080fd5b82356001600160a01b03811681146106e557600080fd5b946020939093013593505050565b60006020828403121561070557600080fd5b5035919050565b60006020828403121561071e57600080fd5b8151801515811461055357600080fd5b60005b83811015610749578181015183820152602001610731565b83811115610758576000848401525b50505050565b6000825161077081846020870161072e565b9190910192915050565b602081526000825180602084015261079981604085016020870161072e565b601f01601f1916919091016040019291505056fea2646970667358221220c0f03149dd58fa21e9bfb72a010b74b1e518d704a2d63d8cc44c0ad3a2f573da64736f6c63430008090033";
        Duration timeout = Duration.ofSeconds(60);

        DecompilationDto result = dedaubService.decompileSmartContractBytecode(bytecode, timeout, 1000L);

        log.info("Decompilation response: {}", result);

        assertThat(result).isNotNull();
        assertThat(result.getMd5()).isNotBlank();
        assertThat(result.getSource()).isNotBlank();
    }

    @Test
    void shouldGetContractMetadata() {
        String chainId = "1";
        String address = "0xdAC17F958D2ee523a2206206994597C13D831ec7";
        String fields = "all";
        String omit = null;

        GetContract200Response response = sourcifyService.getContract(chainId, address, fields, omit);

        log.info("Sourcify response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getAddress()).isEqualToIgnoringCase(address);
        assertThat(response.getChainId()).isEqualTo(chainId);
    }

    @Test
    void shouldGetContractsList() {
        String chainId = "1";
        String sort = "asc";
        BigDecimal limit = BigDecimal.valueOf(5);
        String afterMatchId = null;

        GetV2ContractsChainId200Response response = sourcifyService.getV2ContractsChainId(chainId, sort, limit, afterMatchId);

        log.info("Sourcify response: {}", response);

        assertThat(response).isNotNull();
        assertThat(response.getResults().size()).isGreaterThan(1);
        assertThat(response.getResults().get(0).getMatch()).isEqualTo(VerifiedContractMinimal.MatchEnum.EXACT_MATCH);
    }
}
