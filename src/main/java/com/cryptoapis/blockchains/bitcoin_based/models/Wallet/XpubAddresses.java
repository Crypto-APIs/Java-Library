package com.cryptoapis.blockchains.bitcoin_based.models.Wallet;

import com.cryptoapis.common_models.Stringify;

public class XpubAddresses extends Stringify {
    private String password;
    private String xpub;
    private int from;
    private int to;

    private XpubAddresses(String password) {
        this.password = password;
    }

    private XpubAddresses(String xpub, int from, int to) {
        this.xpub = xpub;
        this.from = from;
        this.to = to;
    }

    public static XpubAddresses createExtendedKey(String password) {
        return new XpubAddresses(password);
    }

    public static XpubAddresses getXpubAddresses(String xpub, int from, int to) {
        return new XpubAddresses(xpub, from, to);
    }
}

