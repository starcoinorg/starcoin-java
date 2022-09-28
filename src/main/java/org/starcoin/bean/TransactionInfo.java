package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionInfo {

    @JSONField(name = "block_hash")
    @JsonProperty("block_hash")
    private String blockHash;

    @JSONField(name = "block_number")
    @JsonProperty("block_number")
    private String blockNumber;

    @JSONField(name = "transaction_hash")
    @JsonProperty("transaction_hash")
    private String transactionHash;

    @JSONField(name = "transaction_index")
    @JsonProperty("transaction_index")
    private int transactionIndex;

    @JSONField(name = "transaction_global_index")
    @JsonProperty("transaction_global_index")
    private long transactionGlobalIndex;

    @JSONField(name = "state_root_hash")
    @JsonProperty("state_root_hash")
    private String stateRootHash;

    @JSONField(name = "event_root_hash")
    @JsonProperty("event_root_hash")
    private String eventRootHash;

    @JSONField(name = "gas_used")
    @JsonProperty("gas_used")
    private String gasUsed;

    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public int getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(int transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public long getTransactionGlobalIndex() {
        return transactionGlobalIndex;
    }

    public void setTransactionGlobalIndex(long transactionGlobalIndex) {
        this.transactionGlobalIndex = transactionGlobalIndex;
    }

    public String getStateRootHash() {
        return stateRootHash;
    }

    public void setStateRootHash(String stateRootHash) {
        this.stateRootHash = stateRootHash;
    }

    public String getEventRootHash() {
        return eventRootHash;
    }

    public void setEventRootHash(String eventRootHash) {
        this.eventRootHash = eventRootHash;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", transactionGlobalIndex=" + transactionGlobalIndex +
                ", stateRootHash='" + stateRootHash + '\'' +
                ", eventRootHash='" + eventRootHash + '\'' +
                ", gasUsed='" + gasUsed + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
