package io.cryptoapis.connections;

import io.cryptoapis.layers.omni_layer.services.AddressService;
import io.cryptoapis.layers.omni_layer.services.NodeInfoService;
import io.cryptoapis.layers.omni_layer.services.TransactionService;
import io.cryptoapis.utils.config.EndpointConfig;

public class Omni_Layer extends ConnectionConstructor {

    private AddressService addressService;
    private NodeInfoService nodeInfoService;
    private TransactionService transactionService;

    public AddressService getAddressService() {
        return addressService;
    }

    public NodeInfoService getNodeInfoService() {
        return nodeInfoService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public Omni_Layer(EndpointConfig endpointConfig) {
        initServices(endpointConfig);
    }

    protected void initServices(EndpointConfig endpointConfig) {
        try {
            this.addressService = getConstructor(AddressService.class).newInstance(endpointConfig);
            this.nodeInfoService = getConstructor(NodeInfoService.class).newInstance(endpointConfig);
            this.transactionService = getConstructor(TransactionService.class).newInstance(endpointConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}