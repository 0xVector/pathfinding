package sk.infivi.pathfinding.pathfindingvisualisation.algorithms;

import sk.infivi.pathfinding.pathfindingvisualisation.graph.Graph;
import sk.infivi.pathfinding.pathfindingvisualisation.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS implements PathfindingAlgorithm {

    private HashMap<Node, Boolean> nodeVisited;
    private HashMap<Node, Integer> distanceToNode;
    private HashMap<Node, Node> previousNode;
    private ArrayList<Node> visitedInOrder;
    private Node start, end;

    @Override
    public void run(Graph graph) {

        Queue<Node> queue = new LinkedList<>();
        nodeVisited = new HashMap<>();
        distanceToNode = new HashMap<>();
        previousNode = new HashMap<>();
        visitedInOrder = new ArrayList<>();

        start = graph.getStartNode();
        end = graph.getEndNode();
        
        queue.add(start);
        nodeVisited.put(start, true);
        distanceToNode.put(start, 0);
        previousNode.put(start, null);
        visitedInOrder.add(start);

        Node node;

        while (!queue.isEmpty()) {
            node = queue.remove();

            for (Node adjacent : node.getAdjacentNodesAndDistance().keySet()) {
                if (nodeVisited.getOrDefault(adjacent, false)) {
                    continue;
                }

                queue.add(adjacent);
                nodeVisited.put(adjacent, true);
                visitedInOrder.add(node);

                // Own distance + distance from self to adjacent
                distanceToNode.put(adjacent, distanceToNode.get(node) + node.getAdjacentNodesAndDistance().get(adjacent));
                previousNode.put(adjacent, node);

                if (adjacent == end) {
                    return;
                }
            }
        }
    }

    @Override
    public PathFindingAlgorithmResult getResult() {
        return new PathFindingAlgorithmResult(start, end, visitedInOrder, distanceToNode, previousNode);
    }
}