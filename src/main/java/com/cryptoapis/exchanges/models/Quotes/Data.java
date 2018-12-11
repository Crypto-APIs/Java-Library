package com.cryptoapis.exchanges.models.Quotes;

import com.cryptoapis.exchanges.models.Meta;
import com.cryptoapis.models.RawJSON;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Data extends RawJSON {
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
        private String exchangeType;
        private String baseAssetId;
        private String quoteAssetId;
        private String type;
        private BigDecimal price;
        private BigDecimal size;
        private String _id;
        private long _created;
        private long _lastModified;

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }

        public String getExchangeType() {
            return exchangeType;
        }

        public void setExchangeType(String exchangeType) {
            this.exchangeType = exchangeType;
        }

        public String getBaseAssetId() {
            return baseAssetId;
        }

        public void setBaseAssetId(String baseAssetId) {
            this.baseAssetId = baseAssetId;
        }

        public String getQuoteAssetId() {
            return quoteAssetId;
        }

        public void setQuoteAssetId(String quoteAssetId) {
            this.quoteAssetId = quoteAssetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getSize() {
            return size;
        }

        public void setSize(BigDecimal size) {
            this.size = size;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public long get_created() {
            return _created;
        }

        public void set_created(long _created) {
            this._created = _created;
        }

        public long get_lastModified() {
            return _lastModified;
        }

        public void set_lastModified(long _lastModified) {
            this._lastModified = _lastModified;
        }
    }
}
