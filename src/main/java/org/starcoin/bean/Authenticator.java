package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Authenticator {
    @JSONField(name = "Ed25519")
    Ed25519 ed25519;

    public Ed25519 getEd25519() {
        return ed25519;
    }

    public void setEd25519(Ed25519 ed25519) {
        this.ed25519 = ed25519;
    }
}
