package com.cryptoapis.blockchains.ethereum.models.EthToken;

import com.cryptoapis.models.RawJSON;
import java.math.BigDecimal;

public class EthToken extends RawJSON {
    private String name;
    private String symbol;
    private BigDecimal token;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getToken() {
        return token;
    }

    public void setToken(BigDecimal token) {
        this.token = token;
    }
}
