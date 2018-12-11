package com.cryptoapis.exchanges.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.exchanges.models.Asset;
import com.cryptoapis.exchanges.models.Exchange;
import com.cryptoapis.exchanges.models.Symbol;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

public class MetadataService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/{1}";

    public MetadataService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<Exchange, ApiError> getExchangesList() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrlExchanges(url, endpointConfig, "exchanges"), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), Exchange.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<Asset, ApiError> getAssetsList() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrlExchanges(url, endpointConfig, "assets"), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), Asset.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public  Pair<Symbol, ApiError> getSymbolsList() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrlExchanges(url, endpointConfig, "mappings"), HttpsRequestsEnum.GET.name(),
                endpointConfig, null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), Symbol.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }
}
