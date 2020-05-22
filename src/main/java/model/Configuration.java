package model;

import java.util.ArrayList;

public class Configuration {
    public Configuration(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    Integer id;
    String name;
    ArrayList<Node> nodes;

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
}
