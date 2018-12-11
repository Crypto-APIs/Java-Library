package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.blockchains.bitcoin.models.BtcPayment;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BtcPaymentService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/payments/{3}";

    protected BtcPaymentService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcPayment, ApiError> createPFPwd(String from, String to, String callBack, String wallet, String password) {
        return createPF(from, to, callBack, wallet, password);
    }

    public Pair<String, ApiError> deletePF(String address) {
        return Utils.deleteUnit(address, url, endpointConfig);
    }

    public Pair<List<BtcPayment>, ApiError> listPayments() {
        return getPayments();
    }

    private Pair<BtcPayment, ApiError> createPF(String from, String to, String callback, String wallet, String password) {
        BtcPayment btcPayment = new BtcPayment();
        btcPayment.setFrom(from);
        btcPayment.setTo(to);
        btcPayment.setCallback(callback);
        btcPayment.setWallet(wallet);
        btcPayment.setPassword(password);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, btcPayment.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcPayment.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    private Pair<List<BtcPayment>, ApiError> getPayments() {
        ApiResponse payments = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        List<BtcPayment> resultArray = new ArrayList<>();
        if (payments != null) {


            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(payments.getResponse());
            } catch (JSONException e) {
                return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
            }
            if (payments.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("payments")) {
                try {
                    result = jsonObject.getString("payments");
                } catch (JSONException e) {
                    return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
                }
            }

            if (result != null) {
                JSONArray paymentsArray;
                try {
                    paymentsArray = new JSONArray(result);
                } catch (JSONException e) {
                    return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
                }
                for (int idx = 0; idx < paymentsArray.length(); idx++) {
                    BtcPayment ethPayment;
                    try {
                        ethPayment = Utils.convertToCustomClass(paymentsArray.get(idx).toString(), BtcPayment.class, endpointConfig);
                    } catch (JSONException e) {
                        return new Pair<>(null, Utils.convertToCustomClass(Utils.setError(e.getMessage(), 30), ApiError.class, null));
                    }
                    if (ethPayment != null) {
                        resultArray.add(ethPayment);
                    }
                }
            }
        }

        return new Pair<>(resultArray, null);
    }
}
