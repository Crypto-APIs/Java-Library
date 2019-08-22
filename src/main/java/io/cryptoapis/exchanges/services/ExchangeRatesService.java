package io.cryptoapis.exchanges.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;

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
