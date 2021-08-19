package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Node;

import java.util.Comparator;

public class DistanceHeuristic implements Comparator<Node> {

    private Node endNode;

    public DistanceHeuristic(Node endNode) {
        this.endNode = endNode;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Double.compare(
                Math.abs(node1.location.distance(endNode.location)),
                Math.abs(node2.location.distance(endNode.location)));
    }
}
