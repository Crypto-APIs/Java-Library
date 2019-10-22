package io.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import io.cryptoapis.common_models.Stringify;

public class RefundTransaction extends Stringify {
    private String txid;
    private String wif;
    private String fee;

    public RefundTransaction(String txid, String wif, String fee) {
        this.txid = txid;
        this.wif = wif;
        if (fee != null)
            this.fee = fee;
    }

    public static RefundTransaction refundTx(String txid, String wif, String fee) {
        return new RefundTransaction(txid, wif, fee);
    }
}
