package com.cryptoapis.utils.config;

public class EndpointConfig {

    private String version;
    private String blockchain;
    private String network;
    private String apiKey;

    public EndpointConfig(String version, String apiKey) {
        this.version = version;
        this.apiKey = apiKey;
    }

    public String getVersion() {
        return version;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public String getNetwork() {
        return network;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

}
