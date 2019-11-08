package io.cryptoapis.blockchains.ethereum_based.models;

import io.cryptoapis.common_models.Stringify;
import io.cryptoapis.utils.enums.KeyType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RawTransaction extends Stringify {

    private String fromAddress;
    private String toAddress;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private BigDecimal value;
    private String privateKey;
    private String password;
    private String data;

    //optional
    private BigInteger nonce;

    private RawTransaction(String from, String to, BigDecimal value, String data) {
        this.fromAddress = from;
        this.toAddress = to;
        this.value = value;
        if (data != null) {
            this.data = data;
        }
    }

    private RawTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType, String key, BigInteger nonce) {
        this(from, to, gasPrice, gasLimit, data, keyType, key, nonce);
        this.value = value;
    }


    private RawTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key, BigInteger nonce) {
        this.fromAddress = from;
        this.toAddress = to;

        if (gasPrice != null)
            this.gasPrice = gasPrice;

        if (gasLimit != null)
            this.gasLimit = gasLimit;

        if (keyType == KeyType.PrivateKey) {
            this.privateKey = key;
        } else if (keyType == KeyType.Password) {
            this.password = key;
        }

        if (data != null) {
            this.data = data;
        }

        if (nonce != null)
            this.nonce = nonce;
    }

    public static RawTransaction createTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType, String key, BigInteger nonce) {
        return new RawTransaction(from, to, gasPrice, gasLimit, value, data, keyType, key, nonce);
    }

    public static RawTransaction createTransaction(String from, String to, BigDecimal value, String data) {
        return new RawTransaction(from, to, value, data);
    }

    public static RawTransaction createTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key, BigInteger nonce) {
        return new RawTransaction(from, to, gasPrice, gasLimit, data, keyType, key, nonce);
    }

}
