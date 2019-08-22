package io.cryptoapis.blockchains.ethereum.models;

import io.cryptoapis.common_models.Stringify;
import io.cryptoapis.utils.enums.KeyType;

public class EthPayment extends Stringify {
    private String from;
    private String to;
    private String callback;
    private String privateKey;
    private String password;
    private Integer confirmations;
    private Integer gasLimit;
    private Long gasPrice;

    private EthPayment(String from, String to, String callback, KeyType keyType, String key, Integer confirmations, Long gasPrice, Integer gasLimit) {
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

    public static EthPayment createPayment(String from, String to, String callback, KeyType keyType, String key,
                                           Integer confirmations,  Long gasPrice, Integer gasLimit) {
        return new EthPayment(from, to, callback, keyType, key, confirmations, gasPrice, gasLimit);
    }

}
