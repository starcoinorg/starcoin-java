package org.starcoin.bean;


import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class Block {
    @JSONField(name = "metadata")
    BlockMetadata blockMetadata;
    @JSONField(name = "uncles")
    List<BlockHeader> uncles;
    @JSONField(name = "header")
    private BlockHeader header;
    private BlockBody body;
    @JSONField(serialize = false)
    private List<Transaction> transactionList;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<BlockHeader> getUncles() {
        return uncles;
    }

    public void setUncles(List<BlockHeader> uncles) {
        this.uncles = uncles;
    }

    public BlockMetadata getBlockMetadata() {
        return blockMetadata;
    }

    public void setBlockMetadata(BlockMetadata blockMetadata) {
        this.blockMetadata = blockMetadata;
    }


    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public BlockBody getBody() {
        return body;
    }

    public void setBody(BlockBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockMetadata=" + blockMetadata +
                ", uncles=" + uncles +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
