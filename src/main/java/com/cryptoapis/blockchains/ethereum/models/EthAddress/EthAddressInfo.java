package com.cryptoapis.blockchains.ethereum.models.EthAddress;

import com.cryptoapis.models.RawJSON;

public class EthAddressInfo extends RawJSON {
    private String chain;
    private String address;
    private long txs_count;
    private long from;
    private long to;

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTxs_count() {
        return txs_count;
    }

    public void setTxs_count(long txs_count) {
        this.txs_count = txs_count;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
