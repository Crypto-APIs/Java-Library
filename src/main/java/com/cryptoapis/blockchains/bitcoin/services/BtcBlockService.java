package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.blockchains.bitcoin.models.BtcBlock;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.log4j.Logger;

public class BtcBlockService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/blocks/{3}";

    protected Logger logger = Logger.getLogger(BtcBlockchainService.class.getName());

    public BtcBlockService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcBlock, ApiError> getBlock(String blockHash) {
        return convertJSONtoBlock(blockHash);
    }

    public Pair<BtcBlock, ApiError> getBlock(int blockNumber) {
        return convertJSONtoBlock(String.valueOf(blockNumber));
    }

    public Pair<BtcBlock, ApiError> getLatestBlock() {
        return convertJSONtoBlock(CryptoApisConstants.LATEST);
    }

    private Pair<BtcBlock, ApiError> convertJSONtoBlock(String endpoint) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcBlock.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }
}
