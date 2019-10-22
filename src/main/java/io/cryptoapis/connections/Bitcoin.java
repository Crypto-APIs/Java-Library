package io.cryptoapis.connections;

import io.cryptoapis.blockchains.bitcoin_based.services.BtcTransactionService;
import io.cryptoapis.utils.config.EndpointConfig;

public class Bitcoin extends Bitcoin_Based {

    private BtcTransactionService btcTransactionService;

    @Override
    public BtcTransactionService getTransactionService(){
        return btcTransactionService;
    }

    public Bitcoin(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected void initServices(EndpointConfig endpointConfig) {
        try {
            this.btcTransactionService = getConstructor(BtcTransactionService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
