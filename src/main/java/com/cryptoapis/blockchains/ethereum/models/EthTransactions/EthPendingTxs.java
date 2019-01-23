package com.cryptoapis.blockchains.ethereum.models.EthTransactions;

import com.cryptoapis.models.RawJSON;

import java.util.List;

public class EthPendingTxs extends RawJSON {
    private int count;
    private List<EthTransactionDTO> pending_txs;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<EthTransactionDTO> getPending_txs() {
        return pending_txs;
    }

    public void setPending_txs(List<EthTransactionDTO> pending_txs) {
        this.pending_txs = pending_txs;
    }
}
