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

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonProperty;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class BlockHeader extends Base {
    long timestamp;

    private String author;

    @JSONField(name = "author_auth_key")
    @JsonProperty("author_auth_key")
    private String authorAuthKey;

    @JSONField(name = "block_accumulator_root")
    @JsonProperty("block_accumulator_root")
    private String blockAccumulatorRoot;

    @JSONField(name = "block_hash")
    @JsonProperty("block_hash")
    private String blockHash;

    @JSONField(name = "body_hash")
    @JsonProperty("body_hash")
    private String bodyHash;

    @JSONField(name = "chain_id")
    @JsonProperty("chain_id")
    private int chainId;

    @JSONField(name = "difficulty")
    @JsonProperty("difficulty")
    private String difficultyHexStr;

    @JSONField(name = "difficulty_number")
    @JsonProperty("difficulty_number")
    private long difficulty;

    private String extra;

    @JSONField(name = "gas_used")
    @JsonProperty("gas_used")
    private long gasUsed;

    private long nonce;

    @JSONField(name = "number")
    @JsonProperty("number")
    private long height;

    @JSONField(name = "parent_hash")
    @JsonProperty("parent_hash")
    private String parentHash;

    @JSONField(name = "state_root")
    @JsonProperty("state_root")
    private String stateRoot;

    @JSONField(name = "txn_accumulator_root")
    @JsonProperty("txn_accumulator_root")
    private String txnAccumulatorRoot;

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

    public String getBlockAccumulatorRoot() {
        return blockAccumulatorRoot;
    }

    public void setBlockAccumulatorRoot(String blockAccumulatorRoot) {
        this.blockAccumulatorRoot = blockAccumulatorRoot;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBodyHash() {
        return bodyHash;
    }

    public void setBodyHash(String bodyHash) {
        this.bodyHash = bodyHash;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(long gasUsed) {
        this.gasUsed = gasUsed;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public void setStateRoot(String stateRoot) {
        this.stateRoot = stateRoot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxnAccumulatorRoot() {
        return txnAccumulatorRoot;
    }

    public void setTxnAccumulatorRoot(String txnAccumulatorRoot) {
        this.txnAccumulatorRoot = txnAccumulatorRoot;
    }

    public String getDifficultyHexStr() {
        return difficultyHexStr;
    }

    public void setDifficultyHexStr(String difficultyHexStr) {
        this.difficultyHexStr = difficultyHexStr;
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "timestamp=" + timestamp +
                ", author='" + author + '\'' +
                ", authorAuthKey='" + authorAuthKey + '\'' +
                ", blockAccumulatorRoot='" + blockAccumulatorRoot + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", bodyHash='" + bodyHash + '\'' +
                ", chainId=" + chainId +
                ", difficultyHexStr='" + difficultyHexStr + '\'' +
                ", difficulty=" + difficulty +
                ", extra='" + extra + '\'' +
                ", gasUsed=" + gasUsed +
                ", nonce=" + nonce +
                ", height=" + height +
                ", parentHash='" + parentHash + '\'' +
                ", stateRoot='" + stateRoot + '\'' +
                ", txnAccumulatorRoot='" + txnAccumulatorRoot + '\'' +
                '}';
    }
}
