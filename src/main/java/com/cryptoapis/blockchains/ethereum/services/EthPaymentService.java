package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.ethereum.models.EthPayment.EthPayment;
import com.cryptoapis.blockchains.ethereum.models.EthPayment.EthPaymentHistory;
import com.cryptoapis.blockchains.ethereum.models.KeyType;
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

public class EthPaymentService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/payments/{3}";

    public EthPaymentService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthPayment, ApiError> createPFPvt(String fromAddress, String toAddress, String callBack, String key) {
        return createPF(fromAddress, toAddress, callBack, KeyType.PrivateKey, key);
    }

    public Pair<EthPayment, ApiError> createPFPwd(String fromAddress, String toAddress, String callBack, String key) {
        return createPF(fromAddress, toAddress, callBack, KeyType.Password, key);
    }

    public Pair<String, ApiError> deletePF(String address) {
        return Utils.deleteUnit(address, url, endpointConfig);
    }

    public Pair<List<EthPayment>, ApiError> listPayments() throws JSONException {
        return getPayments();
    }

    public Pair<List<EthPaymentHistory>, ApiError> listPHistory() throws JSONException {
        return getPaymentsHistory();
    }

    private Pair<List<EthPayment>, ApiError> getPayments() throws JSONException {
        ApiResponse payments = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        List<EthPayment> resultArray = new ArrayList<>();
        if (payments != null) {
            JSONObject jsonObject = new JSONObject(payments.getResponse());
            if (payments.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("payments")) {
                result = jsonObject.getString("payments");
            }

            if (result != null) {
                JSONArray paymentsArray = new JSONArray(result);

                for (int idx = 0; idx < paymentsArray.length(); idx++) {
                    EthPayment ethPayment = Utils.convertToCustomClass(paymentsArray.get(idx).toString(), EthPayment.class, endpointConfig);
                    if (ethPayment != null) {
                        resultArray.add(ethPayment);
                    }
                }
            }
        }

        return new Pair<>(resultArray, null);
    }

    private Pair<List<EthPaymentHistory>, ApiError> getPaymentsHistory() throws JSONException {
        ApiResponse history = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "history"), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        List<EthPaymentHistory> resultArray = new ArrayList<>();
        if (history != null) {

            JSONObject jsonObject = new JSONObject(history.getResponse());
            if (history.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("payments_history")) {
                result = jsonObject.getString("payments_history");
            }

            if (result != null) {
                JSONArray phArray = new JSONArray(result);

                for (int idx = 0; idx < phArray.length(); idx++) {
                    EthPaymentHistory ethPaymentHistory = Utils.convertToCustomClass(phArray.get(idx).toString(), EthPaymentHistory.class, endpointConfig);
                    if (ethPaymentHistory != null) {
                        resultArray.add(ethPaymentHistory);
                    }
                }
            }
        }
        return new Pair<>(resultArray, null);
    }

    private Pair<EthPayment, ApiError> createPF(String fromAddress, String toAddress, String callBack, KeyType keyType, String key) {
        EthPayment ethPayment = new EthPayment();
        ethPayment.setFromAddress(fromAddress);
        ethPayment.setToAddress(toAddress);
        ethPayment.setCallBack(callBack);

        if (keyType == KeyType.PrivateKey) {
            ethPayment.setPrivateKey(key);
        } else if (keyType == KeyType.Password) {
            ethPayment.setPassword(key);
        }

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, ethPayment.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), EthPayment.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }
}
