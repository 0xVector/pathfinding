package sk.infivi.pathfinding.algorithms;

public enum PathfindingAlgorithmType {

    BFS (new BFS()),
    DIJKSTRA (new BFS()),
    A_STAR (new BFS());

    private final PathfindingAlgorithm algorithm;

    PathfindingAlgorithmType(PathfindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }
}
