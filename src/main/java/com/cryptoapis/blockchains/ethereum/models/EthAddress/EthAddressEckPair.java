package com.cryptoapis.blockchains.ethereum.models.EthAddress;

import com.cryptoapis.models.RawJSON;

public class EthAddressEckPair extends RawJSON {
    private String address;
    private String privateKey;
    private String publicKey;

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
}
