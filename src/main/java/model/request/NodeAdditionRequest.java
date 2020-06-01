package model.request;

public class NodeAdditionRequest {

    public Integer configId;
    public String name;
    public Integer x;
    public Integer y;

    public NodeAdditionRequest(Integer configId, String name, Integer x, Integer y) {
        this.configId = configId;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
