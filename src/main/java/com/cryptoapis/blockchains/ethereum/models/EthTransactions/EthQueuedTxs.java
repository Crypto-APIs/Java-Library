package com.cryptoapis.blockchains.ethereum.models.EthTransactions;

import com.cryptoapis.models.RawJSON;

import java.util.List;

public class EthQueuedTxs extends RawJSON {
    private int count;
    private List<EthTransactionDTO> queued_txs;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<EthTransactionDTO> getQueued_txs() {
        return queued_txs;
    }

    public void setQueued_txs(List<EthTransactionDTO> queued_txs) {
        this.queued_txs = queued_txs;
    }
}
