package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ListResourceOption {
    private boolean decode;
    @JSONField(name = "state_root")
    private String stateRoot;

    public boolean isDecode() {
        return decode;
    }

    public void setDecode(boolean decode) {
        this.decode = decode;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public void setStateRoot(String stateRoot) {
        this.stateRoot = stateRoot;
    }
}
