package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class BlockMetadata {

    String parentHash;

    long timestamp;

    String author;

    @JSONField(name = "author_auth_key")
    String authorAuthKey;

    String uncles;

    String number;

    @JSONField(name = "chain_id")
    String chainId;

    @JSONField(name = "parent_gas_used")
    String parentGasUsed;

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

    public String getParentGasUsed() {
        return parentGasUsed;
    }

    public void setParentGasUsed(String parentGasUsed) {
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
                ", parentGasUsed='" + parentGasUsed + '\'' +
                '}';
    }
}
