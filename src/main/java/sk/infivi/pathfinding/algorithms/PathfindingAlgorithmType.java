package sk.infivi.pathfinding.algorithms;

public enum PathfindingAlgorithmType {

    BFS (new BFS(), "BFS", "Standard BFS."),
    GREEDY_BFS (new GreedyBFS(), "Greedy-BFS", "A greedy variant of BFS."),
    A_STAR (new BFS(), "A*", "A* desc"),
    DIJKSTRA (new BFS(), "Dijkstra", "Dijksra desc");

    public final PathfindingAlgorithm algorithm;
    public final String name;
    public final String description;

    PathfindingAlgorithmType(PathfindingAlgorithm algorithm, String name, String description) {
        this.algorithm = algorithm;
        this.name = name;
        this.description = description;
    }

    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }
}
