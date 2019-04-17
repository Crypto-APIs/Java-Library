package com.cryptoapis.common_models;

import com.google.gson.Gson;

public class ApiError {
    private Meta meta;
    private int statusCode;

    public static class Meta {
        private Error error;

        public static class Error {
            private String message;
            private int code;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return new Gson().toJson(this);
            }
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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
