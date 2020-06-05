package model.request;

import model.Edge;
import model.Node;

import java.util.List;

public class ShortestPathRequest {
    public List<Node> nodes;

    public List<Edge> edges;

    public Node node_from;

    public Node node_to;

    public ShortestPathRequest(List<Node> nodes, List<Edge> edges, Node node_from, Node node_to) {
        this.nodes = nodes;
        this.edges = edges;
        this.node_from = node_from;
        this.node_to = node_to;
    }
}
