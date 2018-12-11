package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.ethereum.models.EthAddress.EthAddressInfo;
import com.cryptoapis.blockchains.ethereum.models.EthAddress.EthAddressEckPair;
import com.cryptoapis.blockchains.ethereum.models.EthAddress.EthAddressBalance;
import com.cryptoapis.blockchains.ethereum.models.EthAddress.EthAddressTransactions;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EthAddressService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/{3}";

    public EthAddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthAddressEckPair, ApiError> generateNewAddress() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "address"), HttpsRequestsEnum.POST.name(), endpointConfig, StringUtils.EMPTY);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), EthAddressEckPair.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<EthAddressInfo, ApiError> getAddressInfo(String address) {
        String endpoint = String.format("address/%s", address);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), EthAddressInfo.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<EthAddressBalance, ApiError> getAddressBalance(String address) {
        String endpoint = String.format("address/%s/balance", address);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), EthAddressBalance.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> generateAccount(String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("password", password);
        } catch (JSONException e) {
            return null;
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "account"), HttpsRequestsEnum.POST.name(), endpointConfig, jsonObject.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<List<EthAddressTransactions>, ApiError> getTxsByAddress(String address, Map<String, String> params) throws JSONException {
        String endpoint = String.format("address/%s/transactions", address);
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        ApiResponse txs = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint.concat(pair.getKey())), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        List<EthAddressTransactions> list = null;

        if (txs != null) {

            JSONObject jsonObject = new JSONObject(txs.getResponse());
            if (txs.getStatusCode() >= 400) {
                ApiError apiError = Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig);

                return new Pair<>(null, apiError);
            }

            String result = null;
            if (jsonObject.has("txs")) {
                result = jsonObject.getString("txs");

            }

            if (result != null) {
                JSONArray txsArray = new JSONArray(result);

                list = convertToEthAddressTxs(txsArray);
            }
        }
        return new Pair<>(list, null);
    }

    private List<EthAddressTransactions> convertToEthAddressTxs(JSONArray txsArray) {
        List<EthAddressTransactions> resultArray = new ArrayList<>();

        for (int idx = 0; idx < txsArray.length(); idx++) {
            EthAddressTransactions transaction;
            try {
                transaction = Utils.convertToCustomClass(txsArray.get(idx).toString(), EthAddressTransactions.class, endpointConfig);
            } catch (JSONException e) {
                return null;
            }

            if (transaction == null) return null;

            resultArray.add(transaction);
        }

        return resultArray;
    }
}
