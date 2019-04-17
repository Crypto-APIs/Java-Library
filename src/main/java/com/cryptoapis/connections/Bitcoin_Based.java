package com.cryptoapis.connections;

import com.cryptoapis.blockchains.bitcoin_based.services.*;
import com.cryptoapis.utils.config.EndpointConfig;
import org.apache.log4j.Logger;

abstract class Bitcoin_Based extends ConnectionConstructor {
    private TransactionService transactionService;
    private BlockchainService blockchainService;
    private BlockService blockService;
    private AddressService addressService;
    private WalletService walletService;
    private WebhookService webhookService;
    private PaymentService paymentService;

    private static final Logger logger = Logger.getLogger(Bitcoin_Based.class);

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public BlockchainService getBlockchainService() {
        return blockchainService;
    }

    public BlockService getBlockService() {
        return blockService;
    }

    public WalletService getWalletService() {
        return walletService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public WebhookService getWebhookService() {
        return webhookService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public Bitcoin_Based(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    private void initServices(EndpointConfig endpointConfig) {
        try {
            this.blockchainService = getConstructor(BlockchainService.class).newInstance(endpointConfig);
            this.blockService = getConstructor(BlockService.class).newInstance(endpointConfig);
            this.addressService = getConstructor(AddressService.class).newInstance(endpointConfig);
            this.walletService = getConstructor(WalletService.class).newInstance(endpointConfig);
            this.transactionService = getConstructor(TransactionService.class).newInstance(endpointConfig);
            this.webhookService = getConstructor(WebhookService.class).newInstance(endpointConfig);
            this.paymentService = getConstructor(PaymentService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
