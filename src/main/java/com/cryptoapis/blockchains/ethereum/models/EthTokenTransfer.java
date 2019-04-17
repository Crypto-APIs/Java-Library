package com.cryptoapis.blockchains.ethereum.models;

import com.cryptoapis.common_models.Stringify;
import com.cryptoapis.utils.enums.KeyType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EthTokenTransfer extends Stringify {
    private String fromAddress;
    private String toAddress;
    private String contract;
    private String password;
    private String privateKey;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private BigDecimal token;

    private EthTokenTransfer(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.contract = contract;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.token = token;

        if (keyType == KeyType.PrivateKey) {
            this.privateKey = key;
        } else if (keyType == KeyType.Password) {
            this.password = key;
        }
    }

    public static EthTokenTransfer createTokenTransaction(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key) {
        return new EthTokenTransfer(fromAddress, toAddress, contract, gasPrice, gasLimit, token, keyType, key);
    }
}
