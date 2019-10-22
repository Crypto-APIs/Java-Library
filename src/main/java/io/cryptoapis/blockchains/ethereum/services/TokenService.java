package io.cryptoapis.blockchains.ethereum.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.blockchains.ethereum.models.TokenTransfer;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.enums.KeyType;
import io.cryptoapis.utils.rest.WebServices;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenService extends AbstractServicesConfig implements TokenServiceInterface {

    private static final String PATH = "/{0}/bc/{1}/{2}/tokens/{3}";

    public TokenService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    public ApiResponse getTokenBalance(String address, String contract) {
        String endpoint = String.format("%s/%s/balance", address, contract);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    @Override
    public ApiResponse transferPvt(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String privateKey, int nonce) {
        String body = createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, KeyType.PrivateKey, privateKey, nonce);

        return broadcastTransfer(body);
    }

    @Override
    public ApiResponse transferPwd(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, String password, int nonce) {
        String body = createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, KeyType.Password, password, nonce);

        return broadcastTransfer(body);
    }

    @Override
    public ApiResponse getTotalSupplyAndDecimals(String contract) {
        String endpoint = String.format("contract/%s", contract);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    private String createTokenTransaction(String fromAddress, String toAddress, String contract, BigInteger gasPrice, BigInteger gasLimit, BigDecimal token, KeyType keyType, String key, Integer nonce) {
        TokenTransfer ethToken = TokenTransfer.createTokenTransaction(fromAddress, toAddress, contract, gasPrice, gasLimit, token, keyType, key, nonce);

        return ethToken.toString();
    }

    private ApiResponse broadcastTransfer(String body) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "transfer"), HttpsRequestsEnum.POST.name(), endpointConfig, body);
    }

    @Override
    protected String getPath() {
        return PATH;
    }
}
