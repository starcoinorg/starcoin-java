package org.starcoin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kind {

    public static final Kind PendingTxn = new Kind(2, "newPendingTransactions");
    private int type;
    @JsonProperty("type_name")
    private String typeName;

    public Kind(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
