package com.cryptoapis.blockchains.bitcoin.models;

import com.cryptoapis.models.RawJSON;
import java.math.BigDecimal;
import java.util.List;

public class BtcBlock extends RawJSON {

    private String nexHash;
    private String previousHash;
    private List<String> tx;
    private Integer n_tx;
    private String bits;
    private String merkleRoot;
    private Integer confirmations;
    private Long version;
    private Long nonce;
    private BigDecimal difficulty;
    private String chainwork;
    private Long size;
    private String time;
    private String hash;
    private Integer height;
    private Integer timestamp;

    public String getNexHash() {
        return nexHash;
    }

    public void setNexHash(String nexHash) {
        this.nexHash = nexHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<String> getTx() {
        return tx;
    }

    public void setTx(List<String> tx) {
        this.tx = tx;
    }

    public Integer getN_tx() {
        return n_tx;
    }

    public void setN_tx(Integer n_tx) {
        this.n_tx = n_tx;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public String getChainwork() {
        return chainwork;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
