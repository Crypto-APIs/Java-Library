package com.cryptoapis.blockchains.bitcoin.models.BtcAddress;

import com.cryptoapis.models.RawJSON;

public class BtcAddress extends RawJSON {
    private String address;
    private String privateKey;
    private String publicKey;
    private String wif;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getWif() {
        return wif;
    }

    public void setWif(String wif) {
        this.wif = wif;
    }
}
