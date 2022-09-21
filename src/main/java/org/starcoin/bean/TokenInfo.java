/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class TokenInfo extends Base {
    @JSONField(name = "token_code")
    @JsonProperty("token_code")
    private String tokenCode;

    @JSONField(name = "total_value")
    @JsonProperty("total_value")
    private BigInteger totalValue;

    @JSONField(name = "scaling_factor")
    @JsonProperty("scaling_factor")
    private long scalingFactor;

    @JSONField(name = "burn_events")
    @JsonProperty("burn_events")
    private Object burnEvents;

    @JSONField(name = "mint_events")
    @JsonProperty("mint_events")
    private Object mintEvents;

    public Object getMintEvents() {
        return mintEvents;
    }

    public void setMintEvents(Object mintEvents) {
        this.mintEvents = mintEvents;
    }

    public Object getBurnEvents() {
        return burnEvents;
    }

    public void setBurnEvents(Object burnEvents) {
        this.burnEvents = burnEvents;
    }


    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public BigInteger getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigInteger totalValue) {
        this.totalValue = totalValue;
    }

    public long getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(long scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "id='" + id + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", totalValue=" + totalValue +
                ", scalingFactor=" + scalingFactor +
                ", burnEvents=" + burnEvents +
                ", mintEvents=" + mintEvents +
                '}';
    }
}
