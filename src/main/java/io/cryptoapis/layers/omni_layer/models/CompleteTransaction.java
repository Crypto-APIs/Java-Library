package io.cryptoapis.layers.omni_layer.models;

import java.math.BigDecimal;

//create, sign and send
public class CompleteTransaction extends CreateTransaction {

    private String wif;

    private CompleteTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, String wif, Integer locktime) {
        super(from, to, value,fee,propertyID,data,locktime);
        this.wif = wif;
    }

    private CompleteTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String wif) {
        super(from, to, value,fee,propertyID);
        this.wif = wif;
    }

    public static CompleteTransaction createSignAndSend(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String wif, String data, Integer locktime) {
        return new CompleteTransaction(from, to, value,fee,propertyID, wif, data,locktime);
    }

    public static CompleteTransaction createSignAndSend(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String wif) {
        return new CompleteTransaction(from, to, value,fee,propertyID, wif);
    }
}
