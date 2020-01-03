package io.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import io.cryptoapis.common_models.Stringify;

import java.util.List;

public class TransactionSize extends Stringify {
    private List<TransactionSize.Inputs> inputs;
    private List<TransactionSize.Outputs> outputs;
    private Integer locktime;
    private TransactionSize.Fee fee;

    //btc
    private Boolean replaceable;
    private String data;

    public TransactionSize(List<Inputs> inputs, List<Outputs> outputs, Integer locktime) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.locktime = locktime;
    }

    public TransactionSize(List<Inputs> inputs, List<Outputs> outputs, Integer locktime, Boolean replaceable, String data) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.locktime = locktime;
        this.replaceable = replaceable;
        this.data = data;
    }

    protected TransactionSize(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, TransactionSize.Fee fee, Integer locktime, Boolean replaceable, String data) {
        this(inputs, outputs, fee, locktime);
        this.replaceable = replaceable;
        this.data = data;
    }


    protected TransactionSize(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, TransactionSize.Fee fee, Integer locktime) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.locktime = locktime;
        if (locktime != null)
            this.fee = fee;
    }

    public TransactionSize() {

    }

    public static TransactionSize transactionSize(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, Integer locktime) {
        return new TransactionSize(inputs, outputs, locktime);
    }

    public static TransactionSize transactionSizeWithFee(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, Fee fee, Integer locktime) {
        return new TransactionSize(inputs, outputs, fee, locktime);
    }

    public static TransactionSize btcTransactionSizeWithFee(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, TransactionSize.Fee fee, Integer locktime, Boolean replaceable, String data) {
        return new TransactionSize(inputs, outputs, fee, locktime, replaceable, data);
    }

    public static TransactionSize btcTransactionSize(List<TransactionSize.Inputs> inputs, List<TransactionSize.Outputs> outputs, Integer locktime, Boolean replaceable, String data) {
        return new TransactionSize(inputs, outputs, locktime, replaceable, data);
    }

    public void setInputs(List<TransactionSize.Inputs> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(List<TransactionSize.Outputs> outputs) {
        this.outputs = outputs;
    }

    public TransactionSize.Fee getFee() {
        return fee;
    }

    public void setFee(TransactionSize.Fee fee) {
        this.fee = fee;
    }


    public static class Fee extends CreateTransaction.Fee {

    }

    public static class Inputs extends CreateTransaction.Inputs {

    }

    public static class Outputs extends CreateTransaction.Outputs {

    }
}
