package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class BlockBody {
    @JSONField(name = "Full")
    List<UserTransaction> userTransactions;

    public List<UserTransaction> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(List<UserTransaction> userTransactions) {
        this.userTransactions = userTransactions;
    }

    @Override
    public String toString() {
        return "BlockBody{" +
                "userTransactions=" + userTransactions +
                '}';
    }
}
