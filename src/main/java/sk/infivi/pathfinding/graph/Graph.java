package sk.infivi.pathfinding.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    // TODO: Maybe store information about visited nodes directly in the graph?

    public Node startNode;
    public Node endNode;
    private Set<Node> nodes;

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
        return nodes;
    }

    public Node getNodeAt(int x, int z) {
        // TODO: better implementation !!!
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

    // TODO: better....
    public boolean checkGraph() {
        return startNode != null && endNode != null;
    }
}
