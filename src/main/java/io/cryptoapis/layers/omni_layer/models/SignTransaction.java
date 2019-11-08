package io.cryptoapis.layers.omni_layer.models;

import io.cryptoapis.common_models.Stringify;

public class SignTransaction extends Stringify {

    private String hex;
    private String wif;

    private SignTransaction(String hex, String wif) {
        this.hex = hex;
        this.wif = wif;
    }

    public static SignTransaction signTransaction(String hex, String wif) {
        return new SignTransaction(hex, wif);
    }
}
