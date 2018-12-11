package com.cryptoapis.exchanges.models;

import com.cryptoapis.models.RawJSON;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Symbol extends RawJSON {
    private Meta meta;
    private ArrayList<Payload> payload;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Payload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Payload> payload) {
        this.payload = payload;
    }

    public static class Payload {
        private String exchangeId;
        private String exchangeSymbol;
        private String baseAsset;
        private String quoteAsset;
        private String tradeType;
        private String _id;
        private int _created;
        private int _lastModified;

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }

        public String getExchangeSymbol() {
            return exchangeSymbol;
        }

        public void setExchangeSymbol(String exchangeSymbol) {
            this.exchangeSymbol = exchangeSymbol;
        }

        public String getBaseAsset() {
            return baseAsset;
        }

        public void setBaseAsset(String baseAsset) {
            this.baseAsset = baseAsset;
        }

        public String getQuoteAsset() {
            return quoteAsset;
        }

        public void setQuoteAsset(String quoteAsset) {
            this.quoteAsset = quoteAsset;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int get_created() {
            return _created;
        }

        public void set_created(int _created) {
            this._created = _created;
        }

        public int get_lastModified() {
            return _lastModified;
        }

        public void set_lastModified(int _lastModified) {
            this._lastModified = _lastModified;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
