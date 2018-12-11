package com.cryptoapis.exchanges.models.Trades;

import com.cryptoapis.models.RawJSON;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Data extends RawJSON {
    private ArrayList<Payload> payload;

    public ArrayList<Payload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Payload> payload) {
        this.payload = payload;
    }

    public static class Payload {
        private String exchangeId;
        private String baseAsset;
        private String quoteAsset;
        private long eventTime;
        private String exchangeSequenceId;
        private String tradeType;
        private BigDecimal price;
        private BigDecimal amount;
        private String direction;

        public String getExchangeId() {
            return exchangeId;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
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

        public long getEventTime() {
            return eventTime;
        }

        public void setEventTime(long eventTime) {
            this.eventTime = eventTime;
        }

        public String getExchangeSequenceId() {
            return exchangeSequenceId;
        }

        public void setExchangeSequenceId(String exchangeSequenceId) {
            this.exchangeSequenceId = exchangeSequenceId;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
