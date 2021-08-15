package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.graph.Node;

import java.util.Comparator;

public class DistanceHeuristic implements Comparator<Node> {

    private Graph graph;

    public DistanceHeuristic(Graph graph) {
        this.graph = graph;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Double.compare(
                Math.abs(node1.location.distance(graph.endNode.location)),
                Math.abs(node2.location.distance(graph.endNode.location)));
    }
}
