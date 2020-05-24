package model;

import java.util.ArrayList;

public class Configuration {
    public Configuration(Integer id, String name,Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Configuration( String name,Integer userId) {
        this.name = name;
        this.userId = userId;
    }

    Integer id;
    String name;
    Integer userId;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
