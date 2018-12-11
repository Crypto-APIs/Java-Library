package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.blockchains.bitcoin.models.BtcAddress.BtcAddressTransactions;
import com.cryptoapis.blockchains.bitcoin.models.BtcTransaction.BtcTransaction;
import com.cryptoapis.blockchains.bitcoin.models.BtcTransaction.BtcTransactionBody;
import com.cryptoapis.blockchains.bitcoin.models.BtcTransaction.BtcTransactionHistorical;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.rest.WebServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BtcTransactionService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/txs/{3}";

    public BtcTransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    protected Logger logger = Logger.getLogger(BtcTransactionService.class.getName());

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcTransaction, ApiError> getTx(String hash) {
        String endpoint = String.format("txid/%s", hash);
        return getTransaction(endpoint);
    }

    public Pair<BtcTransaction, ApiError> getTx(String blockHash, int index) {
        String endpoint = String.format("block/%s/%s", blockHash, index);
        return getTransaction(endpoint);
    }

    public Pair<List<BtcTransaction>, ApiError> getTxByIdxAndLimit(int blockNumber, Map<String, String> params) {
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

    public Pair<String, ApiError> getUnconfirmedTxs() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<List<BtcTransaction>, ApiError> traceTxs(List<String> txsList) {
        if (txsList == null) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("Transactions list must not be empty", 2004), ApiError.class, null));
        }

        JSONObject jbObj = new JSONObject();
        try {
            jbObj.put("txs", txsList);
        } catch (JSONException e) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
        }

        ApiResponse txs = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "trace"), HttpsRequestsEnum.POST.name(), endpointConfig, jbObj.toString());
        List<BtcTransaction> resultArray = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        if (txs != null) {
            if (txs.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(txs.getResponse(), ApiError.class, endpointConfig));
            }

            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(txs.getResponse());
            } catch (JSONException e) {
                return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
            }

            String result = null;
            if (jsonObject.has("result")) {
                try {
                    result = jsonObject.getString("result");
                } catch (JSONException e) {
                    return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
                }
            }
            if (result != null) {
                JSONArray txsArray;
                try {
                    txsArray = new JSONArray(result);
                } catch (JSONException e) {
                    return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
                }

                for (int idx = 0; idx < txsArray.length(); idx++) {
                    BtcTransaction transaction;
                    try {
                        transaction = mapper.readValue(txsArray.get(idx).toString(), BtcTransaction.class);
                    } catch (Exception e) {
                        return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("General error", 1), ApiError.class, null));
                    }

                    if (transaction != null) {
                        resultArray.add(transaction);
                    }
                }
            }
        }
        return new Pair<>(resultArray, null);
    }

    public Pair<BtcTransactionHistorical, ApiError> getHistoricalTxs(Map<String, String> params) {
        String endpoint = "history";
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint.concat(pair.getKey())), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcTransactionHistorical.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> createTx(List<BtcTransactionBody.Inputs> inputs, List<BtcTransactionBody.Outputs> outputs, BigDecimal fee) {
        BtcTransactionBody btcTransactionBody = BtcTransactionBody.createTransactionBody(inputs, outputs, fee);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "new"), HttpsRequestsEnum.POST.name(), endpointConfig, btcTransactionBody.toString());

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);

    }

    public Pair<String, ApiError> sendTx(String txHash) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("toSend", txHash);
        } catch (JSONException e) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "send"), HttpsRequestsEnum.POST.name(), endpointConfig, jsonObject.toString());

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<BtcTransaction, ApiError> decodeTx(String txHex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("txHex", txHex);
        } catch (JSONException e) {
            return new Pair<>(null, Utils.convertToCustomClass(Utils.setError("JSON error", 30), ApiError.class, null));
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "decode"), HttpsRequestsEnum.POST.name(), endpointConfig, jsonObject.toString());

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcTransaction.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }


    private Pair<BtcTransaction, ApiError> getTransaction(String endpoint) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcTransaction.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    private Pair<List<BtcTransaction>, ApiError> getTransactionList(String endpoint) throws JSONException {
        ApiResponse txs = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        List<BtcTransaction> resultArray = new ArrayList<>();

        if (txs != null) {
            if (txs.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(txs.getResponse(), ApiError.class, endpointConfig));
            }

            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(txs.getResponse());
            } catch (JSONException e) {
                return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
            }

            String result = null;
            if (jsonObject.has("result")) {
                try {
                    result = jsonObject.getString("result");
                } catch (JSONException e) {
                    return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
                }
            }

            if (result != null) {
                JSONArray txsArray = new JSONArray(result);
                for (int idx = 0; idx < txsArray.length(); idx++) {
                    BtcTransaction transaction;
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        transaction = mapper.readValue(txsArray.get(idx).toString(), BtcTransaction.class);
                    } catch (Exception e) {
                        return null;
                    }

                    if (transaction != null) {
                        resultArray.add(transaction);
                    }
                }
            }
        }
        return new Pair<>(resultArray, null);
    }
}
