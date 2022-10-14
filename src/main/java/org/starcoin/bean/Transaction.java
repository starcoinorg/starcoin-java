/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Transaction extends Base {
    @JSONField(name = "block_hash")
    @JsonProperty("block_hash")
    String blockHash;

    @JSONField(name = "block_number")
    @JsonProperty("block_number")
    String blockNumber;

    @JSONField(name = "transaction_hash")
    @JsonProperty("transaction_hash")
    String transactionHash;

    @JSONField(name = "transaction_index")
    @JsonProperty("transaction_index")
    int transactionIndex;

    @JSONField(name = "transaction_global_index")
    @JsonProperty("transaction_global_index")
    long transactionGlobalIndex;

    @JSONField(name = "state_root_hash")
    @JsonProperty("state_root_hash")
    String stateRootHash;

    @JSONField(name = "event_root_hash")
    @JsonProperty("event_root_hash")
    String eventRootHash;

    @JSONField(name = "gas_used")
    @JsonProperty("gas_used")
    String gasUsed;

    @JSONField(name = "user_transaction")
    @JsonProperty("user_transaction")
    UserTransaction userTransaction;

    @JSONField(name = "block_metadata")
    @JsonProperty("block_metadata")
    BlockMetadata blockMetadata;

    @JSONField(serialize = false)
    @JsonIgnore
    List<Event> events;

    @JSONField(name = "transaction_type")
    @JsonProperty("transaction_type")
    TransactionType transactionType;

    @JSONField(name = "status")
    @JsonProperty("status")
    @JsonDeserialize(using = CustomStringDeserializer.class)
    String status;

//    @JsonProperty("status")
//    Object statusObj;

    long timestamp;


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public long getTransactionGlobalIndex() {
        return transactionGlobalIndex;
    }

    public void setTransactionGlobalIndex(long transactionGlobalIndex) {
        this.transactionGlobalIndex = transactionGlobalIndex;
    }
//
//    public Object getStatusObj() {
//        return statusObj;
//    }
//
//    public void setStatusObj(Object statusObj) {
//        this.statusObj = statusObj;
//    }

    @Override
    public String toString() {
        return "Transaction{" +
                "blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", transactionGlobalIndex=" + transactionGlobalIndex +
                ", stateRootHash='" + stateRootHash + '\'' +
                ", eventRootHash='" + eventRootHash + '\'' +
                ", gasUsed='" + gasUsed + '\'' +
                ", userTransaction=" + userTransaction +
                ", blockMetadata=" + blockMetadata +
                ", events=" + events +
                ", transactionType=" + transactionType +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
