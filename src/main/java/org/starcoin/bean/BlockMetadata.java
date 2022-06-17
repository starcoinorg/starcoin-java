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

public class BlockMetadata {

    @JSONField(name = "parent_hash")
    @JsonProperty("parent_hash")
    String parentHash;

    long timestamp;

    String author;

    @JSONField(name = "author_auth_key")
    @JsonProperty("author_auth_key")
    String authorAuthKey;

    String uncles;

    String number;

    @JSONField(name = "chain_id")
    @JsonProperty("chain_id")
    String chainId;

    @JSONField(name = "parent_gas_used")
    @JsonProperty("parent_gas_used")
    long parentGasUsed;

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorAuthKey() {
        return authorAuthKey;
    }

    public void setAuthorAuthKey(String authorAuthKey) {
        this.authorAuthKey = authorAuthKey;
    }

    public String getUncles() {
        return uncles;
    }

    public void setUncles(String uncles) {
        this.uncles = uncles;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public long getParentGasUsed() {
        return parentGasUsed;
    }

    public void setParentGasUsed(long parentGasUsed) {
        this.parentGasUsed = parentGasUsed;
    }

    @Override
    public String toString() {
        return "BlockMetadata{" +
                "parentHash='" + parentHash + '\'' +
                ", timestamp=" + timestamp +
                ", author='" + author + '\'' +
                ", authorAuthKey='" + authorAuthKey + '\'' +
                ", uncles='" + uncles + '\'' +
                ", number='" + number + '\'' +
                ", chainId='" + chainId + '\'' +
                ", parentGasUsed=" + parentGasUsed +
                '}';
    }
}
