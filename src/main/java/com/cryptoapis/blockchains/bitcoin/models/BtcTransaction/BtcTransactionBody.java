package com.cryptoapis.blockchains.bitcoin.models.BtcTransaction;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;

public class BtcTransactionBody {
    private List<Inputs> inputs;
    private List<Outputs> outputs;
    private BigDecimal fee;

    public static class Inputs extends BtcTransactionBase {

    }

    public static class Outputs extends BtcTransactionBase {

    }

    private BtcTransactionBody(List<Inputs> inputs, List<Outputs> outputs, BigDecimal fee) {
        this.fee = fee;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public BtcTransactionBody() {

    }

    public static BtcTransactionBody createTransactionBody(List<Inputs> inputs, List<Outputs> outputs, BigDecimal fee) {
        return new BtcTransactionBody(inputs, outputs, fee);
    }

    public List<Inputs> getInputs() {
        return inputs;
    }

    public List<Outputs> getOutputs() {
        return outputs;
    }

    public BigDecimal getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
