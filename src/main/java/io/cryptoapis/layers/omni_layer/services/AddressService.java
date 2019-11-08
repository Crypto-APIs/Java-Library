package io.cryptoapis.layers.omni_layer.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

public class AddressService extends AbstractServicesConfig {

    private static final String PATH = "/{0}/bc/{1}/omni/{2}{3}";

    protected AddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getAddress(String data){
        String endpoint = String.format("/address/%s", data);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getAddressTransactions(String data){
        String endpoint = String.format("%s/transactions", data);

        return getAddress(endpoint);
    }

}
