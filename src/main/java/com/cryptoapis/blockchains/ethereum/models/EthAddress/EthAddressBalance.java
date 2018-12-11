package com.cryptoapis.blockchains.ethereum.models.EthAddress;

import com.cryptoapis.models.RawJSON;

public class EthAddressBalance extends RawJSON {
    private String chain;
    private String address;
    private String balance;

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
