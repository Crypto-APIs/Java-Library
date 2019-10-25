package io.cryptoapis.blockchains.ethereum_based.models;

import io.cryptoapis.common_models.Stringify;
import io.cryptoapis.utils.enums.KeyType;

public class Payment extends Stringify {
    private String from;
    private String to;
    private String callback;
    private String privateKey;
    private String password;
    private Integer confirmations;
    private Integer gasLimit;
    private Long gasPrice;

    private Payment(String from, String to, String callback, KeyType keyType, String key, Integer confirmations, Long gasPrice, Integer gasLimit) {
        this.from = from;
        this.to = to;
        this.callback = callback;
        this.confirmations = confirmations != null ? confirmations : 0;

        if (keyType == KeyType.PrivateKey) {
            this.privateKey = key;
        } else if (keyType == KeyType.Password) {
            this.password = key;
        }

        if (gasLimit != null)
            this.gasLimit = gasLimit;
        if (gasLimit != null) {
            this.gasPrice = gasPrice;
        }
    }

    public static Payment createPayment(String from, String to, String callback, KeyType keyType, String key,
                                        Integer confirmations, Long gasPrice, Integer gasLimit) {
        return new Payment(from, to, callback, keyType, key, confirmations, gasPrice, gasLimit);
    }

}
