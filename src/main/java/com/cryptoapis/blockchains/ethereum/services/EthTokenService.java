package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.ethereum.models.EthToken.EthToken;
import com.cryptoapis.blockchains.ethereum.models.EthToken.EthTokenAddress;
import com.cryptoapis.blockchains.ethereum.models.EthToken.EthTokenTransfer;
import com.cryptoapis.blockchains.ethereum.models.KeyType;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EthTokenService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/tokens/{3}";

    public EthTokenService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthToken, ApiError> getTokenBalance(String address, String contract) {
        String endpoint = String.format("%s/%s/balance", address, contract);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), EthToken.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> transferPvt(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String privateKey) {
        String body = createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, KeyType.PrivateKey, privateKey);

        return broadcastTransfer(body);
    }

    public Pair<String, ApiError> transferPwd(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String password) {
        String body = createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, KeyType.Password, password);

        return broadcastTransfer(body);
    }

    public Pair<List<EthTokenAddress>, ApiError> getTokensPerAddress(String address) throws JSONException {
        String endpoint = String.format("address/%s", address);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        List<EthTokenAddress> resultArray = new ArrayList<>();
        if (res != null) {

            JSONObject jsonObject = new JSONObject(res.getResponse());
            if (res.getStatusCode() >= 400) {
                return new Pair<>(null, Utils.convertToCustomClass(jsonObject.toString(), ApiError.class, endpointConfig));
            }

            String result = null;
            if (jsonObject.has("tokens_data")) {
                result = jsonObject.getString("tokens_data");
            }

            if (result != null) {
                JSONArray etArray = new JSONArray(result);

                for (int idx = 0; idx < etArray.length(); idx++) {
                    EthTokenAddress ethPaymentHistory = Utils.convertToCustomClass(etArray.get(idx).toString(), EthTokenAddress.class, endpointConfig);
                    if (ethPaymentHistory != null) {
                        resultArray.add(ethPaymentHistory);
                    }
                }
            }
        }

        return new Pair<>(resultArray, null);
    }

    private String createTokenTransaction(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key) {
        EthTokenTransfer ethToken = EthTokenTransfer.createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, keyType, key);
        return ethToken.toString();
    }

    private Pair<String, ApiError> broadcastTransfer(String body) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "transfer"), HttpsRequestsEnum.POST.name(), endpointConfig, body);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }
}
