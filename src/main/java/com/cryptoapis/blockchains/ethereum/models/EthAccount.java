package com.cryptoapis.blockchains.ethereum.models;

import com.cryptoapis.common_models.Stringify;

public class EthAccount extends Stringify {
    private String password;

    private EthAccount(String password) {
        this.password = password;
    }

    public static EthAccount createAccount(String password) {
        return new EthAccount(password);
    }
}
