package com.cryptoapis.blockchains.bitcoin.models.BtcTransaction;

import java.math.BigDecimal;

public class BtcTransactionBase {
    private String address;
    private BigDecimal value;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
