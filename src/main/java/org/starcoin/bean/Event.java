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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.starcoin.types.AccountAddress;

public class Event extends Base {
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

    String data;

    @JSONField(name = "type_tag")
    @JsonProperty("type_tag")
    String typeTag;

    @JSONField(name = "event_key")
    @JsonProperty("event_key")
    String eventKey;

    @JSONField(name = "event_seq_number")
    @JsonProperty("event_seq_number")
    String eventSeqNumber;


    @JSONField(name = "event_index")
    @JsonProperty("event_index")
    Long eventIndex;


    @JSONField(name = "decode_event_data")
    @JsonProperty("decode_event_data")
    Object decodeEventData;


    public Long getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(Long eventIndex) {
        this.eventIndex = eventIndex;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public String eventCreateAddress() {
        int length = this.eventKey.length();
        if (length >= AccountAddress.LENGTH * 2) {
            return "0x" + this.eventKey.substring(length - AccountAddress.LENGTH * 2);
        } else {
            return null;
        }
    }

    public Object getDecodeEventData() {
        return decodeEventData;
    }

    public void setDecodeEventData(Object decodeEventData) {
        this.decodeEventData = decodeEventData;
    }

    public long getTransactionGlobalIndex() {
        return transactionGlobalIndex;
    }

    public void setTransactionGlobalIndex(long transactionGlobalIndex) {
        this.transactionGlobalIndex = transactionGlobalIndex;
    }


    @Override
    public String toString() {
        return "Event{" +
                "blockHash='" + blockHash + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", transactionIndex=" + transactionIndex +
                ", transactionGlobalIndex=" + transactionGlobalIndex +
                ", data='" + data + '\'' +
                ", typeTag='" + typeTag + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", eventSeqNumber='" + eventSeqNumber + '\'' +
                ", eventIndex=" + eventIndex +
                ", decodeEventData=" + decodeEventData +
                '}';
    }
}
