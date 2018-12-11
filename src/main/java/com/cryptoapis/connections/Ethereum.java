package com.cryptoapis.connections;

import com.cryptoapis.blockchains.ethereum.services.*;
import com.cryptoapis.utils.config.EndpointConfig;
import org.apache.log4j.Logger;

public class Ethereum extends ConnectionConstructor {
    private EthTransactionService ethTransactionService;
    private EthBlockService ethBlockService;
    private EthBlockchainService ethBlockchainService;
    private EthAddressService ethAddressService;
    private EthContractService ethContractService;
    private EthTokenService ethTokenService;
    private EthWebhookService ethWebhookService;
    private EthPaymentService ethPaymentService;

    private static final Logger logger = Logger.getLogger(Ethereum.class);

    public EthTransactionService getEthTransactionService() {
        return ethTransactionService;
    }

    public EthBlockService getEthBlockService() {
        return ethBlockService;
    }

    public EthBlockchainService getEthBlockchainService() {
        return ethBlockchainService;
    }

    public EthAddressService getEthAddressService() {
        return ethAddressService;
    }

    public EthContractService getEthContractService() {
        return ethContractService;
    }

    public EthTokenService getEthTokenService() {
        return ethTokenService;
    }

    public EthWebhookService getWebhookService() {
        return ethWebhookService;
    }

    public EthPaymentService getEthPaymentService() {
        return ethPaymentService;
    }

    public Ethereum(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    private void initServices(EndpointConfig endpointConfig) {
        try {
            this.ethTransactionService = getConstructor(EthTransactionService.class).newInstance(endpointConfig);
            this.ethBlockService = getConstructor(EthBlockService.class).newInstance(endpointConfig);
            this.ethBlockchainService = getConstructor(EthBlockchainService.class).newInstance(endpointConfig);
            this.ethAddressService = getConstructor(EthAddressService.class).newInstance(endpointConfig);
            this.ethContractService = getConstructor(EthContractService.class).newInstance(endpointConfig);
            this.ethTokenService = getConstructor(EthTokenService.class).newInstance(endpointConfig);
            this.ethWebhookService = getConstructor(EthWebhookService.class).newInstance(endpointConfig);
            this.ethPaymentService = getConstructor(EthPaymentService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}