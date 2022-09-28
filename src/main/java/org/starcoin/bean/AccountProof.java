package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountProof extends BaseProof {

    @JSONField(name = "leaf")
    @JsonProperty("leaf")
    private List<String> leaf;

    public List<String> getLeaf() {
        return leaf;
    }

    public void setLeaf(List<String> leaf) {
        this.leaf = leaf;
    }
}
