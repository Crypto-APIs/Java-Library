package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cryptoapis.blockchains.ethereum.models.EthBlockchain;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

import java.io.IOException;

public class EthBlockchainService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/info";

    private ObjectMapper mapper = new ObjectMapper();

    public EthBlockchainService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthBlockchain, ApiError> getBlockchainInfo() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, null), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        EthBlockchain blockchain = null;
        ApiError apiError = null;
        try {
            if (res != null) {
                blockchain = mapper.readValue(res.getResponse(), EthBlockchain.class);
            }
        } catch (IOException e) {
            apiError = Utils.convertToCustomClass(res.getResponse(), ApiError.class, endpointConfig);
        }

        return new Pair<>(blockchain, apiError);
    }
}
