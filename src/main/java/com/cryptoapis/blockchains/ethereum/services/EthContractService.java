package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.ethereum.models.EthContract;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;

public class EthContractService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/contracts/{3}";

    public EthContractService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public String estimateGasSC() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "gas"), HttpsRequestsEnum.GET.name(), endpointConfig, null);
        return res != null ? res.getResponse() : null;
    }

    public Pair<String, ApiError> deploySC(String privateKey, String fromAddress, BigInteger gasPrice, BigInteger gasLimit, String byteCode) {
        EthContract ethContract = EthContract.setData(privateKey, fromAddress, gasPrice, gasLimit, byteCode);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, ethContract.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }
}
