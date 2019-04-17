package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.bitcoin_based.models.Hex;
import com.cryptoapis.common_models.ApiError;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.blockchains.ethereum.models.EthRawTransaction;
import com.cryptoapis.utils.enums.KeyType;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class EthTransactionService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/txs/{3}";

    public EthTransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getTx(String hash) {
        String endpoint = String.format("hash/%s", hash);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTx(int blockNumber, int index) {
        String endpoint = String.format("block/%s/%s", blockNumber, index);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTx(String blockHash, int index) {
        String endpoint = String.format("block/%s/%s", blockHash, index);

        return getApiResponse(endpoint);
    }

    public ApiResponse getPendingTxs() {
        return getApiResponse("pending");
    }

    public ApiResponse getQueuedTxs() {
        return getApiResponse("queued");
    }

    public ApiResponse getTxByIdxAndLimit(int blockNumber, Map<String, String> params) {
        String endpoint = String.format("block/%s", blockNumber);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            Utils.setApiResponse(pair.getValue());
        }

        return getApiResponse(endpoint.concat(pair.getKey()));
    }

    public ApiResponse createTxKeyStore(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String password) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.Password, password, "new");
    }

    public ApiResponse createTxKeyStoreAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String password) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.Password, password, "new/all");
    }

    public ApiResponse createTxPvt(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String privateKey) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.PrivateKey, privateKey, "new-pvtkey");
    }

    public ApiResponse createTxPvtAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String privateKey) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.PrivateKey, privateKey, "new-pvtkey/all");
    }

    public ApiResponse getRawTxBody(String from, String to, BigDecimal value, String data) {
        return setRawTransactionBody(from, to, value, data, "send");
    }

    public ApiResponse estimateGasLimit(String from, String to, BigDecimal value, String data) {
        return setRawTransactionBody(from, to, value, data, "gas");
    }

    public ApiResponse broadcastSignedTx(String hex) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "push"), HttpsRequestsEnum.POST.name(),
                endpointConfig, Hex.createHex(hex).toString());
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType,
                                              String key, String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, gasPrice, gasLimit, value, data, keyType, key);
        return sendTx(endpoint, ethRawTransaction);
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigDecimal value, String data, String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, value, data);
        return sendTx(endpoint, ethRawTransaction);
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key,
                                              String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, gasPrice, gasLimit, data, keyType, key);
        return sendTx(endpoint, ethRawTransaction);
    }


    private ApiResponse getApiResponse(String endpoint) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    private ApiResponse sendTx(String endpoint, EthRawTransaction ethRawTransaction) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                ethRawTransaction.toString());
    }
}
