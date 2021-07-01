package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Ed25519 {
    @JSONField(name = "public_key")
    String publicKey;
    @JSONField(name = "signature")
    String signature;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
