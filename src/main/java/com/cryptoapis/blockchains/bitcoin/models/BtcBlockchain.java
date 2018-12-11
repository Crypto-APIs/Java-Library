package com.cryptoapis.blockchains.bitcoin.models;


import com.cryptoapis.models.RawJSON;
import java.math.BigDecimal;

public class BtcBlockchain extends RawJSON {

    private BigDecimal difficulty;
    private Integer headers;
    private String chain;
    private String chainWork;
    private Long mediantime;
    private Integer blocks;
    private String bestBlockHash;
    private String currency;
    private Long transactions;
    private BigDecimal verificationProgress;

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getHeaders() {
        return headers;
    }

    public void setHeaders(Integer headers) {
        this.headers = headers;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getChainWork() {
        return chainWork;
    }

    public void setChainWork(String chainWork) {
        this.chainWork = chainWork;
    }

    public Long getMediantime() {
        return mediantime;
    }

    public void setMediantime(Long mediantime) {
        this.mediantime = mediantime;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public String getBestBlockHash() {
        return bestBlockHash;
    }

    public void setBestBlockHash(String bestBlockHash) {
        this.bestBlockHash = bestBlockHash;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getTransactions() {
        return transactions;
    }

    public void setTransactions(Long transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getVerificationProgress() {
        return verificationProgress;
    }

    public void setVerificationProgress(BigDecimal verificationProgress) {
        this.verificationProgress = verificationProgress;
    }
}
