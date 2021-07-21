package org.starcoin.bean;

import java.util.List;

public class BlockBody {
    List<RawTransaction> rawTransactions;

    public List<RawTransaction> getRawTransactions() {
        return rawTransactions;
    }

    public void setRawTransactions(List<RawTransaction> rawTransactions) {
        this.rawTransactions = rawTransactions;
    }

    @Override
    public String toString() {
        return "BlockBody{" +
                "rawTransactions=" + rawTransactions +
                '}';
    }
}
