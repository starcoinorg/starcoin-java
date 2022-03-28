package org.starcoin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEventOption {

    @JsonProperty("decode")
    private boolean decode = true;

    public GetEventOption() {
    }

    public GetEventOption(boolean decode) {
        this.decode = decode;
    }

    public boolean isDecode() {
        return decode;
    }

    public void setDecode(boolean decode) {
        this.decode = decode;
    }
}
