package io.cryptoapis.blockchains.ethereum.models;

import io.cryptoapis.common_models.Stringify;

public class Account extends Stringify {
    private String password;

    private Account(String password) {
        this.password = password;
    }

    public static Account createAccount(String password) {
        return new Account(password);
    }
}
