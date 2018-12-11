package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cryptoapis.blockchains.ethereum.models.EthBlock;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;

import java.io.IOException;

public class EthBlockService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/blocks/{3}";

    private ObjectMapper mapper = new ObjectMapper();

    public EthBlockService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<EthBlock, ApiError> getBlock(String blockHash) {
        String endpoint = String.format("%s", blockHash);

        return fetchBlock(endpoint);
    }

    public Pair<EthBlock, ApiError> getBlock(int blockNumber) {
        String endpoint = String.format("%s", blockNumber);

        return fetchBlock(endpoint);
    }

    public Pair<EthBlock, ApiError> getLatestBlock() {
        return fetchBlock(CryptoApisConstants.LATEST);
    }

    private Pair<EthBlock, ApiError> fetchBlock(String endpoint) {
        ApiError apiError = null;

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        EthBlock ethBlock = null;
        try {
            if (res != null)
                ethBlock = mapper.readValue(res.getResponse(), EthBlock.class);
        } catch (IOException e) {
            apiError = Utils.convertToCustomClass(res.getResponse(), ApiError.class, endpointConfig);
        }

        return new Pair<>(ethBlock, apiError);
    }
}
