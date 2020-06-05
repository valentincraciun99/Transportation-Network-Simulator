package business;

import business.interfaces.Service;
import model.Edge;
import model.request.ShortestPathRequest;

import java.util.*;

public class FindShortestPathService implements Service<ShortestPathRequest> {


    public class SimpleNode implements Comparator<SimpleNode> {
        public int nodeId;
        public int distance;

        public SimpleNode(Integer nodeId,Integer distance)
        {
            this.nodeId = nodeId;
            //distance from a node to this
            this.distance = distance;
        }

        public SimpleNode()
        {

        }

        @Override
        public int compare(SimpleNode node1, SimpleNode node2)
        {
            if (node1.distance < node2.distance)
                return -1;
            if (node1.distance > node2.distance)
                return 1;
            return 0;
        }
    }

    // Adjacency list representation of the
    // connected edges
    private List<List<SimpleNode> > adj;
    private int dist[];
    private int prev[];
    private List<Integer> settled;
    private PriorityQueue<SimpleNode> pq;
    private List<Integer> pathNodesIds;
    private List<Edge> result;
    Dictionary<Integer,Integer> nodesIdsNormalization;
    Dictionary<Integer,Integer> reverseNodesIdsNormalization;


    @Override
    public List<Edge> Execute(ShortestPathRequest request) {

        setInitData(request);

        solve(request);

        computePath(request);

        return result;
    }

    private void computePath(ShortestPathRequest request) {

        var nodeToNormalizedId= nodesIdsNormalization.get(request.node_to.getId());
        var lastId = nodeToNormalizedId;

        //Add nodes ids in a list
        while(lastId!=-1)
        {
            pathNodesIds.add(reverseNodesIdsNormalization.get(lastId));
            lastId=prev[lastId];
        }

        //Verify for each edge if contains nodes from path
        for(int i=1;i<pathNodesIds.size();i++) {
            for (var edge : request.edges) {
                if(edge.getNode_from()==pathNodesIds.get(i) && edge.getNode_to() == pathNodesIds.get(i-1))
                    result.add(edge);
            }
        }

    }

    private void solve(ShortestPathRequest request) {

        Integer normalizedNodeTo = nodesIdsNormalization.get(request.node_to.getId());
        while(!settled.contains(normalizedNodeTo))
        {
            // remove the minimum distance node
            // from the priority queue
            int u = pq.poll().nodeId;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            System.out.println(u);

            processNeighbours(u);

        }

    }

    private void processNeighbours(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            SimpleNode v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.nodeId)) {
                edgeDistance = v.distance;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.nodeId]) {
                    dist[v.nodeId] = newDistance;
                    prev[v.nodeId] = u;
                }

                // Add the current node to the queue
                pq.add(new SimpleNode(v.nodeId, dist[v.nodeId]));
            }
        }
    }



    private void setInitData(ShortestPathRequest request) {


        //create instances
        pathNodesIds = new ArrayList<>();
        result = new ArrayList<>();
        dist = new int[request.nodes.size()];
        prev = new int[request.nodes.size()];
        settled = new ArrayList<>();
        pq = new PriorityQueue<SimpleNode>(request.nodes.size(), new SimpleNode());
        adj = new ArrayList<List<SimpleNode>>();
        nodesIdsNormalization = new Hashtable<>();
        reverseNodesIdsNormalization = new Hashtable<>();
        //Normalize Nodes Ids
        int index =-1;
        for(var node:request.nodes)
        {
            index++;
            nodesIdsNormalization.put(node.getId(),index);
            reverseNodesIdsNormalization.put(index,node.getId());
        }

        // Initialize list for every node
        for (int i = 0; i < request.nodes.size(); i++) {
            List<SimpleNode> item = new ArrayList<SimpleNode>();
            adj.add(item);
        }

        prev[nodesIdsNormalization.get(request.node_from.getId())] = -1;

        //Create adjency list
        for(var edge:request.edges)
        {
            Integer nodeFromNormalizedId = nodesIdsNormalization.get( edge.getNode_from());
            Integer nodeToNormaizedId = nodesIdsNormalization.get( edge.getNode_to());

            adj.get(nodeFromNormalizedId).add(new SimpleNode(nodeToNormaizedId,edge.getValue()));
        }

        //Initialize array that contains distances from root to a node
        for (int i = 0; i < request.nodes.size(); i++)
            dist[i] = Integer.MAX_VALUE;
        // Distance to the source is 0
        dist[nodesIdsNormalization.get(request.node_from.getId())] = 0;

        // Add source node to the priority queue
        pq.add(new SimpleNode(nodesIdsNormalization.get(request.node_from.getId()), 0));


    }



}
