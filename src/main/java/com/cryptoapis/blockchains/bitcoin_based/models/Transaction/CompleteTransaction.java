package com.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import com.cryptoapis.common_models.Stringify;

import java.util.List;

//create, sign and send
public class CompleteTransaction extends Stringify {
    private CreateTransaction createTx;
    private List<String> wifs;

    private CompleteTransaction(CreateTransaction createTx, List<String> wifs) {
        this.createTx = createTx;
        this.wifs = wifs;
    }

    public static CompleteTransaction createSignAndSend(CreateTransaction createTx, List<String> wifs) {
        return new CompleteTransaction(createTx, wifs);
    }
}
