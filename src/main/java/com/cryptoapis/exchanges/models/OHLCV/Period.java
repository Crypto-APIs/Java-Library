package com.cryptoapis.exchanges.models.OHLCV;

import com.cryptoapis.exchanges.models.Meta;
import com.cryptoapis.models.RawJSON;
import java.util.ArrayList;

public class Period extends RawJSON {
    private Meta meta;
    private ArrayList<Payload>  payload;
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
        private String period;
        private long lengthInSeconds;
        private Integer lengthInMonths;
        private int unitCount;
        private String unitName;
        private String displayName;

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public long getLengthInSeconds() {
            return lengthInSeconds;
        }

        public void setLengthInSeconds(long lengthInSeconds) {
            this.lengthInSeconds = lengthInSeconds;
        }

        public Integer getLengthInMonths() {
            return lengthInMonths;
        }

        public void setLengthInMonths(Integer lengthInMonths) {
            this.lengthInMonths = lengthInMonths;
        }

        public int getUnitCount() {
            return unitCount;
        }

        public void setUnitCount(int unitCount) {
            this.unitCount = unitCount;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }
}
