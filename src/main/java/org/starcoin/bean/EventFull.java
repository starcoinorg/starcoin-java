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

public class EventFull extends Event {

    @JSONField(name = "event_address")
    private String eventAddress;

    @JSONField(name = "tag_address")
    private String tagAddress;

    @JSONField(name = "tag_module")
    private String tagModule;

    @JSONField(name = "tag_name")
    private String tagName;

    private long timestamp;

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getTagAddress() {
        return tagAddress;
    }

    public void setTagAddress(String tagAddress) {
        this.tagAddress = tagAddress;
    }

    public String getTagModule() {
        return tagModule;
    }

    public void setTagModule(String tagModule) {
        this.tagModule = tagModule;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EventFull{" +
                "eventAddress='" + eventAddress + '\'' +
                ", tagAddress='" + tagAddress + '\'' +
                ", tagModule='" + tagModule + '\'' +
                ", tagName='" + tagName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
