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
}
