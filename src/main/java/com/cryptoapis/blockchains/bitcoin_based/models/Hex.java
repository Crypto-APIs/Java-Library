package com.cryptoapis.blockchains.bitcoin_based.models;

import com.cryptoapis.common_models.Stringify;

public class Hex extends Stringify {
    private String hex;

    private Hex(String hex) {
        this.hex = hex;
    }

    public static Hex createHex(String hex) {
        return new Hex(hex);
    }
}
