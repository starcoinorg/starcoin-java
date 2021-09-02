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

public class TokenInfo extends Base {
    @JSONField(name = "total_value")
    private long totalValue;
    @JSONField(name = "scaling_factor")
    private long scalingFactor;

    public long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(long totalValue) {
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
                "totalValue=" + totalValue +
                ", scalingFactor=" + scalingFactor +
                '}';
    }
}
