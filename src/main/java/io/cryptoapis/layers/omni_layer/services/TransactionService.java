package io.cryptoapis.layers.omni_layer.services;

import io.cryptoapis.abstractServices.AbstractServicesConfig;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.layers.omni_layer.models.*;
import io.cryptoapis.utils.config.EndpointConfig;
import io.cryptoapis.utils.enums.HttpsRequestsEnum;
import io.cryptoapis.utils.rest.WebServices;

import java.math.BigDecimal;

public class TransactionService extends AbstractServicesConfig {

    private static final String PATH = "/{0}/bc/{1}/omni/{2}/txs{3}";
    private static final String ABSTRACT_ENDPOINT = "/%s/%s?index=%d&limit=%d";
    private static final String EMPTY_STRING = "";

    protected TransactionService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    //only creates the transaction
    public ApiResponse createTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        CreateTransaction createTransaction = CreateTransaction.createTransaction(from, to, value, fee, propertyID);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/create"), HttpsRequestsEnum.POST.name(),
                endpointConfig, createTransaction.toString());
    }

    //only creates the transaction with optional fields
    public ApiResponse createTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {
        CreateTransaction createTransaction = CreateTransaction.createTransaction(from, to, value, fee, propertyID, data, locktime);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/create"), HttpsRequestsEnum.POST.name(),
                endpointConfig, createTransaction.toString());
    }

    public ApiResponse signTransaction(String hex, String wif) {
        SignTransaction signTransaction = SignTransaction.signTransaction(hex, wif);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/sign"), HttpsRequestsEnum.POST.name(),
                endpointConfig, signTransaction.toString());
    }

    public ApiResponse sendTransaction(String hex) {
        SendTransaction sendTransaction = SendTransaction.sendTransaction(hex);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/send"), HttpsRequestsEnum.POST.name(),
                endpointConfig, sendTransaction.toString());
    }

    //create, sign and send txs
    public ApiResponse createSignAndSendTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String wif) {
        CompleteTransaction completeTransaction = CompleteTransaction.createSignAndSend(from, to, value, fee, propertyID, wif);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/new"), HttpsRequestsEnum.POST.name(),
                endpointConfig, completeTransaction.toString());
    }

    //create, sign and send txs with optional fields
    public ApiResponse createSignAndSendTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String wif, String data, Integer locktime) {
        CompleteTransaction completeTransaction = CompleteTransaction.createSignAndSend(from, to, value, fee, propertyID, wif, data, locktime);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/new"), HttpsRequestsEnum.POST.name(),
                endpointConfig, completeTransaction.toString());
    }

    //create hd wallet txs
    public ApiResponse createHDWalletTransaction(String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        CreateHDWalletTransaction createHDWalletTransaction = CreateHDWalletTransaction.createHDWalletTransaction(walletName, password, from, to, value, fee, propertyID);

        System.out.println(WebServices.formatUrl(url, endpointConfig, "/hdwallet"));
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/hdwallet"), HttpsRequestsEnum.POST.name(),
                endpointConfig, createHDWalletTransaction.toString());
    }

    //create hd wallet txs with optional fields
    public ApiResponse createHDWalletTransaction(
            String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {

        CreateHDWalletTransaction createHDWalletTransaction = CreateHDWalletTransaction.createHDWalletTransaction(
                walletName, password, from, to, value, fee, propertyID, data, locktime);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "/hdwallet"), HttpsRequestsEnum.POST.name(),
                endpointConfig, createHDWalletTransaction.toString());
    }

    public ApiResponse getTransactionById(String txid) {
        String endpoint = String.format("/txid/%s", txid);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    public ApiResponse getTransactionsByBlockHeight(Integer block) {

        return createGetTransactionsByPropertyEndpoint(block, null, null, "block");
    }

    //get txs by block with optional query params
    public ApiResponse getTransactionsByBlockHeight(Integer block, Integer index, Integer limit) {

        return createGetTransactionsByPropertyEndpoint(block, index, limit, "block");
    }

    public ApiResponse getTransactionsByPropertyId(Integer propertyId) {

        return createGetTransactionsByPropertyEndpoint(propertyId, null, null, "propertyid");
    }

    //get txs by property id with optional query params
    public ApiResponse getTransactionsByPropertyId(Integer propertyId, Integer index, Integer limit) {

        return createGetTransactionsByPropertyEndpoint(propertyId, index, limit, "propertyid");
    }

    public ApiResponse getUnconfirmedTransactions() {

        return createGetTransactionsByPropertyEndpoint(null, null, null, "unconfirmed");
    }

    //get unconfirmed txs with optional query params
    public ApiResponse getUnconfirmedTransactions(Integer index, Integer limit) {

        return createGetTransactionsByPropertyEndpoint(null, index, limit, "unconfirmed");
    }

    private ApiResponse createGetTransactionsByPropertyEndpoint(Integer property, Integer index, Integer limit, String endpoint) {
        index = isValid(index) ? index : 0;
        limit = isValid(limit) ? limit : 50;
        String endpointProperty = property == null ? EMPTY_STRING : String.valueOf(property);

        endpoint = String.format(ABSTRACT_ENDPOINT, endpoint, endpointProperty, index, limit);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig, null);
    }

    private boolean isValid(Integer param) {
        return ((param != null) && (param > 0));
    }
}
