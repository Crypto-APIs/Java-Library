package io.cryptoapis.exchanges.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;

import java.util.Map;

public class QuotesService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/quotes/{1}";

    public QuotesService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getLatestData(Map<String, String> params) {
        return Utils.sendListRequest(CryptoApis.LATEST, params, url, endpointConfig);
    }

    public ApiResponse getHistoricalData(String symbol, Map<String, String> params) {
        String endpoint = String.format("%s/history", symbol);
        return Utils.sendListRequest(endpoint, params, url, endpointConfig);
    }

}
