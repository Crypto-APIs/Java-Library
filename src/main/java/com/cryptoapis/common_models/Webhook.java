package com.cryptoapis.common_models;

public class Webhook extends Stringify {
    private String event;
    private String transaction;
    private int confirmations;
    private String address;
    private String url;

    private Webhook(String event, String url) {
        this.event = event;
        this.url = url;
    }

    private Webhook(String event, String url, String address) {
        this.event = event;
        this.url = url;
        this.address = address;
    }

    private Webhook(String event, String url, String transaction, String address, int confirmations) {
        this.event = event;
        this.url = url;
        if (transaction != null)
            this.transaction = transaction;
        if (address != null)
            this.address = address;
        this.confirmations = confirmations;
    }

    public static Webhook createNewBlock(String event, String url) {
        return new Webhook(event, url);
    }

    public static Webhook createConfirmed(String event, String url, String transaction, int confirmations) {
        return new Webhook(event, url, transaction, null, confirmations);
    }

    public static Webhook createAddress(String event, String url, String address, int confirmations) {
        return new Webhook(event, url, null, address, confirmations);
    }

    public static Webhook createToken(String event, String url, String address, int confirmations) {
        return new Webhook(event, url, null, address, confirmations);
    }

    public static Webhook createTXPool(String event, String url, String address) {
        return new Webhook(event, url, address);
    }
}
