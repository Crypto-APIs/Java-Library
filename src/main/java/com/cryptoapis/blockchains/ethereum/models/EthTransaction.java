package com.cryptoapis.blockchains.ethereum.models;

import com.cryptoapis.models.RawJSON;
import java.math.BigInteger;
import java.util.ArrayList;

public class EthTransaction extends RawJSON {
    private String chain;

    private String status;

    private BigInteger index;

    private String hash;

    private BigInteger value;

    private String from;

    private String to;

    private String date;

    private String block_hash;

    private BigInteger block_number;

    private BigInteger gas;

    private BigInteger gas_price;

    private BigInteger gas_used;

    private BigInteger nonce;

    private BigInteger confirmations;

    private String input;

    private ArrayList<Object> token_transfers; //todo change object

    public String getChain() {
        return chain;
    }

    public String getStatus() {
        return status;
    }

    public BigInteger getIndex() {
        return index;
    }

    public String getHash() {
        return hash;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getBlock_hash() {
        return block_hash;
    }

    public BigInteger getBlock_number() {
        return block_number;
    }

    public BigInteger getGas() {
        return gas;
    }

    public BigInteger getGas_price() {
        return gas_price;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public BigInteger getConfirmations() {
        return confirmations;
    }

    public String getInput() {
        return input;
    }

    public BigInteger getGas_used() {
        return gas_used;
    }

    public ArrayList<Object> getToken_transfers() {
        return token_transfers;
    }
}
