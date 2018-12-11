package com.cryptoapis.blockchains.bitcoin.models.BtcWallet;

import com.cryptoapis.models.RawJSON;

import java.util.ArrayList;

public class BtcHDWallet extends RawJSON {
    private String walletName;
    private int addressCount;
    private String password;

    private ArrayList<Address> addresses;
    private boolean hd;

    private BtcHDWallet(String walletName, int addressCount, String password) {
        this.walletName = walletName;
        this.addressCount = addressCount;
        this.password = password;
    }

    public static BtcHDWallet createHDWallet(String walletName, int addressCount, String password) {
        return new BtcHDWallet(walletName, addressCount, password);
    }

    public BtcHDWallet (){}

    public static class Address {
        private String path;
        private String address;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public String getWalletName() {
        return walletName;
    }

    public int getAddressCount() {
        return addressCount;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isHd() {
        return hd;
    }

    public void setHd(boolean hd) {
        this.hd = hd;
    }
}
