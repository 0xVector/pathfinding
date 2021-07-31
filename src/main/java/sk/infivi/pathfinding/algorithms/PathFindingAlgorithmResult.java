package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PathFindingAlgorithmResult {

    public ArrayList<Node> path;
    public List<Node> visitedInOrder;
    public int distanceToEndNode;
    public final boolean successful;

    public PathFindingAlgorithmResult(Node start, Node end, List<Node> visitedInOrder, Map<Node, Integer> distanceToNode, Map<Node, Node> previousNode) {
        this.visitedInOrder = visitedInOrder;

        successful = distanceToNode.containsKey(end);
        if (!successful)
            return;

        this.path = new ArrayList<>();
        this.distanceToEndNode = distanceToNode.get(end);
        Node node = end;

        path.add(end);
        while ( (node = previousNode.get(node)) != start ) {
            path.add(node);
        }
        path.add(start);
        Collections.reverse(path);
    }
}
