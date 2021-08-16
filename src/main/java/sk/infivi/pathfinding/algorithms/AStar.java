package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Node;

public class AStar extends GreedyBFS implements PathfindingAlgorithm {

    @Override
    protected boolean shouldVisit(Node current, Node adjacent) {
        return distanceToNode.get(current) + 1 < distanceToNode.getOrDefault(adjacent, Integer.MAX_VALUE);
    }
}