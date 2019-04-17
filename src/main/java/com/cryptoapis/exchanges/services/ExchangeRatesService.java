package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;

import java.util.Map;

public class ExchangeRatesService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/exchange-rates/{1}";

    public ExchangeRatesService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getSpecificRate(String baseAssetId, String quoteAssetId, Map<String, String> params) {
        String endpoint = String.format("%s/%s", baseAssetId, quoteAssetId);
       return Utils.sendListRequest(endpoint, params, url, endpointConfig);
    }

    public ApiResponse getCurrentRate(String baseAssetId, Map<String, String> params) {
        return Utils.sendListRequest(baseAssetId, params, url, endpointConfig);
    }
}
