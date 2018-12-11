package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.exchanges.models.Quotes.Data;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

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


    public Pair<Data, ApiError> getLatestData(Map<String, String> params) {
        String endpoint = "latest";

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return getData(endpoint.concat(pair.getKey()));
    }

    public Pair<Data, ApiError> getHistoricalData(String symbol, Map<String, String> params) {
        String endpoint = String.format("%s/history", symbol);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return getData(endpoint.concat(pair.getKey()));
    }

    private Pair<Data, ApiError> getData(String endpoint) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrlExchanges(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), Data.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }
}
