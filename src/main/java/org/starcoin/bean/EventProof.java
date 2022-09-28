package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventProof {

    @JSONField(name = "event")
    @JsonProperty("event")
    private String event;

    @JSONField(name = "proof")
    @JsonProperty("proof")
    private BaseProof proof;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public BaseProof getProof() {
        return proof;
    }

    public void setProof(BaseProof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "EventProof{" +
                "event='" + event + '\'' +
                ", proof=" + proof +
                '}';
    }
}
