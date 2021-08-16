package sk.infivi.pathfinding.algorithms;

import java.util.*;

public class GreedyBFS extends BFS implements PathfindingAlgorithm {

    @Override
    protected void initialize() {
        queue = new PriorityQueue<>(new DistanceHeuristic(end));
        nodeVisited = new HashMap<>();
        distanceToNode = new HashMap<>();
        previousNode = new HashMap<>();
        visitedInOrder = new ArrayList<>();
    }
}