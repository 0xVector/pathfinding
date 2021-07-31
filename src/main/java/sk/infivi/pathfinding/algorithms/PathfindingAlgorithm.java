package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.graph.Graph;

public interface PathfindingAlgorithm {
    void run(Graph graph);
    PathFindingAlgorithmResult getResult();
}
