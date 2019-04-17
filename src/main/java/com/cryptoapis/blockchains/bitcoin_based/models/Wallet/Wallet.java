package com.cryptoapis.blockchains.bitcoin_based.models.Wallet;

import com.cryptoapis.common_models.Stringify;
import java.util.List;

public class Wallet extends Stringify {
    private List<String> addresses;
    private String walletName;

    private Wallet(List<String> addresses, String walletName) {
        this.addresses = addresses;
        this.walletName = walletName;
    }

    public static Wallet createWallet(List<String> addresses, String walletName) {
        return new Wallet(addresses, walletName);
    }

}
