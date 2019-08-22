package io.cryptoapis.blockchains.ethereum.models;

import io.cryptoapis.common_models.Stringify;

public class EthAccount extends Stringify {
    private String password;

    private EthAccount(String password) {
        this.password = password;
    }

    public static EthAccount createAccount(String password) {
        return new EthAccount(password);
    }
}
