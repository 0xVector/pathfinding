package sk.infivi.pathfinding;

import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;

class Settings {
    public PathfindingAlgorithmType algorithm;
    public DrawMode drawMode;
    public BlockPlane blockPlane;
    public int speed = 5;  // 1-10, 0 means instant
    public boolean silent = false;
    public boolean refresh = false;
    public boolean random = true;
}
