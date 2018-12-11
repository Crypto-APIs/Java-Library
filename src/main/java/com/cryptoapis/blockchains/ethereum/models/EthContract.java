package com.cryptoapis.blockchains.ethereum.models;

import com.google.gson.Gson;

import java.math.BigInteger;

public class EthContract {

    private String privateKey;
    private String fromAddress;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private String byteCode;

    private EthContract(String privateKey, String fromAddress, BigInteger gasPrice, BigInteger gasLimit, String byteCode) {
        this.privateKey = privateKey;
        this.fromAddress = fromAddress;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.byteCode = byteCode;
    }

    public static EthContract setData(String privateKey, String fromAddress, BigInteger gasPrice, BigInteger gasLimit, String byteCode) {
        return new EthContract(privateKey, fromAddress, gasPrice, gasLimit, byteCode);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
