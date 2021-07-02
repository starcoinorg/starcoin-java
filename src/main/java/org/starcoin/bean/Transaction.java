package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Transaction {
    @JSONField(name = "block_hash")
    String blockHash;

    @JSONField(name = "block_number")
    String blockNumber;

    @JSONField(name = "transaction_hash")
    String transactionHash;

    @JSONField(name = "transaction_index")
    int transactionIndex;

    @JSONField(name = "state_root_hash")
    String stateRootHash;

    @JSONField(name = "event_root_hash")
    String eventRootHash;

    @JSONField(name = "gas_used")
    String gasUsed;

    String status;

    long timestamp;

    @JSONField(name = "user_transaction")
    UserTransaction userTransaction;

    @JSONField(name = "block_metadata")
    BlockMetadata blockMetadata;

    List<Event> events;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

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

    public BlockMetadata getBlockMetadata() {
        return blockMetadata;
    }

    public void setBlockMetadata(BlockMetadata blockMetadata) {
        this.blockMetadata = blockMetadata;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }
}
