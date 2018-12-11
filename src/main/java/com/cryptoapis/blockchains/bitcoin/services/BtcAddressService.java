package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.blockchains.bitcoin.models.BtcAddress.BtcAddress;
import com.cryptoapis.blockchains.bitcoin.models.BtcAddress.BtcAddressInfo;
import com.cryptoapis.blockchains.bitcoin.models.BtcAddress.BtcAddressTransactions;
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

public class BtcAddressService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/address/{3}";

    public BtcAddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcAddressInfo, ApiError> getAddressInfo(String address) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, address), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcAddressInfo.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<BtcAddress, ApiError> generateNewAddress() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, StringUtils.EMPTY);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcAddress.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<List<BtcAddressTransactions>, ApiError> getTxsByAddress(String address, Map<String, String> params) throws JSONException {
        String endpoint = String.format("%s/transactions", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        ApiResponse txs = WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        List<BtcAddressTransactions> list = null;

        if (txs != null) {

            JSONObject jsonObject = new JSONObject(txs.getResponse());
            if (txs.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("txs")) {
                result = jsonObject.getString("txs");
            }
            if (result != null) {
                JSONArray txsArray = new JSONArray(result);
                list = convertToBtcAddressTxs(txsArray);
            }
        }
        return new Pair<>(list, null);
    }

    private List<BtcAddressTransactions> convertToBtcAddressTxs(JSONArray txsArray) throws JSONException {
        List<BtcAddressTransactions> resultArray = new ArrayList<>();

        for (int idx = 0; idx < txsArray.length(); idx++) {
            BtcAddressTransactions transaction;
            transaction = Utils.convertToCustomClass(txsArray.get(idx).toString(), BtcAddressTransactions.class, endpointConfig);

            if (transaction == null) return null;

            resultArray.add(transaction);
        }

        return resultArray;
    }
}
