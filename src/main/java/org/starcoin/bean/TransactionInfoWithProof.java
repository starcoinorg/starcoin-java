package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionInfoWithProof {

    @JSONField(name = "transaction_info")
    @JsonProperty("transaction_info")
    private TransactionInfo transactionInfo;

    @JSONField(name = "proof")
    @JsonProperty("proof")
    private BaseProof proof;

    @JSONField(name = "event_proof")
    @JsonProperty("event_proof")
    private EventProof eventProof;

    @JSONField(name = "state_proof")
    @JsonProperty("state_proof")
    private StateProof stateProof;

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public BaseProof getProof() {
        return proof;
    }

    public void setProof(BaseProof proof) {
        this.proof = proof;
    }

    public EventProof getEventProof() {
        return eventProof;
    }

    public void setEventProof(EventProof eventProof) {
        this.eventProof = eventProof;
    }

    public StateProof getStateProof() {
        return stateProof;
    }

    public void setStateProof(StateProof stateProof) {
        this.stateProof = stateProof;
    }

    @Override
    public String toString() {
        return "TransactionInfoWithProof{" +
                "transactionInfo=" + transactionInfo +
                ", proof=" + proof +
                ", eventProof=" + eventProof +
                ", stateProof=" + stateProof +
                '}';
    }
}
