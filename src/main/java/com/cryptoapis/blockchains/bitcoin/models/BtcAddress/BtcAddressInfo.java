package com.cryptoapis.blockchains.bitcoin.models.BtcAddress;

import com.cryptoapis.models.RawJSON;
import com.google.gson.Gson;

public class BtcAddressInfo extends RawJSON {
    private Long total_spent;
    private Integer txout_n;
    private Long final_balance;
    private String address;
    private Integer txout_o;
    private Integer txs;
    private Long total_received;

    public Long getTotal_spent() {
        return total_spent;
    }

    public void setTotal_spent(Long total_spent) {
        this.total_spent = total_spent;
    }

    public Integer getTxout_n() {
        return txout_n;
    }

    public void setTxout_n(Integer txout_n) {
        this.txout_n = txout_n;
    }

    public Long getFinal_balance() {
        return final_balance;
    }

    public void setFinal_balance(Long final_balance) {
        this.final_balance = final_balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTxout_o() {
        return txout_o;
    }

    public void setTxout_o(Integer txout_o) {
        this.txout_o = txout_o;
    }

    public Integer getTxs() {
        return txs;
    }

    public void setTxs(Integer txs) {
        this.txs = txs;
    }

    public Long getTotal_received() {
        return total_received;
    }

    public void setTotal_received(Long total_received) {
        this.total_received = total_received;
    }
}
