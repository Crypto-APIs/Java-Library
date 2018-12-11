package com.cryptoapis.blockchains.bitcoin.models.BtcTransaction;

import com.cryptoapis.models.RawJSON;
import java.util.List;

public class BtcTransaction extends RawJSON {

    private String txid;
    private String block_hash;
    private int confirmations;
    private int version;
    private long block_time;
    private long size;
    private String time;
    private int vin_sz;
    private String hash;
    private int vout_sz;
    private int locktime;
    private long timestamp;
    private List<Inputs> inputs;
    private List<Outputs> outputs;

    public static class Outputs {
        private List<String> addresses;
        private String asm;
        private String type;
        private long value;
        private int reqsigs;

        public List<String> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<String> addresses) {
            this.addresses = addresses;
        }

        public String getAsm() {
            return asm;
        }

        public void setAsm(String asm) {
            this.asm = asm;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public int getReqsigs() {
            return reqsigs;
        }

        public void setReqsigs(int reqsigs) {
            this.reqsigs = reqsigs;
        }
    }

    public static class Inputs {
        private boolean coinbase;
        private List<String> addresses;
        private String prev_hash;
        private long output_value;
        private int output_index;
        private int vout;
        private Script script;

        public List<String> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<String> addresses) {
            this.addresses = addresses;
        }

        public String getPrev_hash() {
            return prev_hash;
        }

        public void setPrev_hash(String prev_hash) {
            this.prev_hash = prev_hash;
        }

        public long getOutput_value() {
            return output_value;
        }

        public void setOutput_value(long output_value) {
            this.output_value = output_value;
        }

        public int getOutput_index() {
            return output_index;
        }

        public void setOutput_index(int output_index) {
            this.output_index = output_index;
        }

        public int getVout() {
            return vout;
        }

        public void setVout(int vout) {
            this.vout = vout;
        }

        public Script getScript() {
            return script;
        }

        public void setScript(Script script) {
            this.script = script;
        }

        public class Script {
            private String asm;

            public String getAsm() {
                return asm;
            }

            public void setAsm(String asm) {
                this.asm = asm;
            }
        }

        public boolean isCoinbase() {
            return coinbase;
        }

        public void setCoinbase(boolean coinbase) {
            this.coinbase = coinbase;
        }
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getBlock_hash() {
        return block_hash;
    }

    public void setBlock_hash(String block_hash) {
        this.block_hash = block_hash;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getBlock_time() {
        return block_time;
    }

    public void setBlock_time(long block_time) {
        this.block_time = block_time;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVin_sz() {
        return vin_sz;
    }

    public void setVin_sz(int vin_sz) {
        this.vin_sz = vin_sz;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getVout_sz() {
        return vout_sz;
    }

    public void setVout_sz(int vout_sz) {
        this.vout_sz = vout_sz;
    }

    public int getLocktime() {
        return locktime;
    }

    public void setLocktime(int locktime) {
        this.locktime = locktime;
    }

    public List<Inputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<Inputs> inputs) {
        this.inputs = inputs;
    }

    public List<Outputs> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Outputs> outputs) {
        this.outputs = outputs;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
