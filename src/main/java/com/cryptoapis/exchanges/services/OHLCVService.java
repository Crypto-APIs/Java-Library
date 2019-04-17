package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;

import java.util.Map;

public class OHLCVService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/ohlcv/{1}";

    public OHLCVService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getPeriodsList(Map<String, String> params) {
        return Utils.sendListRequest("periods", params, url, endpointConfig);
    }

    public ApiResponse getLatestData(String symbol, Map<String, String> params) {
        String endpoint = String.format("latest/%s/", symbol);
        return Utils.sendListRequest(endpoint, params, url, endpointConfig);
    }

    public ApiResponse getHistoricalData(String symbol, Map<String, String> params) {
        String endpoint = String.format("history/%s/", symbol);
        return Utils.sendListRequest(endpoint, params, url, endpointConfig);
    }
}
