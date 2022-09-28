package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockInfo {

    @JSONField(name = "block_accumulator_info")
    @JsonProperty("block_accumulator_info")
    private AccumulatorNode blockAccumulatorInfo;

    @JSONField(name = "block_hash")
    @JsonProperty("block_hash")
    private String blockHash;

    @JSONField(name = "total_difficulty")
    @JsonProperty("total_difficulty")
    private String totalDifficulty;

    @JSONField(name = "txn_accumulator_info")
    @JsonProperty("txn_accumulator_info")
    private AccumulatorNode txnAccumulatorInfo;

    public AccumulatorNode getBlockAccumulatorInfo() {
        return blockAccumulatorInfo;
    }

    public void setBlockAccumulatorInfo(AccumulatorNode blockAccumulatorInfo) {
        this.blockAccumulatorInfo = blockAccumulatorInfo;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    public void setTotalDifficulty(String totalDifficulty) {
        this.totalDifficulty = totalDifficulty;
    }

    public AccumulatorNode getTxnAccumulatorInfo() {
        return txnAccumulatorInfo;
    }

    public void setTxnAccumulatorInfo(AccumulatorNode txnAccumulatorInfo) {
        this.txnAccumulatorInfo = txnAccumulatorInfo;
    }

    @Override
    public String toString() {
        return "BlockNodeInfo{" +
                "blockAccumulatorInfo=" + blockAccumulatorInfo +
                ", blockHash='" + blockHash + '\'' +
                ", totalDifficulty='" + totalDifficulty + '\'' +
                ", txnAccumulatorInfo=" + txnAccumulatorInfo +
                '}';
    }
}
