package io.cryptoapis.exchanges.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

public class OrderBookService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/order-book/{3}";

    public OrderBookService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getSnapshotBySymbolId(String symbol_id) {
        String endpoint = String.format("%s/snapshot", symbol_id);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
