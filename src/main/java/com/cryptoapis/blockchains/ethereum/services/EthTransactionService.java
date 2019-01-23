package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cryptoapis.blockchains.ethereum.models.EthRawTransaction;
import com.cryptoapis.blockchains.ethereum.models.EthTransaction;
import com.cryptoapis.blockchains.ethereum.models.KeyType;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EthTransactionService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/txs/{3}";

    private ObjectMapper mapper = new ObjectMapper();

    public EthTransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthTransaction, ApiError> getTx(String hash) {
        String endpoint = String.format("hash/%s", hash);

        return fetchTransaction(endpoint);
    }

    public Pair<EthTransaction, ApiError> getTx(int blockNumber, int index) {
        String endpoint = String.format("block/%s/%s", blockNumber, index);

        return fetchTransaction(endpoint);
    }

    public Pair<EthTransaction, ApiError> getTx(String blockHash, int index) {
        String endpoint = String.format("block/%s/%s", blockHash, index);

        return fetchTransaction(endpoint);
    }

    public Pair<EthTransaction, ApiError> getPendingTxs() {
        return fetchTransaction("pending");
    }

    public Pair<EthTransaction, ApiError> getQueuedTxs() {
        return fetchTransaction("queued");
    }

    public Pair<List<EthTransaction>, ApiError> getTxByIdxAndLimit(int blockNumber, Map<String, String> params) {
        String endpoint = String.format("block/%s", blockNumber);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        try {
            return getTransactionList(endpoint.concat(pair.getKey()));
        } catch (JSONException e) {
            return new Pair<>(null, Utils.convertToCustomClass("Could not get list", ApiError.class, null));
        }
    }

    public Pair<String, ApiError> createTxKeyStore(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String password) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.Password, password, "new");

    }

    public Pair<String, ApiError> createTxKeyStoreAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String password) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.Password, password, "new/all");
    }

    public Pair<String, ApiError> createTxPvt(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, String privateKey) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, value, data, KeyType.PrivateKey, privateKey, "new-pvtkey");
    }

    public Pair<String, ApiError> createTxPvtAll(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, String privateKey) {
        return setRawTransactionBody(from, to, gasPrice, gasLimit, data, KeyType.PrivateKey, privateKey, "new-pvtkey/all");
    }

    public Pair<String, ApiError> getRawTxBody(String from, String to, BigDecimal value, String data) {
        return setRawTransactionBody(from, to, value, data, "send");
    }

    public Pair<String, ApiError> estimateGasLimit(String from, String to, BigDecimal value, String data) {
        return setRawTransactionBody(from, to, value, data, "gas");
    }

    public Pair<String, ApiError> broadcastSignedTx(String signedTx) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("signed_tx", signedTx);
        } catch (JSONException e) {
            return null;
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "push"), HttpsRequestsEnum.POST.name(),
                endpointConfig, jsonObject.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    private Pair<EthTransaction, ApiError> fetchTransaction(String endpoint) {
        ApiError apiError = null;
        ApiResponse tx = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        EthTransaction transaction = null;
        try {
            if (tx != null)
                transaction = mapper.readValue(tx.getResponse(), EthTransaction.class);
        } catch (IOException e) {
            apiError = Utils.convertToCustomClass(tx.getResponse(), ApiError.class, endpointConfig);
        }
        return new Pair<>(transaction, apiError);
    }

    private Pair<List<EthTransaction>, ApiError> getTransactionList(String endpoint) throws JSONException {
        ApiResponse txs = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        List<EthTransaction> resultArray = new ArrayList<>();

        if (txs != null) {
            if (txs.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(txs.getResponse(), ApiError.class, endpointConfig));
            }

            JSONArray txsArray = new JSONArray(txs.getResponse());
            for (int idx = 0; idx < txsArray.length(); idx++) {
                EthTransaction transaction;
                try {
                    transaction = mapper.readValue(txsArray.get(idx).toString(), EthTransaction.class);
                } catch (Exception e) {
                    return null;
                }

                if (transaction != null) {
                    resultArray.add(transaction);
                }
            }
        }
        return new Pair<>(resultArray, null);
    }

    private Pair<String, ApiError> setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType,
                                                         String key, String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, gasPrice, gasLimit, value, data, keyType, key);
        return sendTx(endpoint, ethRawTransaction);
    }

    private Pair<String, ApiError> setRawTransactionBody(String from, String to, BigDecimal value, String data, String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, value, data);
        return sendTx(endpoint, ethRawTransaction);
    }

    private Pair<String, ApiError> setRawTransactionBody(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key,
                                                         String endpoint) {
        EthRawTransaction ethRawTransaction = EthRawTransaction.createTransaction(from, to, gasPrice, gasLimit, data, keyType, key);
        return sendTx(endpoint, ethRawTransaction);
    }


    private Pair<String, ApiError> sendTx(String endpoint, EthRawTransaction ethRawTransaction) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig, ethRawTransaction.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }
}
