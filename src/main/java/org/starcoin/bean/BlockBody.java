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

import java.util.List;

public class BlockBody {
    @JSONField(name = "Full")
    @JsonProperty("Full")
    List<UserTransaction> userTransactions;

    @JSONField(name = "Hashes")
    @JsonProperty("Hashes")
    List<String> hashes;
    public List<UserTransaction> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(List<UserTransaction> userTransactions) {
        this.userTransactions = userTransactions;
    }

    public List<String> getHashes() {
        return hashes;
    }

    public void setHashes(List<String> hashes) {
        this.hashes = hashes;
    }

    @Override
    public String toString() {
        return "BlockBody{" +
                "userTransactions=" + userTransactions +
                ", hashes=" + hashes +
                '}';
    }
}
