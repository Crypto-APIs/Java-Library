package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.blockchains.bitcoin.models.BtcBlockchain;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;

import java.io.IOException;

public class BtcBlockchainService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/info";

    private ObjectMapper mapper = new ObjectMapper();

    public BtcBlockchainService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcBlockchain, ApiError> getBlockchainInfo() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, null), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        BtcBlockchain blockchain = null;
        ApiError apiError = null;
        try {
            if (res != null)
                blockchain = mapper.readValue(res.getResponse(), BtcBlockchain.class);
        } catch (IOException e) {
            apiError = Utils.convertToCustomClass(res.getResponse(), ApiError.class, endpointConfig);
        }

        return new Pair<>(blockchain, apiError);
    }
}
