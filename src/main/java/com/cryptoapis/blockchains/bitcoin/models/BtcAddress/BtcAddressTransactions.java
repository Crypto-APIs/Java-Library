package com.cryptoapis.blockchains.bitcoin.models.BtcAddress;

import com.google.gson.Gson;

import java.util.ArrayList;

public class BtcAddressTransactions {
    private String txid;
    private String hash;
    private int version;
    private long size;
    private long vsize;
    private long locktime;
    private String time;
    private boolean addressProcessed;
    private String blockhash;
    private int blockheight;
    private int blocktime;
    private ArrayList<TxIns> txins;
    private ArrayList<TxOuts> txouts;
    private long timestamp;
    private int confirmations;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getVsize() {
        return vsize;
    }

    public void setVsize(long vsize) {
        this.vsize = vsize;
    }

    public long getLocktime() {
        return locktime;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAddressProcessed() {
        return addressProcessed;
    }

    public void setAddressProcessed(boolean addressProcessed) {
        this.addressProcessed = addressProcessed;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public int getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(int blocktime) {
        this.blocktime = blocktime;
    }

    public ArrayList<TxIns> getTxins() {
        return txins;
    }

    public void setTxins(ArrayList<TxIns> txins) {
        this.txins = txins;
    }

    public ArrayList<TxOuts> getTxouts() {
        return txouts;
    }

    public void setTxouts(ArrayList<TxOuts> txouts) {
        this.txouts = txouts;
    }

    public int getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(int blockheight) {
        this.blockheight = blockheight;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class TxIns {
        private boolean coinbase;
        private String txout;
        private int vout;
        private String address;
        private long amount;

        public boolean isCoinbase() {
            return coinbase;
        }

        public void setCoinbase(boolean coinbase) {
            this.coinbase = coinbase;
        }

        public String getTxout() {
            return txout;
        }

        public void setTxout(String txout) {
            this.txout = txout;
        }

        public int getVout() {
            return vout;
        }

        public void setVout(int vout) {
            this.vout = vout;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }
    }

    public static class TxOuts {
        private String address;
        private long amount;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
