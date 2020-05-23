package model;

public class Edge {
    Integer id;
    Integer node_from;
    Integer node_tol;
    Integer value;
    Integer configId;

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

    public Integer getNode_tol() {
        return node_tol;
    }

    public void setNode_tol(Integer node_tol) {
        this.node_tol = node_tol;
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

    public Edge(Integer id, Integer node_from, Integer node_tol, Integer value, Integer configId) {
        this.id = id;
        this.node_from = node_from;
        this.node_tol = node_tol;
        this.value = value;
        this.configId = configId;
    }
}
