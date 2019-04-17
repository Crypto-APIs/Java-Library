package com.cryptoapis.blockchains.bitcoin_based.services;

import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;

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
        return convertJSONtoBlock(blockHash);
    }

    public ApiResponse getBlock(int blockNumber) {
        return convertJSONtoBlock(String.valueOf(blockNumber));
    }

    public ApiResponse getLatestBlock() {
        return convertJSONtoBlock(CryptoApisConstants.LATEST);
    }

    private ApiResponse convertJSONtoBlock(String endpoint) {
       return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }
}
