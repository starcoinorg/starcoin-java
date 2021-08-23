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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class EventFilter {

    private static final String recvPrefix = "0100000000000000";
    private static final String sendPrefix = "0000000000000000";

    @JsonProperty("from_block")
    private long fromBlock;

    @JsonProperty("event_keys")
    private List<String> eventKeys;

    private boolean decode = true;

    public EventFilter(long fromBlock, String address) {
        this.fromBlock = fromBlock;
        address = address.replaceFirst("0x", "");
        this.eventKeys = Arrays.asList(recvPrefix + address, sendPrefix + address);
    }

    public long getFromBlock() {
        return fromBlock;
    }

    public List<String> getEventKeys() {
        return eventKeys;
    }

    public boolean isDecode() {
        return decode;
    }
}
