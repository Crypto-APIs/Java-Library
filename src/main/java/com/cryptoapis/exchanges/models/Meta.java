package com.cryptoapis.exchanges.models;

import com.google.gson.Gson;

public class Meta {
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}