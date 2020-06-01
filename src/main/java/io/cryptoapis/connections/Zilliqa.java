package io.cryptoapis.connections;


import io.cryptoapis.blockchains.zilliqa.services.TransactionService;
import io.cryptoapis.blockchains.zilliqa.services.BlockchainService;
import io.cryptoapis.blockchains.zilliqa.services.BlockService;
import io.cryptoapis.blockchains.zilliqa.services.AddressService;
import io.cryptoapis.utils.config.EndpointConfig;

public class Zilliqa extends ConnectionConstructor {
    private TransactionService transactionService;
    private BlockchainService blockchainService;
    private BlockService blockService;
    private AddressService addressService;

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public BlockchainService getBlockchainService() {
        return blockchainService;
    }

    public BlockService getBlockService() {
        return blockService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public Zilliqa(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    protected void initServices(EndpointConfig endpointConfig) {
        try {
            this.addressService = getConstructor(AddressService.class).newInstance(endpointConfig);
            this.blockchainService = getConstructor(BlockchainService.class).newInstance(endpointConfig);
            this.blockService = getConstructor(BlockService.class).newInstance(endpointConfig);
            this.transactionService = getConstructor(TransactionService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
