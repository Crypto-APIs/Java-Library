package com.cryptoapis.client;

import com.cryptoapis.connections.Bitcoin;
import com.cryptoapis.connections.Ethereum;
import com.cryptoapis.connections.Exchanges;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;
import com.cryptoapis.utils.enums.EnumOptions;

public class CryptoApis implements CryptoApisConstants {
    private String apiKey;

    public CryptoApis(String apiKey) {
        this.apiKey = apiKey;
    }

    public Ethereum connectToEth(String network, EnumOptions.IncludeRawJSON includeRawJSON) {
        return new Ethereum(setBlockChainConfig(CryptoApisConstants.ETHEREUM, network, includeRawJSON));
    }

    public Bitcoin connectToBtc(String network, EnumOptions.IncludeRawJSON includeRawJSON) {
        return new Bitcoin(setBlockChainConfig(CryptoApisConstants.BITCOIN, network, includeRawJSON));
    }

    public Exchanges connectToExchanges(EnumOptions.IncludeRawJSON includeRawJSON) {
        return new Exchanges(setConfig(includeRawJSON));
    }

    private EndpointConfig setBlockChainConfig(String blockchain, String network, EnumOptions.IncludeRawJSON includeRawJSON) {
        EndpointConfig newConfig = setConfig(includeRawJSON);
        newConfig.setBlockchain(blockchain);
        newConfig.setNetwork(network);
        return newConfig;
    }

    private EndpointConfig setConfig(EnumOptions.IncludeRawJSON includeRawJSON) {
       return new EndpointConfig(CryptoApisConstants.VERSION_V1, this.apiKey,
                includeRawJSON == EnumOptions.IncludeRawJSON.True);
    }
}
