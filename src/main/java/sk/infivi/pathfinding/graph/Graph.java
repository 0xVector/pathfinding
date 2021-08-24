package sk.infivi.pathfinding.graph;

import java.util.*;

public class Graph {

    private final Set<Node> nodes;
    private final Map<Integer, Map<Integer, Node>> nodesByCoords;
    private Node startNode;
    private Node endNode;

    public Graph() {
        nodes = new HashSet<>();
        nodesByCoords = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.add(node);

        if (node.type == NodeType.START)
            startNode = node;
        else if (node.type == NodeType.END)
            endNode = node;

        nodesByCoords.putIfAbsent(node.location.getBlockX(), new HashMap<>());
        nodesByCoords.get(node.location.getBlockX()).put(node.location.getBlockZ(), node);
    }

    public Set<Node> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public Node getNodeAt(int x, int z) {
        return nodesByCoords.getOrDefault(x, Collections.emptyMap()).get(z);
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public boolean checkGraph() {
        return startNode != null && endNode != null;
    }
}
