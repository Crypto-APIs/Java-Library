package io.cryptoapis.blockchains.bitcoin_based.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.rest.WebServices;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import java.util.Map;

public class AddressService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/address{3}";

    public AddressService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getAddressInfo(String address) {
        String endpoint = String.format("/%s" , address);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getMultisigAddressInfo(String address, Map<String, String> params) {
        String endpoint = String.format("/%s/multisig", address);

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
        String endpoint = String.format("/%s/transactions", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getUnconfirmedTxsByAddress(String address, Map<String, String> params) {
        String endpoint = String.format("/%s/unconfirmed-transactions", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getBasicTxsByAddress(String address, Map<String, String> params) {
        String endpoint = String.format("/%s/basic/transactions", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            return Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url.concat(pair.getKey()), endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
