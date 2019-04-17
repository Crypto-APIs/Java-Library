package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;

import java.util.Map;

public class MetadataService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/{1}";

    public MetadataService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getExchangesList(Map<String, String> params) {
        return Utils.sendListRequest("exchanges", params, url, endpointConfig);
    }

    public ApiResponse getAssetsList(Map<String, String> params) {
        return Utils.sendListRequest("assets", params, url, endpointConfig);
    }

    public ApiResponse getSymbolsList(Map<String, String> params) {
        return Utils.sendListRequest("mappings", params, url, endpointConfig);
    }


}
