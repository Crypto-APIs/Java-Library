package io.cryptoapis.blockchains.ethereum_based.models;

import io.cryptoapis.common_models.Stringify;
import io.cryptoapis.utils.enums.KeyType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenTransfer extends Stringify {
    private String fromAddress;
    private String toAddress;
    private String contract;
    private String password;
    private String privateKey;
    private BigInteger gasPrice;
    private BigInteger gasLimit;
    private BigDecimal token;

    //optional field
    private Integer nonce;

    private TokenTransfer(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key, Integer nonce) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.contract = contract;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.token = token;
        this.nonce = nonce;

        if (keyType == KeyType.PrivateKey) {
            this.privateKey = key;
        } else if (keyType == KeyType.Password) {
            this.password = key;
        }
    }

    public static TokenTransfer createTokenTransaction(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key, Integer nonce) {
        return new TokenTransfer(fromAddress, toAddress, contract, gasPrice, gasLimit, token, keyType, key, nonce);
    }
}
