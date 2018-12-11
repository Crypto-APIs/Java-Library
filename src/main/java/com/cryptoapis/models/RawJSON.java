package com.cryptoapis.models;

import com.google.gson.Gson;

public class RawJSON {
    private String rawJSON;

    public String getRawJSON() {
        return rawJSON;
    }

    public void setRawJSON(String rawJSON) {
        this.rawJSON = rawJSON;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
