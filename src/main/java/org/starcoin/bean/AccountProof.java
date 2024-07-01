package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class AccountProof extends BaseProof {

    @JSONField(name = "leaf")
    private List<String> leaf;

    public List<String> getLeaf() {
        return leaf;
    }

    public void setLeaf(List<String> leaf) {
        this.leaf = leaf;
    }
}
