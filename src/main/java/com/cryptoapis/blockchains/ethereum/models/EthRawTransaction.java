package com.cryptoapis.blockchains.ethereum.models;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EthRawTransaction {

    private String fromAddress;
    private String toAddress;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private BigDecimal value;
    private String privateKey;
    private String password;
    private String data;

    private EthRawTransaction(String from, String to, BigDecimal value, String data) {
        this.fromAddress = from;
        this.toAddress = to;
        this.value = value;
        if (data != null) {
            this.data = data;
        }
    }

    private EthRawTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType, String key) {
        this.fromAddress = from;
        this.toAddress = to;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.value = value;

        if (keyType == KeyType.PrivateKey) {
            this.privateKey = key;
        } else if (keyType == KeyType.Password) {
            this.password = key;
        }

        if (data != null) {
            this.data = data;
        }
    }


    private EthRawTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key) {
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
    }

    public static EthRawTransaction createTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, BigDecimal value, String data, KeyType keyType, String key) {
        return new EthRawTransaction(from, to, gasPrice, gasLimit, value, data, keyType, key);
    }

    public static EthRawTransaction createTransaction(String from, String to, BigDecimal value, String data) {
        return new EthRawTransaction(from, to, value, data);
    }

    public static EthRawTransaction createTransaction(String from, String to, BigInteger gasPrice, BigInteger gasLimit, String data, KeyType keyType, String key) {
        return new EthRawTransaction(from, to, gasPrice, gasLimit, data, keyType, key);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
