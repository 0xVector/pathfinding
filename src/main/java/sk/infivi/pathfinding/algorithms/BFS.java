package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS implements PathfindingAlgorithm {

    protected Queue<Node> queue;
    protected HashMap<Node, Boolean> nodeVisited;
    protected HashMap<Node, Integer> distanceToNode;
    protected HashMap<Node, Node> previousNode;
    protected ArrayList<Node> visitedInOrder;
    protected Node start, end;

    // Initialization of data structures
    protected void initialize() {
        queue = new LinkedList<>();
        nodeVisited = new HashMap<>();
        distanceToNode = new HashMap<>();
        previousNode = new HashMap<>();
        visitedInOrder = new ArrayList<>();
    }

    // Condition checked before visiting a neighbour
    protected boolean shouldVisit(Node current, Node adjacent) {
        return !nodeVisited.getOrDefault(adjacent, false);
    }

    @Override
    public void run(Graph graph) {

        start = graph.getStartNode();
        end = graph.getEndNode();

        initialize();

        queue.add(start);
        nodeVisited.put(start, true);
        distanceToNode.put(start, 0);
        previousNode.put(start, null);
        visitedInOrder.add(start);

        Node node;

        while (!queue.isEmpty()) {
            node = queue.remove();

            for (Node adjacent : node.getAdjacentNodesAndDistance().keySet()) {

                if (shouldVisit(node, adjacent)) {

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
    }

    @Override
    public PathFindingAlgorithmResult getResult() {
        return new PathFindingAlgorithmResult(start, end, visitedInOrder, distanceToNode, previousNode);
    }
}