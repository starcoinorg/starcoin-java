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

    public EventFilter(long fromBlock,  String address) {
        this.fromBlock = fromBlock;
        this.eventKeys = Arrays.asList(recvPrefix+address,sendPrefix+address);
    }

    public long getFromBlock() {
        return fromBlock;
    }

    public List<String> getEventKeys() {
        return eventKeys;
    }

}
