package io.cryptoapis.connections;

import io.cryptoapis.blockchains.ethereum_based.services.EthTokenService;
import io.cryptoapis.utils.config.EndpointConfig;

public class Ethereum extends Ethereum_Based {
    private EthTokenService ethTokenService;

    @Override
    public EthTokenService getTokenService() {
        return ethTokenService;
    }

    public Ethereum(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected void initServices(EndpointConfig endpointConfig) {
        super.initServices(endpointConfig);
        try {
            this.ethTokenService = getConstructor(EthTokenService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}