package com.cryptoapis.blockchains.ethereum.models;

import com.cryptoapis.models.RawJSON;
import java.math.BigInteger;

public class EthBlockchain extends RawJSON {
    private String timestamp;

    private String chain;

    private BigInteger height;

    private String difficulty;

    private String bestBlockHash;

    private boolean synced;

    private BigInteger txs_count;

    public String getTimestamp() {
        return timestamp;
    }

    public String getChain() {
        return chain;
    }

    public BigInteger getHeight() {
        return height;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getBestBlockHash() {
        return bestBlockHash;
    }

    public boolean isSynced() {
        return synced;
    }

    public BigInteger getTxs_count() {
        return txs_count;
    }
}
