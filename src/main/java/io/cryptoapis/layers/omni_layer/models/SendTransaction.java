package io.cryptoapis.layers.omni_layer.models;

import io.cryptoapis.common_models.Stringify;

public class SendTransaction extends Stringify {

    String hex;

    private SendTransaction(String hex) {
        this.hex = hex;
    }

    public static SendTransaction sendTransaction(String hex) {
        return new SendTransaction(hex);
    }
}
