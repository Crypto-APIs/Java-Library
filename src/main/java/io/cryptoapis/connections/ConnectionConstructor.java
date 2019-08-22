package io.cryptoapis.connections;

import io.cryptoapis.utils.config.EndpointConfig;

import java.lang.reflect.Constructor;

class ConnectionConstructor {
    <T> Constructor<T> getConstructor(final Class<T> clazz) throws Exception {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(EndpointConfig.class);
        declaredConstructor.setAccessible(true);
        return declaredConstructor;
    }
}
