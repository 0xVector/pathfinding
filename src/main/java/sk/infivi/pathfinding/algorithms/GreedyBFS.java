package sk.infivi.pathfinding.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

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