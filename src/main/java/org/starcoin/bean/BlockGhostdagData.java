package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;
import java.util.List;

public class BlockGhostdagData {

    @JSONField(name = "blue_work")
    @JsonProperty("blue_work")
    private String blueWork;

    @JSONField(name = "blue_score")
    @JsonProperty("blue_score")
    private Long blueScore;

    @JSONField(name = "blues_anticone_sizes")
    @JsonProperty("blues_anticone_sizes")
    private HashMap<String, Integer> bluesAnticoneSizes;

    @JSONField(name = "mergeset_blues")
    @JsonProperty("mergeset_blues")
    private List<String> mergesetBlues;

    @JSONField(name = "mergeset_reds")
    @JsonProperty("mergeset_reds")
    private List<String> mergesetReds;

    @JSONField(name = "selected_parent")
    @JsonProperty("selected_parent")
    private String selectedParent;

    public String getBlueWork() {
        return blueWork;
    }

    public void setBlueWork(String blueWork) {
        this.blueWork = blueWork;
    }

    public Long getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(Long blueScore) {
        this.blueScore = blueScore;
    }

    public HashMap<String, Integer> getBluesAnticoneSizes() {
        return bluesAnticoneSizes;
    }

    public void setBluesAnticoneSizes(HashMap<String, Integer> bluesAnticoneSizes) {
        this.bluesAnticoneSizes = bluesAnticoneSizes;
    }

    public List<String> getMergesetBlues() {
        return mergesetBlues;
    }

    public void setMergesetBlues(List<String> mergesetBlues) {
        this.mergesetBlues = mergesetBlues;
    }

    public List<String> getMergesetReds() {
        return mergesetReds;
    }

    public void setMergesetReds(List<String> mergesetReds) {
        this.mergesetReds = mergesetReds;
    }

    public String getSelectedParent() {
        return selectedParent;
    }

    public void setSelectedParent(String selectedParent) {
        this.selectedParent = selectedParent;
    }

    public String toString() {
        return "BlockGhostdagData{" +
                "blueWork=" + blueWork +
                ", blueScore=" + blueScore +
                ", bluesAnticoneSizes=" + bluesAnticoneSizes +
                ", mergesetBlues=" + mergesetBlues +
                ", mergesetReds=" + mergesetReds +
                ", selectedParent=" + selectedParent +
                '}';
    }
}
