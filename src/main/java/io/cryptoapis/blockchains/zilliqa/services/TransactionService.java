package io.cryptoapis.blockchains.zilliqa.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiError;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.Utils;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

import java.util.Map;

public class TransactionService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/txs/{3}";

    protected TransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getTx(String hash) {
        String endpoint = String.format("hash/%s", hash);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTx(int blockNumber, int index) {
        String endpoint = String.format("block/%s/%s", blockNumber, index);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTx(String blockHash, int index) {
        String endpoint = String.format("block/%s/%s", blockHash, index);

        return getApiResponse(endpoint);
    }

    public ApiResponse getTxByIdxAndLimit(int blockNumber, Map<String, String> params) {
        String endpoint = String.format("block/%s", blockNumber);

        Pair<String, ApiError> pair = Utils.setQueryParams(params);
        if (pair.getValue() != null) {
            Utils.setApiResponse(pair.getValue());
        }

        return getApiResponse(endpoint.concat(pair.getKey()));
    }

    private ApiResponse getApiResponse(String endpoint) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint),
                HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
