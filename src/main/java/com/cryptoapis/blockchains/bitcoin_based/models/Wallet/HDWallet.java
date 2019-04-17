package com.cryptoapis.blockchains.bitcoin_based.models.Wallet;

import com.cryptoapis.common_models.Stringify;

public class HDWallet extends Stringify {
    private String walletName;
    private int addressCount;
    private String password;

    private HDWallet(String walletName, int addressCount, String password) {
        this.walletName = walletName;
        this.addressCount = addressCount;
        this.password = password;
    }

    private HDWallet(int addressCount, String password) {
        this.addressCount = addressCount;
        this.password = password;
    }


    public static HDWallet createHDWallet(String walletName, int addressCount, String password) {
        return new HDWallet(walletName, addressCount, password);
    }

    public static HDWallet generateAddress(int addressCount, String password) {
        return new HDWallet(addressCount, password);
    }
}
