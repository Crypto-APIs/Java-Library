package com.cryptoapis.blockchains.ethereum.services;

import com.cryptoapis.blockchains.ethereum.models.EthPayment;
import com.cryptoapis.utils.enums.KeyType;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import org.apache.commons.lang.StringUtils;

public class EthPaymentService extends AbstractServicesConfig {
    private static final String PATH = "/{0}/bc/{1}/{2}/payments/{3}";

    public EthPaymentService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    //use private key
    public ApiResponse createPFPvt(String from, String to, String callback, String privateKey, Integer confirmations, Long gasPrice, Integer gasLimit) {
        return createPF(from, to, callback, KeyType.PrivateKey, privateKey, confirmations, gasPrice, gasLimit);
    }

    //use password
    public ApiResponse createPFPwd(String from, String to, String callback, String password, Integer confirmations, Long gasPrice, Integer gasLimit) {
        return createPF(from, to, callback, KeyType.Password, password, confirmations, gasPrice, gasLimit);
    }

    public ApiResponse deletePF(String paymentID) {
        return Utils.deleteUnit(paymentID, url, endpointConfig);
    }

    public ApiResponse listPayments() {
        return getPayments(StringUtils.EMPTY);
    }

    public ApiResponse listPastPayments() {
        return getPayments("history");
    }

    private ApiResponse getPayments(String endpoint) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    private ApiResponse createPF(String from, String to, String callback, KeyType keyType, String key, Integer confirmations, Long gasPrice, Integer gasLimit) {
        EthPayment ethPayment = EthPayment.createPayment(from, to, callback, keyType, key, confirmations, gasPrice, gasLimit);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig, ethPayment.toString());
    }
}
