package com.cryptoapis.blockchains.ethereum.models.EthPayment;

import com.cryptoapis.models.RawJSON;

public class EthPaymentHistory extends RawJSON {
    private String paymentID;
    private String uuid;
    private String status;
    private String block_height;
    private String transaction;

    public String getPaymentID() {
        return paymentID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getStatus() {
        return status;
    }

    public String getBlock_height() {
        return block_height;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBlock_height(String block_height) {
        this.block_height = block_height;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
