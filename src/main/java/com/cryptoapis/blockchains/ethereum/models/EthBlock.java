package com.cryptoapis.blockchains.ethereum.models;

import com.cryptoapis.models.RawJSON;
import java.math.BigInteger;
import java.util.List;

public class EthBlock extends RawJSON {
    private String chain;

    private BigInteger height;

    private String hash;

    private String parent_hash;

    private String sha3Uncles;

    private BigInteger gas_used;

    private BigInteger total;

    private String size;

    private BigInteger timestamp;

    private String date;

    private int transactions;

    private BigInteger difficulty;

    private String total_difficulty;

    private BigInteger gas_limit;

    private String nonce;

    private String mined_by;

    private List<String> uncles;

    private String extra_data;

    private BigInteger confirmations;

    private BigInteger total_fees;

    public String getChain() {
        return chain;
    }

    public BigInteger getHeight() {
        return height;
    }

    public String getHash() {
        return hash;
    }

    public String getParent_hash() {
        return parent_hash;
    }

    public String getSha3Uncles() {
        return sha3Uncles;
    }

    public BigInteger getGas_used() {
        return gas_used;
    }

    public BigInteger getTotal() {
        return total;
    }

    public String getSize() {
        return size;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public int getTransactions() {
        return transactions;
    }

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public String getTotal_difficulty() {
        return total_difficulty;
    }

    public BigInteger getGas_limit() {
        return gas_limit;
    }

    public String getNonce() {
        return nonce;
    }

    public String getMined_by() {
        return mined_by;
    }

    public List<String> getUncles() {
        return uncles;
    }

    public String getExtra_data() {
        return extra_data;
    }

    public BigInteger getConfirmations() {
        return confirmations;
    }

    public BigInteger getTotal_fees() {
        return total_fees;
    }
}
