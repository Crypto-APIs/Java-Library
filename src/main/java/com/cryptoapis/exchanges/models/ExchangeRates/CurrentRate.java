package com.cryptoapis.exchanges.models.ExchangeRates;

import com.cryptoapis.exchanges.models.Meta;
import com.cryptoapis.models.RawJSON;
import java.util.ArrayList;

public class CurrentRate extends RawJSON {
    private Meta meta;
    private ArrayList<SpecificRate.Payload> payload;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<SpecificRate.Payload> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<SpecificRate.Payload> payload) {
        this.payload = payload;
    }
}
