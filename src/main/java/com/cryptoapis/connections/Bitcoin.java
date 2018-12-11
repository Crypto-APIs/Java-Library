package com.cryptoapis.connections;

import com.cryptoapis.blockchains.bitcoin.services.*;
import com.cryptoapis.utils.config.EndpointConfig;
import org.apache.log4j.Logger;

public class Bitcoin extends ConnectionConstructor {
    private BtcTransactionService btcTransactionService;
    private BtcBlockchainService btcBlockchainService;
    private BtcBlockService btcBlockService;
    private BtcAddressService btcAddressService;
    private BtcWalletService btcWalletService;
    private BtcWebhookService btcWebhookService;
    private BtcPaymentService btcPaymentService;

    private static final Logger logger = Logger.getLogger(Bitcoin.class);

    public BtcTransactionService getBtcTransactionService() {
        return btcTransactionService;
    }

    public BtcBlockchainService getBtcBlockchainService() {
        return btcBlockchainService;
    }

    public BtcBlockService getBtcBlockService() {
        return btcBlockService;
    }

    public BtcWalletService getBtcWalletService() {
        return btcWalletService;
    }

    public BtcAddressService getBtcAddressService() {
        return btcAddressService;
    }

    public BtcWebhookService getBtcWebhookService() {
        return btcWebhookService;
    }

    public BtcPaymentService getBtcPaymentService() {
        return btcPaymentService;
    }

    public Bitcoin(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    private void initServices(EndpointConfig endpointConfig) {
        try {
            this.btcBlockchainService = getConstructor(BtcBlockchainService.class).newInstance(endpointConfig);
            this.btcBlockService = getConstructor(BtcBlockService.class).newInstance(endpointConfig);
            this.btcAddressService = getConstructor(BtcAddressService.class).newInstance(endpointConfig);
            this.btcWalletService = getConstructor(BtcWalletService.class).newInstance(endpointConfig);
            this.btcTransactionService = getConstructor(BtcTransactionService.class).newInstance(endpointConfig);
            this.btcWebhookService = getConstructor(BtcWebhookService.class).newInstance(endpointConfig);
            this.btcPaymentService = getConstructor(BtcPaymentService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
