package com.cryptoapis.connections;

import com.cryptoapis.utils.config.EndpointConfig;

class ConnectionConstructor {
    <T> java.lang.reflect.Constructor<T> getConstructor(final Class<T> clazz) throws Exception {
        java.lang.reflect.Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(EndpointConfig.class);
        declaredConstructor.setAccessible(true);
        return declaredConstructor;
    }
}
