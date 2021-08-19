package sk.infivi.pathfinding.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    // TODO: Maybe store information about visited nodes directly in the graph?

    private final Set<Node> nodes;
    private Node startNode;
    private Node endNode;

    public Graph() {
        nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);

        if (node.type == NodeType.START)
            startNode = node;
        else if (node.type == NodeType.END)
            endNode = node;
    }

    public Set<Node> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public Node getNodeAt(int x, int z) {
        // TODO: better implementation - performance !!!
        for (Node node : nodes) {
            if (node.location.getX() == x && node.location.getZ() == z) {
                return node;
            }
        }
        return null;
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
