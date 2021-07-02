package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class BlockBody {
    @JSONField(name = "Full")
    List<RawTransaction> rawTransactions;

    public List<RawTransaction> getRawTransactions() {
        return rawTransactions;
    }

    public void setRawTransactions(List<RawTransaction> rawTransactions) {
        this.rawTransactions = rawTransactions;
    }
}
