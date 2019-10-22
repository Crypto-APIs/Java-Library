package io.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import io.cryptoapis.common_models.Stringify;

import java.math.BigDecimal;
import java.util.List;

public class CreateTransaction extends Stringify {
    private List<Inputs> inputs;
    private List<Outputs> outputs;
    private Integer locktime;
    private Fee fee;

    //btc
    private Boolean replaceable;
    private String data;

    public static class Inputs extends Base {

    }

    public static class Outputs extends Base {

    }

    protected CreateTransaction(List<Inputs> inputs, List<Outputs> outputs, CreateTransaction.Fee fee, Integer locktime, Boolean replaceable, String data) {
        this(inputs, outputs, fee, locktime);
        this.replaceable = replaceable;
        this.data = data;
    }


    protected CreateTransaction(List<Inputs> inputs, List<Outputs> outputs, Fee fee, Integer locktime) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.locktime = locktime;
        if (locktime != null)
            this.fee = fee;
    }

    public CreateTransaction() {

    }

    public static CreateTransaction createTx(List<Inputs> inputs, List<Outputs> outputs, Fee fee, Integer locktime) {
        return new CreateTransaction(inputs, outputs, fee, locktime);
    }

    public static CreateTransaction btcCreateTransaction(List<Inputs> inputs, List<Outputs> outputs, CreateTransaction.Fee fee, Integer locktime, Boolean replaceable, String data) {
        return new CreateTransaction(inputs, outputs, fee, locktime, replaceable, data);
    }

    public List<Inputs> getInputs() {
        return inputs;
    }

    public List<Outputs> getOutputs() {
        return outputs;
    }

    public void setInputs(List<Inputs> inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(List<Outputs> outputs) {
        this.outputs = outputs;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public static class Base {

        private String address;
        private BigDecimal value;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = BigDecimal.valueOf(value);
        }
    }

    public static class Fee {

        private String address;
        private BigDecimal value;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public BigDecimal getValue() {
            return  value;
        }

        public void setValue(Double value) {
            this.value = BigDecimal.valueOf(value);
        }
    }

}
