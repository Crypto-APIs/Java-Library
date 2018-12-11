package com.cryptoapis.blockchains.bitcoin.services;

import com.cryptoapis.blockchains.bitcoin.models.BtcWallet.BtcHDWallet;
import com.cryptoapis.blockchains.bitcoin.models.BtcWallet.BtcWallet;
import com.cryptoapis.abstractServices.AbstractServicesConfig;
import com.cryptoapis.models.ApiError;
import com.cryptoapis.models.ApiResponse;
import com.cryptoapis.utils.Utils;
import com.cryptoapis.utils.config.EndpointConfig;
import com.cryptoapis.utils.enums.HttpsRequestsEnum;
import com.cryptoapis.utils.rest.WebServices;
import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class BtcWalletService extends AbstractServicesConfig {

    private static final String PATH = "/{0}/bc/{1}/{2}/wallets/{3}";
    protected Logger logger = Logger.getLogger(BtcWalletService.class.getName());

    public BtcWalletService(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    public Pair<BtcWallet, ApiError> createWallet(List<String> addresses, String walletName) {
        BtcWallet btcWallet = BtcWallet.createBtcWallet(addresses, walletName);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.POST.name(), endpointConfig,
                btcWallet.toString());
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcWallet.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<BtcHDWallet, ApiError> createHDWallet(String walletName, int addressCount, String password) {
        BtcHDWallet btcHDWallet = BtcHDWallet.createHDWallet(walletName, addressCount, password);

        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "hd"), HttpsRequestsEnum.POST.name(), endpointConfig,
                btcHDWallet.toString());

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcHDWallet.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> listWallets() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, StringUtils.EMPTY), HttpsRequestsEnum.GET.name(), endpointConfig,
                null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> listHDWallets() {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, "hd"), HttpsRequestsEnum.GET.name(), endpointConfig,
                null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<BtcWallet, ApiError> getWallet(String walletName) {
        ApiError error = checkWalletName(walletName);
        if (error != null) {
            return new Pair<>(null, error);
        }

        Pair<String, ApiError> pair = checkRequestData(walletName, HttpsRequestsEnum.GET.name());
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), BtcWallet.class, endpointConfig), null);
    }

    public Pair<BtcHDWallet, ApiError> getHDWallet(String hdWalletName) {
        ApiError error = checkWalletName(hdWalletName);
        if (error != null) {
            return new Pair<>(null, error);
        }

        Pair<String, ApiError> pair = checkRequestData(hdWalletName, "hd/", HttpsRequestsEnum.GET.name());
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), BtcHDWallet.class, endpointConfig), null);
    }

    public Pair<BtcWallet, ApiError> addAddressToWallet(List<String> addresses, String walletName) {
        BtcWallet btcWallet = BtcWallet.createBtcWallet(addresses, walletName);

        String endpoint = String.format("%s/addresses", walletName);
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.POST.name(), endpointConfig,
                btcWallet.toString());

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcWallet.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<BtcWallet, ApiError> generateAddressBtcWallet(String walletName) {

        Pair<String, ApiError> pair = checkRequestData(walletName, StringUtils.EMPTY, "/addresses/generate", HttpsRequestsEnum.POST.name(), StringUtils.EMPTY);
        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), BtcWallet.class, endpointConfig), null);
    }

    public Pair<BtcHDWallet, ApiError> generateAddressBtcHDWallet(String hdWalletName, int addressCount, String password) {

        BtcHDWallet btcHDWallet = BtcHDWallet.createHDWallet(hdWalletName, addressCount, password);

        Pair<String, ApiError> pair = checkRequestData(hdWalletName, "hd/", "/addresses/generate", HttpsRequestsEnum.POST.name(), btcHDWallet.toString());

        if (pair.getValue() != null) {
            return new Pair<>(null, pair.getValue());
        }

        return new Pair<>(Utils.convertToCustomClass(pair.getKey(), BtcHDWallet.class, endpointConfig), null);
    }

    public Pair<BtcWallet, ApiError> removeAddress(String walletName, String address) {
        String endpoint = String.format("%s/address/%s", walletName, address);
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), HttpsRequestsEnum.DELETE.name(), endpointConfig,
                null);

        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(Utils.convertToCustomClass(res.getResponse(), BtcWallet.class, endpointConfig), null);
        }
        return new Pair<>(null, apiError);
    }

    public Pair<String, ApiError> deleteBtcWallet(String wallletName) {
        return deleteWallet(wallletName);
    }

    public Pair<String, ApiError> deleteHDBtcWallet(String hdWallletName) {
        String endpoint = String.format("hd/%s", hdWallletName);
        return deleteWallet(endpoint);
    }

    private Pair<String, ApiError> checkRequestData(String walletName, String httpRequestType) {
        return checkRequestData(walletName, StringUtils.EMPTY, httpRequestType);
    }

    private Pair<String, ApiError> checkRequestData(String walletName, String hd, String httpRequestType) {
        return checkRequestData(walletName, hd, StringUtils.EMPTY, httpRequestType, StringUtils.EMPTY);
    }

    private Pair<String, ApiError> checkRequestData(String walletName, String hd, String path, String httpRequestType, String body) {
        String endpoint = String.format("%s%s%s", hd, walletName, path);
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, endpoint), httpRequestType, endpointConfig,
                body);

        return sendRequest(res);
    }

    private Pair<String, ApiError> deleteWallet(String path) {
        ApiResponse res = WebServices.httpsRequest(WebServices.formatUrl(url, endpointConfig, path), HttpsRequestsEnum.DELETE.name(), endpointConfig,
                null);

        return sendRequest(res);
    }

    private Pair<String, ApiError> sendRequest(ApiResponse res) {
        ApiError apiError = Utils.checkForError(res);
        if (apiError == null && res != null) {
            return new Pair<>(res.getResponse(), null);
        }
        return new Pair<>(null, apiError);
    }

    private ApiError checkWalletName(String walletName) {
        if (walletName == null || walletName.isEmpty()) {
            return   Utils.convertToCustomClass(Utils.setError("walletName is too short", 2108), ApiError.class, null);

        }

        return null;
    }
}
