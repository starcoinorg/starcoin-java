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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class EventNotificationResult {

    @JsonProperty("block_hash")
    private String blockHash;

    @JsonProperty("block_number")
    private String blockNumber;

    @JsonProperty("transaction_hash")
    private String transactionHash;

    @JsonProperty("transaction_index")
    private int transactionIndex;

    @JsonProperty("decode_event_data")
    private JsonNode data;

    @JsonProperty("type_tag")
    private String typeTag;

    @JsonProperty("event_key")
    private String eventKey;

    @JsonProperty("event_seq_number")
    private String eventSeqNumber;

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

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(String typeTag) {
        this.typeTag = typeTag;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventSeqNumber() {
        return eventSeqNumber;
    }

    public void setEventSeqNumber(String eventSeqNumber) {
        this.eventSeqNumber = eventSeqNumber;
    }

    @Override
    public String toString() {
        return "EventNotificationResult{" +
                "blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", data='" + data + '\'' +
                ", typeTag='" + typeTag + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", eventSeqNumber='" + eventSeqNumber + '\'' +
                '}';
    }
}
