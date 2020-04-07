package io.cryptoapis.connections;

import io.cryptoapis.blockchains.bitcoin_based.services.BtcTransactionService;
import io.cryptoapis.blockchains.bitcoin_based.services.BtcWalletService;
import io.cryptoapis.utils.config.EndpointConfig;

public class Bitcoin extends Bitcoin_Based {

    private BtcTransactionService btcTransactionService;
    private BtcWalletService btcWalletService;
    private Omni_Layer omniLayer;

    @Override
    public BtcTransactionService getTransactionService(){
        return btcTransactionService;
    }

    @Override
    public BtcWalletService getWalletService(){
        return btcWalletService;
    }

    public Bitcoin(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    public Omni_Layer getOmniLayer() {
        return omniLayer;
    }

    @Override
    protected void initServices(EndpointConfig endpointConfig) {
        super.initServices(endpointConfig);
        try {
            this.btcTransactionService = getConstructor(BtcTransactionService.class).newInstance(endpointConfig);
            this.btcWalletService = getConstructor(BtcWalletService.class).newInstance(endpointConfig);
            this.omniLayer = getConstructor(Omni_Layer.class).newInstance(endpointConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
