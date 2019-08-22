package io.cryptoapis.blockchains.bitcoin_based.models;

import io.cryptoapis.common_models.Stringify;

public class Hex extends Stringify {
    private String hex;

    private Hex(String hex) {
        this.hex = hex;
    }

    public static Hex createHex(String hex) {
        return new Hex(hex);
    }
}
