package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Transfer {

    long timestamp;
    String identifier;
    @JSONField(name = "txn_hash")
    String txnHash;
    @JSONField(name = "sender")
    String sender;
    @JSONField(name = "receiver")
    String receiver;
    @JSONField(name = "amount")
    String amount;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTxnHash() {
        return txnHash;
    }

    public void setTxnHash(String txnHash) {
        this.txnHash = txnHash;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
