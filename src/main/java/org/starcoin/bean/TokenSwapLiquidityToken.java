package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class TokenSwapLiquidityToken {

    @JSONField(name = "token_first")
    private String tokenFirst;

    @JSONField(name = "token_second")
    private String tokenSecond;

    public TokenSwapLiquidityToken(String tokenFirst, String tokenSecond) {
        this.tokenFirst = tokenFirst;
        this.tokenSecond = tokenSecond;
    }

    public String getTokenFirst() {
        return tokenFirst;
    }

    public String getTokenSecond() {
        return tokenSecond;
    }

    @Override
    public String toString() {
        return "TokenSwapLiquidityToken{" +
                "tokenFirst='" + tokenFirst + '\'' +
                ", tokenSecond='" + tokenSecond + '\'' +
                '}';
    }
}
