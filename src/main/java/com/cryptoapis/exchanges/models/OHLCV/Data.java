package com.cryptoapis.exchanges.models.OHLCV;

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
        private String exchange;
        private String eventType;
        private String assetBase;
        private String assetQuote;
        private long timePeriodStart;
        private long timePeriodEnd;
        private long timeOpen;
        private long timeClose;
        private BigDecimal priceOpen;
        private BigDecimal priceClose;
        private BigDecimal priceLow;
        private BigDecimal priceHigh;
        private BigDecimal volumeTraded;
        private int tradesCount;
        private String _id;
        private long _created;
        private long _lastModified;

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getAssetBase() {
            return assetBase;
        }

        public void setAssetBase(String assetBase) {
            this.assetBase = assetBase;
        }

        public String getAssetQuote() {
            return assetQuote;
        }

        public void setAssetQuote(String assetQuote) {
            this.assetQuote = assetQuote;
        }

        public long getTimePeriodStart() {
            return timePeriodStart;
        }

        public void setTimePeriodStart(long timePeriodStart) {
            this.timePeriodStart = timePeriodStart;
        }

        public long getTimePeriodEnd() {
            return timePeriodEnd;
        }

        public void setTimePeriodEnd(long timePeriodEnd) {
            this.timePeriodEnd = timePeriodEnd;
        }

        public long getTimeOpen() {
            return timeOpen;
        }

        public void setTimeOpen(long timeOpen) {
            this.timeOpen = timeOpen;
        }

        public long getTimeClose() {
            return timeClose;
        }

        public void setTimeClose(long timeClose) {
            this.timeClose = timeClose;
        }

        public BigDecimal getPriceOpen() {
            return priceOpen;
        }

        public void setPriceOpen(BigDecimal priceOpen) {
            this.priceOpen = priceOpen;
        }

        public BigDecimal getPriceClose() {
            return priceClose;
        }

        public void setPriceClose(BigDecimal priceClose) {
            this.priceClose = priceClose;
        }

        public BigDecimal getPriceLow() {
            return priceLow;
        }

        public void setPriceLow(BigDecimal priceLow) {
            this.priceLow = priceLow;
        }

        public BigDecimal getPriceHigh() {
            return priceHigh;
        }

        public void setPriceHigh(BigDecimal priceHigh) {
            this.priceHigh = priceHigh;
        }

        public BigDecimal getVolumeTraded() {
            return volumeTraded;
        }

        public void setVolumeTraded(BigDecimal volumeTraded) {
            this.volumeTraded = volumeTraded;
        }

        public int getTradesCount() {
            return tradesCount;
        }

        public void setTradesCount(int tradesCount) {
            this.tradesCount = tradesCount;
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
