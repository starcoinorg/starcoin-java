package org.starcoin.bean;


import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class UncleBlock {
    private BlockHeader header;
    @JSONField(name = "uncle_block_number")
    private long uncleBlockNumber;

    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public long getUncleBlockNumber() {
        return uncleBlockNumber;
    }

    public void setUncleBlockNumber(long uncleBlockNumber) {
        this.uncleBlockNumber = uncleBlockNumber;
    }
}
