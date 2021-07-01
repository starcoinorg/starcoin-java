package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class PendingTransaction {

    @JSONField(name = "authenticator")
    private Authenticator authenticator;
    @JSONField(name = "transaction_hash")
    private String transactionHash;

    @JSONField(name = "raw_txn")
    private RawTransaction rawTransaction;

    private long timestamp;

    public PendingTransaction(){
        timestamp = System.currentTimeMillis();
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public RawTransaction getRawTransaction() {
        return rawTransaction;
    }

    public void setRawTransaction(RawTransaction rawTransaction) {
        this.rawTransaction = rawTransaction;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
