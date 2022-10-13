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


import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class Block extends Base {
    @JSONField(name = "metadata")
    @JsonProperty("metadata")
    BlockMetadata blockMetadata;

    @JSONField(name = "uncles")
    @JsonProperty("uncles")
    List<BlockHeader> uncles;

    @JSONField(name = "header")
    @JsonProperty("header")
    private BlockHeader header;

    @JSONField(name = "raw")
    @JsonProperty("raw")
    private String raw;

    private BlockBody body;

    @JSONField(serialize = false)
    private List<Transaction> transactionList;


    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<BlockHeader> getUncles() {
        return uncles;
    }

    public void setUncles(List<BlockHeader> uncles) {
        this.uncles = uncles;
    }

    public BlockMetadata getBlockMetadata() {
        return blockMetadata;
    }

    public void setBlockMetadata(BlockMetadata blockMetadata) {
        this.blockMetadata = blockMetadata;
    }


    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public BlockBody getBody() {
        return body;
    }

    public void setBody(BlockBody body) {
        this.body = body;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }


    @Override
    public String toString() {
        return "Block{" +
                "blockMetadata=" + blockMetadata +
                ", uncles=" + uncles +
                ", header=" + header +
                ", raw='" + raw + '\'' +
                ", body=" + body +
                ", transactionList=" + transactionList +
                '}';
    }
}
