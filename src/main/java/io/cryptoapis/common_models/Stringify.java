package io.cryptoapis.common_models;

import com.google.gson.Gson;

public class Stringify {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
