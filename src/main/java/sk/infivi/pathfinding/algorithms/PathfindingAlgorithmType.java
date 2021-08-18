package sk.infivi.pathfinding.algorithms;

import sk.infivi.pathfinding.commands.MenuOption;

public enum PathfindingAlgorithmType implements MenuOption {

    BFS (new BFS(), "BFS", "Standard BFS"),
    GREEDY_BFS (new GreedyBFS(), "Greedy-BFS", "A greedy variant of BFS"),
    A_STAR (new AStar(), "A*", "A* pathfinding algorithm"),
    DFS (new DFS(), "DFS", "DFS pathfinding algorithm"),
    DIJKSTRA (new BFS(), "Dijkstra", "Dijkstra algorithm");

    private static final String command = "/algorithm";
    private final PathfindingAlgorithm algorithm;
    private final String name;
    private final String description;

    PathfindingAlgorithmType(PathfindingAlgorithm algorithm, String name, String description) {
        this.algorithm = algorithm;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCommandName() {
        return command;
    }

    public PathfindingAlgorithm getAlgorithm() {
        return algorithm;
    }
}
