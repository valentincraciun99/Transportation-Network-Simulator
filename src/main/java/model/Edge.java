package model;

public class Edge {
    Integer id;
    Integer node_from;
    Integer node_to;

    public Edge(Integer node_from, Integer node_to, Integer value, Integer configId) {
        this.node_from = node_from;
        this.node_to = node_to;
        this.value = value;
        this.configId = configId;
    }

    Integer value;
    Integer configId;

    public Edge(Integer id, Integer node_from, Integer node_to, Integer value, Integer configId) {
        this.id = id;
        this.node_from = node_from;
        this.node_to = node_to;
        this.value = value;
        this.configId = configId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNode_from() {
        return node_from;
    }

    public void setNode_from(Integer node_from) {
        this.node_from = node_from;
    }

    public Integer getNode_to() {
        return node_to;
    }

    public void setNode_to(Integer node_to) {
        this.node_to = node_to;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }


}
