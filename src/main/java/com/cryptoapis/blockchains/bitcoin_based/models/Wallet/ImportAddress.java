package com.cryptoapis.blockchains.bitcoin_based.models.Wallet;

import com.cryptoapis.common_models.Stringify;

public class ImportAddress extends Stringify {

    private String walletName;
    private String privateKey;
    private String password;
    private String address;

    private ImportAddress(String walletName, String privateKey, String password, String address) {
        this.walletName = walletName;
        this.privateKey = privateKey;
        this.password = password;
        this.address = address;
    }

    public static ImportAddress importAddressAsWallet(String walletName, String privateKey, String password, String address) {
        return new ImportAddress(walletName, privateKey, password, address);
    }
}
