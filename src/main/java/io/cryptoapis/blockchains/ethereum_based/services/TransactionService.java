package io.cryptoapis.blockchains.ethereum_based.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.blockchains.bitcoin_based.models.Hex;
import io.cryptoapis.blockchains.ethereum_based.models.RawTransaction;
import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.enums.KeyType;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class TransactionService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/txs/{3}";

    public TransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getInternalTxs(String hash) {
        String endpoint = String.format("hash/%s/internal", hash);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTx(String hash) {
        String endpoint = String.format("hash/%s", hash);

        return getApiResponse(endpoint);
    }

    public ApiResponse getBasicTx(String hash) {
        String endpoint = String.format("basic/hash/%s", hash);

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

    public ApiResponse createTxKeyStore(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String password, BigInteger nonce) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.Password, password, nonce, "new");
    }

    public ApiResponse createTxKeyStoreAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String password, BigInteger nonce) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.Password, password, nonce, "new/all");
    }

    public ApiResponse createTxPvt(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String privateKey, BigInteger nonce) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.PrivateKey, privateKey, nonce, "new-pvtkey");
    }

    public ApiResponse createTxPvtAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String privateKey, BigInteger nonce) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.PrivateKey, privateKey, nonce, "new-pvtkey/all");
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

    public ApiResponse getGasFees() {
        return getApiResponse("fee");
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType,
                                              String key, BigInteger nonce, String endpoint) {
        RawTransaction rawTransaction = RawTransaction.createTransaction(from, to, gasPrice, gasLimit, value, data, keyType, key, nonce);
        return sendTx(endpoint, rawTransaction);
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigDecimal value, String data, String endpoint) {
        RawTransaction rawTransaction = RawTransaction.createTransaction(from, to, value, data);
        return sendTx(endpoint, rawTransaction);
    }

    private ApiResponse setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key,
                                              BigInteger nonce, String endpoint) {
        RawTransaction rawTransaction = RawTransaction.createTransaction(from, to, gasPrice, gasLimit, data, keyType, key, nonce);
        return sendTx(endpoint, rawTransaction);
    }


    private ApiResponse getApiResponse(String endpoint) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    private ApiResponse sendTx(String endpoint, RawTransaction rawTransaction) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                rawTransaction.toString());
    }
}
