package com.cryptoapis.blockchains.bitcoin_based.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;

public class BlockchainService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/info";

    public BlockchainService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getBlockchainInfo() {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, null), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
