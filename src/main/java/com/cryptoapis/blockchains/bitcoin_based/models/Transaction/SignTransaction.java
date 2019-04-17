package com.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import com.cryptoapis.common_models.Stringify;

import java.util.List;

public class SignTransaction extends Stringify {
    private String hex;
    private List<String> wifs;

    private SignTransaction(String hex, List<String> wifs) {
        this.hex = hex;
        this.wifs = wifs;
    }

    public static SignTransaction sign(String hex, List<String> wifs) {
        return new SignTransaction(hex, wifs);
    }
}
