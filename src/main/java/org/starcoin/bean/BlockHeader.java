package org.starcoin.bean;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class BlockHeader {
    private String author;
    @JSONField(name = "author_auth_key")
    private String authorAuthKey;
    @JSONField(name = "block_accumulator_root")
    private String blockAccumulatorRoot;
    @JSONField(name = "block_hash")
    private String blockHash;
    @JSONField(name = "body_hash")
    private String bodyHash;
    @JSONField(name = "chain_id")
    private int chainId;
    @JSONField(name = "difficulty_number")
    private long difficulty;
    private String extra;
    @JSONField(name = "gas_used")
    private long gasUsed;
    private long nonce;
    @JSONField(name = "number")
    private long height;
    @JSONField(name = "parent_hash")
    private String parentHash;
    @JSONField(name = "state_root")
    private String stateRoot;
    private String timestamp;
    @JSONField(name = "txn_accumulator_root")
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxnAccumulatorRoot() {
        return txnAccumulatorRoot;
    }

    public void setTxnAccumulatorRoot(String txnAccumulatorRoot) {
        this.txnAccumulatorRoot = txnAccumulatorRoot;
    }
}
