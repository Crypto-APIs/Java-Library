package io.cryptoapis.blockchains.ethereum_based.services;

import io.cryptoapis.common_models.ApiResponse;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface TokenServiceInterface {

    ApiResponse getTokenBalance(String address, String contract);

    ApiResponse transferPvt(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String privateKey, int nonce);

    ApiResponse transferPwd(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String password, int nonce);

    ApiResponse getTotalSupplyAndDecimals(String contract);

}
