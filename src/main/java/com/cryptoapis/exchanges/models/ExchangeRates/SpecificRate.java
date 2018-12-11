package com.cryptoapis.exchanges.models.ExchangeRates;

import com.cryptoapis.models.RawJSON;
import java.math.BigDecimal;

public class SpecificRate extends RawJSON {
    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Payload {

        private String baseAsset;
        private String quoteAsset;
        private BigDecimal weightedMedianAveragePrice;
        private BigDecimal medianPrice;
        private BigDecimal weightedAveragePrice;
        private long timestamp;

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

        public BigDecimal getWeightedMedianAveragePrice() {
            return weightedMedianAveragePrice;
        }

        public void setWeightedMedianAveragePrice(BigDecimal weightedMedianAveragePrice) {
            this.weightedMedianAveragePrice = weightedMedianAveragePrice;
        }

        public BigDecimal getMedianPrice() {
            return medianPrice;
        }

        public void setMedianPrice(BigDecimal medianPrice) {
            this.medianPrice = medianPrice;
        }

        public BigDecimal getWeightedAveragePrice() {
            return weightedAveragePrice;
        }

        public void setWeightedAveragePrice(BigDecimal weightedAveragePrice) {
            this.weightedAveragePrice = weightedAveragePrice;
        }

        public long getTimestamp() {
            return timestamp /1000;
        }

        public void setTimestamp(long time) {
            this.timestamp = time;
        }
    }
}
