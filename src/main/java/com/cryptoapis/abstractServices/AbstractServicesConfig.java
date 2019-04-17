package com.cryptoapis.abstractServices;

import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.constants.CryptoApisConstants;

public abstract class AbstractServicesConfig {
    protected EndpointConfig endpointConfig;

    protected String url;

    protected AbstractServicesConfig(EndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;
        this.url = CryptoApisConstants.CRYPTOAPIS_ENDPOINT + getPath();
    }

    protected abstract String getPath();
}
