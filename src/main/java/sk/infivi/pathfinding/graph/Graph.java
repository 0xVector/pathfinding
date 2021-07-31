package sk.infivi.pathfinding.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

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

    public void printGraph() {
        System.out.println("before loop: size: " + nodes.size());
        for (Node node : nodes) {
            System.out.println("adj nodes: " + node.getAdjacentNodesAndDistance().keySet().size());
            for (Node adj : node.getAdjacentNodesAndDistance().keySet()) {
                System.out.println((node.location.getX() * 1000 + Math.abs(node.location.getZ())) + " " + (adj.location.getX() * 1000 + Math.abs(adj.location.getZ())));
            }
        }
        System.out.println("after loop");
    }
}
