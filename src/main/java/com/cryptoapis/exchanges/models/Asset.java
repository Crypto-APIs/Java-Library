package com.cryptoapis.exchanges.models;

import com.cryptoapis.models.RawJSON;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Asset extends RawJSON {
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
        private String assetId;
        private String originalSymbol;
        private String name;
        private String slug;
        private boolean cryptoType;
        private long supply;
        private long marketCap;
        private BigDecimal price;
        private long volume;
        private BigDecimal change;
        private String _id;
        private int _created;
        private int _lastModified;

        public String getAssetId() {
            return assetId;
        }

        public void setAssetId(String assetId) {
            this.assetId = assetId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getOriginalSymbol() {
            return originalSymbol;
        }

        public void setOriginalSymbol(String originalSymbol) {
            this.originalSymbol = originalSymbol;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public boolean isCryptoType() {
            return cryptoType;
        }

        public void setCryptoType(boolean cryptoType) {
            this.cryptoType = cryptoType;
        }

        public long getSupply() {
            return supply;
        }

        public void setSupply(long supply) {
            this.supply = supply;
        }

        public long getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(long marketCap) {
            this.marketCap = marketCap;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }

        public BigDecimal getChange() {
            return change;
        }

        public void setChange(BigDecimal change) {
            this.change = change;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
