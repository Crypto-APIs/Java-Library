package com.cryptoapis.blockchains.bitcoin_based.models;

import com.cryptoapis.common_models.Stringify;

public class Payment extends Stringify {
    private String from;
    private String to;
    private String callback;
    private String wallet;
    private String password;
    private Integer confirmations;
    private Double fee;

    private Payment(String from, String to, String callback, String walletName, String password, Integer confirmations, Double fee) {
        this.from = from;
        this.to = to;
        this.callback = callback;
        this.wallet = walletName;
        this.password = password;

        this.confirmations = confirmations != null ? confirmations : 0;
        if (fee != null) {
            this.fee = fee;
        }

    }

    public static Payment createPayment(String from, String to, String callback, String walletName, String password, Integer confirmations, Double fee) {
        return new Payment(from, to, callback, walletName, password, confirmations, fee);
    }

}
