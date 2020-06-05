package model;

import java.util.NoSuchElementException;

public class Node   {
    Integer id;
    String name;
    Integer x;
    Integer y;
    Integer configId;

    public Node(Integer id, String name, Integer x, Integer y, Integer configId) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.configId = configId;
    }

    public Node(String name, Integer x, Integer y, Integer configId) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.configId = configId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node)
            return this.getId() == ((Node) obj).getId();

        throw new NoSuchElementException();
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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }
}
