package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BaseProof {

    @JSONField(name = "siblings")
    @JsonProperty("siblings")
    public List<String> siblings;

    public List<String> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<String> siblings) {
        this.siblings = siblings;
    }

    @Override
    public String toString() {
        return "Proof{" +
                "siblings=" + siblings +
                '}';
    }
}
