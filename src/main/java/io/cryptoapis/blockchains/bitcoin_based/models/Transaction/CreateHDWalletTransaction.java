package io.cryptoapis.blockchains.bitcoin_based.models.Transaction;

import io.cryptoapis.common_models.Stringify;

import java.util.List;

public class CreateHDWalletTransaction extends Stringify {
    private String walletName;
    private String password;
    private List<CreateTransaction.Inputs> inputs;
    private List<CreateTransaction.Outputs> outputs;
    private CreateTransaction.Fee fee;
    private Integer locktime;

    //btc
    private Boolean replaceable;
    private String data;

    private CreateHDWalletTransaction(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                      List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                      Boolean replaceable, String data) {
        this(walletName, password, inputs, outputs, fee, locktime);
        this.replaceable = replaceable;
        this.data = data;
    }

    private CreateHDWalletTransaction(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                      List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime) {
        this.walletName = walletName;
        this.password = password;
        if (inputs != null && inputs.size() > 0) {
            this.inputs = inputs;
        }

        this.outputs = outputs;
        this.fee = fee;
        this.locktime = locktime != null ? locktime : 0;
    }

    public static CreateHDWalletTransaction create(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                                   List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime) {
        return new CreateHDWalletTransaction(walletName, password, inputs, outputs, fee, locktime);
    }

    public static CreateHDWalletTransaction createBtc(String walletName, String password, List<CreateTransaction.Inputs> inputs,
                                                      List<CreateTransaction.Outputs> outputs, CreateTransaction.Fee fee, Integer locktime,
                                                      Boolean replaceable, String data) {
        return new CreateHDWalletTransaction(walletName, password, inputs, outputs, fee, locktime, replaceable, data);
    }
}
