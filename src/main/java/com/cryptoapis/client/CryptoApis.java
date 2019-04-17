package com.cryptoapis.client;

import com.cryptoapis.connections.*;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;

public class CryptoApis implements CryptoApisConstants {
    private String apiKey;

    public CryptoApis(String apiKey) {
        this.apiKey = apiKey;
    }

    public Ethereum connectToEth(String network) {
        return new Ethereum(setBlockChainConfig(CryptoApisConstants.ETHEREUM, network));
    }

    public Bitcoin connectToBtc(String network) {
        return new Bitcoin(setBlockChainConfig(CryptoApisConstants.BITCOIN, network));
    }

    public Litecoin connectToLtc(String network) {
        return new Litecoin(setBlockChainConfig(CryptoApisConstants.LITECOIN, network));
    }

    public Bitcoin_Cash connectToBch(String network) {
        return new Bitcoin_Cash(setBlockChainConfig(CryptoApisConstants.BITCOIN_CASH, network));
    }

    public Exchanges connectToExchanges() {
        return new Exchanges(setConfig());
    }

    private EndpointConfig setBlockChainConfig(String blockchain, String network) {
        EndpointConfig newConfig = setConfig();
        newConfig.setBlockchain(blockchain);
        newConfig.setNetwork(network);
        return newConfig;
    }

    private EndpointConfig setConfig() {
       return new EndpointConfig(CryptoApisConstants.VERSION_V1, this.apiKey);
    }
}
