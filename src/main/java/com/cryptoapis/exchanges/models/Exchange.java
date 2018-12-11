package com.cryptoapis.exchanges.models;

import com.cryptoapis.models.RawJSON;
import java.util.ArrayList;

public class Exchange extends RawJSON {
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
        private String name;
        private String website;
        private String _id;
        private int _created;
        private int _lastModified;

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
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
    }
}
