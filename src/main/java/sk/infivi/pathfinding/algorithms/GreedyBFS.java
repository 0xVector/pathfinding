package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.graph.Node;

import java.util.*;

public class GreedyBFS implements PathfindingAlgorithm {

    private Queue<Node> queue;
    private HashMap<Node, Boolean> nodeVisited;
    private HashMap<Node, Integer> distanceToNode;
    private HashMap<Node, Node> previousNode;
    private ArrayList<Node> visitedInOrder;
    private Node start, end;

    @Override
    public void run(Graph graph) {

        queue = new PriorityQueue<>(new DistanceHeuristic(graph));
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
                visitedInOrder.add(adjacent);

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