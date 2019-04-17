package com.cryptoapis.blockchains.bitcoin_based.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiError;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import java.util.Map;

public class AddressService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/address/{3}";

    public AddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getAddressInfo(String address) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, address), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getMultisigAddressInfo(String address, Map<String, String> params) {
        String endpoint = String.format("%s/multisig", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse generateNewAddress() {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, StringUtils.EMPTY);
    }

    public ApiResponse getTxsByAddress(String address, Map<String, String> params) {
        String endpoint = String.format("%s/transactions", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
