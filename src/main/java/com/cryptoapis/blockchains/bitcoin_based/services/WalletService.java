package com.cryptoapis.blockchains.bitcoin_based.services;

import com.cryptoapis.blockchains.bitcoin_based.models.Wallet.HDWallet;
import com.cryptoapis.blockchains.bitcoin_based.models.Wallet.Wallet;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.common_models.ApiResponse;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class WalletService extends AbstractServicesConfig {

    private static final String PATH = "/{0}/bc/{1}/{2}/wallets/{3}";

    public WalletService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    private static final String HD = "hd";

    @Override
    protected String getPath() {
        return PATH;
    }

    public ApiResponse createWallet(List<String> addresses, String walletName) {
        Wallet wallet = Wallet.createWallet(addresses, walletName);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig,
                wallet.toString());
    }

    public ApiResponse createHDWallet(String walletName, int addressCount, String password) {
        HDWallet hdWallet = HDWallet.createHDWallet(walletName, addressCount, password);

        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, HD), HttpsRequestsEnum.POST.name(), endpointConfig,
                hdWallet.toString());
    }

    public ApiResponse listWallets() {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig,
                null);
    }

    public ApiResponse listHDWallets() {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, HD), HttpsRequestsEnum.GET.name(), endpointConfig,
                null);
    }

    public ApiResponse getWallet(String walletName) {
        return getWallet(walletName, false);
    }

    public ApiResponse getHDWallet(String hdWalletName) {
        return getWallet(hdWalletName, true);
    }

    public ApiResponse addAddressToWallet(List<String> addresses, String walletName) {
        Wallet wallet = Wallet.createWallet(addresses, walletName);

        String endpoint = String.format("%s/addresses", walletName);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                wallet.toString());
    }

    public ApiResponse generateAddressWallet(String walletName) {
        return generateAddress(walletName, false, StringUtils.EMPTY);
    }

    public ApiResponse generateAddressHDWallet(String hdWalletName, int addressCount, String password) {
        HDWallet hdWallet = HDWallet.generateAddress(addressCount, password);

        return generateAddress(hdWalletName, true, hdWallet.toString());
    }

    public ApiResponse removeAddress(String walletName, String address) {
        String endpoint = String.format("%s/address/%s", walletName, address);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.DELETE.name(), endpointConfig,
                null);
    }

    public ApiResponse deleteWallet(String wallletName) {
        return deleteWallets(wallletName);
    }

    public ApiResponse deleteHDWallet(String hdWallletName) {
        String endpoint = String.format("%s/%s", HD, hdWallletName);
        return deleteWallets(endpoint);
    }

    private ApiResponse getWallet(String walletName, boolean isHD) {
        String endpoint = isHD ? String.format("%s/%s", HD, walletName) : String.format("%s", walletName);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.GET.name(), endpointConfig,
                null);
    }

    private ApiResponse generateAddress(String walletName, boolean isHD, String body) {
        final String generateEndpoint = "addresses/generate";

        String endpoint = isHD ? String.format("%s/%s/%s", HD, walletName, generateEndpoint) : String.format("%s/%s", walletName, generateEndpoint);
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                body);
    }

    private ApiResponse deleteWallets(String path) {
        return WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, path), HttpsRequestsEnum.DELETE.name(), endpointConfig,
                null);
    }
}
