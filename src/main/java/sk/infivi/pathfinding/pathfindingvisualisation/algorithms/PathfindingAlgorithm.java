package sk.infivi.pathfinding.pathfindingvisualisation.algorithms;

import sk.infivi.pathfinding.pathfindingvisualisation.graph.Graph;

public interface PathfindingAlgorithm {
    void run(Graph graph);
    PathFindingAlgorithmResult getResult();
}
