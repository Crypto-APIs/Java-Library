package com.cryptoapis.blockchains.bitcoin.models.BtcWallet;

import com.cryptoapis.models.RawJSON;
import java.util.List;

public class BtcWallet extends RawJSON {
    private List<String> addresses;
    private String walletName;
    private boolean hd;

    private BtcWallet(List<String> addresses, String walletName) {
        this.addresses = addresses;
        this.walletName = walletName;
    }

    public static BtcWallet createBtcWallet(List<String> addresses, String walletName) {
        return new BtcWallet(addresses, walletName);
    }

    public BtcWallet() {}

    public List<String> getAddresses() {
        return addresses;
    }

    public String getWalletName() {
        return walletName;
    }

    public boolean isHd() {
        return hd;
    }

    public void setHd(boolean hd) {
        this.hd = hd;
    }
}
