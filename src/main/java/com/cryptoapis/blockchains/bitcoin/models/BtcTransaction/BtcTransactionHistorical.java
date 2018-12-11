package com.cryptoapis.blockchains.bitcoin.models.BtcTransaction;

import com.cryptoapis.models.RawJSON;

import java.util.List;

public class BtcTransactionHistorical extends RawJSON {
    private int limit;
    private int count;
    private int index;
    private List<Txs> txs;

    public static class Txs {
        private long receivedtime;
        private String id;

        public long getReceivedtime() {
            return receivedtime;
        }

        public void setReceivedtime(long receivedtime) {
            this.receivedtime = receivedtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Txs> getTxs() {
        return txs;
    }

    public void setTxs(List<Txs> txs) {
        this.txs = txs;
    }
}
