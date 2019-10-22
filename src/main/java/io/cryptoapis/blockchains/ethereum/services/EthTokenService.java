package io.cryptoapis.blockchains.ethereum.services;

import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

import java.util.Map;

public class EthTokenService extends TokenService {

    public EthTokenService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    public ApiResponse getAllTokens(Map<String, String> params) {
        String endpoint = "all";

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint.concat(pair.getKey())), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getTokensByAddress(String address) {
        String endpoint = String.format("address/%s", address);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getTokenTxsByAddress(String address, Map<String, String> params) {
        String endpoint = String.format("address/%s/transfers", address);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            Utils.setApiResponse(pair.getValue());
        }

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint.concat(pair.getKey())), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
