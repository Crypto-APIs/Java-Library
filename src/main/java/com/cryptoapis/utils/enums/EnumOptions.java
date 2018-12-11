package com.cryptoapis.utils.enums;

public enum EnumOptions {
    ;
    public enum IncludeRawJSON {
        True,
        False
    }
    public enum QueryParams {
        index,
        limit,
        period,
        timeperiodstart,
        timeperiodend,
        timestamp,
        timestart,
        timeend
    }

    public enum BTCTxsQueryParams {
        TXSINCLUDE("txs-included"),
        DATETIMEFROM("datetime-from"),
        DATETIMETO("datetime-to")
        ;
        private final String text;

        BTCTxsQueryParams(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
