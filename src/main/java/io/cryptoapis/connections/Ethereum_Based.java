package io.cryptoapis.connections;

import io.cryptoapis.blockchains.ethereum.services.*;
import io.cryptoapis.utils.config.EndpointConfig;

abstract class Ethereum_Based extends ConnectionConstructor {
    private TransactionService transactionService;
    private BlockService blockService;
    private BlockchainService blockchainService;
    private AddressService addressService;
    private ContractService contractService;
    protected TokenService tokenService;
    private WebhookService webhookService;
    private PaymentService paymentService;

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public BlockService getBlockService() {
        return blockService;
    }

    public BlockchainService getBlockchainService() {
        return blockchainService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public WebhookService getWebhookService() {
        return webhookService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public Ethereum_Based(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    protected void initServices(EndpointConfig endpointConfig) {
        try {
            this.transactionService = getConstructor(TransactionService.class).newInstance(endpointConfig);
            this.blockService = getConstructor(BlockService.class).newInstance(endpointConfig);
            this.blockchainService = getConstructor(BlockchainService.class).newInstance(endpointConfig);
            this.addressService = getConstructor(AddressService.class).newInstance(endpointConfig);
            this.contractService = getConstructor(ContractService.class).newInstance(endpointConfig);
            this.tokenService = getConstructor(TokenService.class).newInstance(endpointConfig);
            this.webhookService = getConstructor(WebhookService.class).newInstance(endpointConfig);
            this.paymentService = getConstructor(PaymentService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
