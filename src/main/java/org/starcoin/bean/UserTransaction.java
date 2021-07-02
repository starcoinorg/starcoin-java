package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class UserTransaction {

    @JSONField(name = "transaction_hash")
    String transactionHash;

    @JSONField(name = "raw_txn")
    RawTransaction rawTransaction;

    Authenticator authenticator;

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

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}
