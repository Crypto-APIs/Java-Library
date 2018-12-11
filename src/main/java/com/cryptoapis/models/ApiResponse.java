package com.cryptoapis.models;

import com.google.gson.Gson;

public class ApiResponse {
    private String response;
    private int statusCode;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
