package org.starcoin.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ListResource {
    @JsonProperty("resources")
    private Map<String, Resource> resources;

    public Map<String, Resource> getResources() {
        return resources;
    }

    public void setResources(Map<String, Resource> resources) {
        this.resources = resources;
    }
}
