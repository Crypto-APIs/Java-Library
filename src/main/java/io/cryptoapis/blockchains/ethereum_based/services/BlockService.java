package io.cryptoapis.blockchains.ethereum_based.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.constants.CryptoApisConstants;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

public class BlockService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/blocks/{3}";


    public BlockService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse getBlock(String blockHash) {
        return fetchBlock(blockHash);
    }

    public ApiResponse getBlock(int blockNumber) {
        return fetchBlock(String.format("%s", blockNumber));
    }

    public ApiResponse getLatestBlock() {
        return fetchBlock(CryptoApisConstants.LATEST);
    }

    private ApiResponse fetchBlock(String endpoint) {
       return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
