package io.cryptoapis.layers.omni_layer.models;

import java.math.BigDecimal;

public class CreateHDWalletTransaction extends CreateTransaction {

    private String walletName;
    private String password;

    protected CreateHDWalletTransaction(String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        super(from, to, value, fee, propertyID);
        this.walletName = walletName;
        this.password = password;
    }

    //constructor with optional fields
    protected CreateHDWalletTransaction(String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {
        super(from, to, value, fee, propertyID, data, locktime);
        this.walletName = walletName;
        this.password = password;
    }
    //method with optional fields
    public static CreateHDWalletTransaction createHDWalletTransaction(String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID, String data, Integer locktime) {
        return new CreateHDWalletTransaction(walletName, password, from, to, value, fee, propertyID, data, locktime);
    }

    public static CreateHDWalletTransaction createHDWalletTransaction(String walletName, String password, String from, String to, BigDecimal value, BigDecimal fee, Integer propertyID) {
        return new CreateHDWalletTransaction(walletName, password, from, to, value, fee, propertyID);
    }

}
