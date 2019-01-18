package com.cryptoapis.blockchains.ethereum.models.EthToken;

import com.cryptoapis.models.RawJSON;

public class EthTokenAddress extends RawJSON {
    private String name;
    private String symbol;
    private String contract;
    private String type;

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

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
