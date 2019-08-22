package io.cryptoapis.abstractServices;

import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.constants.CryptoApisConstants;

public abstract class AbstractServicesConfig {
    protected EndpointConfig endpointConfig;

    protected String url;

    protected AbstractServicesConfig(EndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;
        this.url = CryptoApisConstants.CRYPTOAPIS_ENDPOINT + getPath();
    }

    protected abstract String getPath();
}
