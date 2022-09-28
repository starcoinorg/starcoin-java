package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccumulatorNode {

    @JSONField(name = "accumulator_root")
    @JsonProperty("accumulator_root")
    private String accumulatorRoot;

    @JSONField(name = "frozen_subtree_roots")
    @JsonProperty("frozen_subtree_roots")
    private List<String> frozenSubtreeRoots;

    @JSONField(name = "num_leaves")
    @JsonProperty("num_leaves")
    private String numLeaves;

    @JSONField(name = "num_nodes")
    @JsonProperty("num_nodes")
    private String numNodes;

    public String getAccumulatorRoot() {
        return accumulatorRoot;
    }

    public void setAccumulatorRoot(String accumulatorRoot) {
        this.accumulatorRoot = accumulatorRoot;
    }

    public List<String> getFrozenSubtreeRoots() {
        return frozenSubtreeRoots;
    }

    public void setFrozenSubtreeRoots(List<String> frozenSubtreeRoots) {
        this.frozenSubtreeRoots = frozenSubtreeRoots;
    }

    public String getNumLeaves() {
        return numLeaves;
    }

    public void setNumLeaves(String numLeaves) {
        this.numLeaves = numLeaves;
    }

    public String getNumNodes() {
        return numNodes;
    }

    public void setNumNodes(String numNodes) {
        this.numNodes = numNodes;
    }

    @Override
    public String toString() {
        return "AccumulatorInfo{" +
                "accumulatorRoot='" + accumulatorRoot + '\'' +
                ", frozenSubtreeRoots=" + frozenSubtreeRoots +
                ", numLeaves='" + numLeaves + '\'' +
                ", numNodes='" + numNodes + '\'' +
                '}';
    }
}
