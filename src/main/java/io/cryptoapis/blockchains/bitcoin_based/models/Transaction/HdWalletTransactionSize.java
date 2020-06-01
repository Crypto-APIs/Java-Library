package io.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import io.cryptoapis.common_models.Stringify;

import java.util.List;

public class HdWalletTransactionSize extends Stringify {
    private String walletName;
    private String password;
    private List<CreateTransaction.Inputs> inputs;
    private List<CreateTransaction.Outputs> outputs;
    private CreateTransaction.Fee fee;
    private Integer locktime;

    //btc
    private Boolean replaceable;
    private String data;

    private HdWalletTransactionSize(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                      List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                      Boolean replaceable, String data) {
        this(walletName, password, inputs, outputs, fee, locktime);
        this.replaceable = replaceable;
        this.data = data;
    }

    private HdWalletTransactionSize(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                      List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime) {
        this.walletName = walletName;
        this.password = password;
        if (inputs != null && inputs.size() > 0) {
            this.inputs = inputs;
        }

        this.outputs = outputs;

        if (fee != null) {
            this.fee = fee;
        }
        this.locktime = locktime != null ? locktime : 0;
    }

    public static HdWalletTransactionSize getHdWalletTransactionSize(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                                                        List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime) {

        return new HdWalletTransactionSize(walletName, password, inputs, outputs, fee, locktime);
    }

    public static HdWalletTransactionSize getHdWalletTransactionSizeBtc(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                                                       List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                                                       Boolean replaceable, String data ) {

        return new HdWalletTransactionSize(walletName, password, inputs, outputs,fee , locktime, replaceable, data);
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CreateTransaction.Inputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<CreateTransaction.Inputs> inputs) {
        this.inputs = inputs;
    }

    public List<CreateTransaction.Outputs> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<CreateTransaction.Outputs> outputs) {
        this.outputs = outputs;
    }

    public CreateTransaction.Fee getFee() {
        return fee;
    }

    public void setFee(CreateTransaction.Fee fee) {
        this.fee = fee;
    }

    public Integer getLocktime() {
        return locktime;
    }

    public void setLocktime(Integer locktime) {
        this.locktime = locktime;
    }

    public Boolean getReplaceable() {
        return replaceable;
    }

    public void setReplaceable(Boolean replaceable) {
        this.replaceable = replaceable;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
