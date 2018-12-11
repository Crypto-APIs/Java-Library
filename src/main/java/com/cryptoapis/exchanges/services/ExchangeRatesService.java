package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.exchanges.models.ExchangeRates.CurrentRate;
import com.cryptoapis.exchanges.models.ExchangeRates.SpecificRate;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

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

    public Pair<SpecificRate, ApiError> getSpecificRate(String baseAssetId, String quoteAssetId, Map<String, String> params) {
        String endpoint = String.format("%s/%s", baseAssetId, quoteAssetId);
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }
        return sendSpecificRateRequest(endpoint.concat(pair.getKey()));
    }

    public Pair<CurrentRate, ApiError> getCurrentRate(String baseAssetId, Map<String, String> params) {
        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }
        return sendCurrentRateRequest(baseAssetId);
    }

    private Pair<SpecificRate, ApiError> sendSpecificRateRequest(String endpoint) {
        Pair<String, ApiError> pair = getAssetID(endpoint);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), SpecificRate.class, endpointConfig), null);
    }

    private Pair<CurrentRate, ApiError> sendCurrentRateRequest(String endpoint) {
        Pair<String, ApiError> pair = getAssetID(endpoint);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), CurrentRate.class, endpointConfig), null);
    }

    private Pair<String, ApiError> getAssetID(String endpoint) {

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrlExchanges(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }
}
