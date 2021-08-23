package sk.infivi.pathfinding;

import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;

public class Settings {
    public PathfindingAlgorithmType algorithm;
    public DrawMode drawMode;
    public BlockPlane blockPlane;
    public int speed = 1;
    public boolean silent = false;
    public boolean refresh = false;
}
