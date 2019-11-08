package io.cryptoapis.layers.omni_layer.models;

import io.cryptoapis.common_models.Stringify;

import java.math.BigDecimal;

public class CreateTransaction extends Stringify {

    private String from;
    private String to;
    private BigDecimal value;
    private BigDecimal fee;
    private Integer propertyID;

    //optional fields
    private String data;
    private Integer locktime;

    protected CreateTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.fee = fee;
        this.propertyID = propertyID;
    }

    protected CreateTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {
        this(from, to, value, fee, propertyID);
        this.data = data;
        this.locktime = locktime != null ? locktime : 0;
    }

    public static CreateTransaction createTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {
        return new CreateTransaction(from, to, value,fee, propertyID, data, locktime);
    }

    public static CreateTransaction createTransaction(String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        return new CreateTransaction(from, to, value, fee, propertyID);
    }
}
