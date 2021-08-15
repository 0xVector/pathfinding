package sk.infivi.pathfinding;

import org.bukkit.Location;
import org.bukkit.World;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithm;
import sk.infivi.pathfinding.algorithms.PathFindingAlgorithmResult;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.commands.Callback;
import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;
import sk.infivi.pathfinding.visualization.PathDrawer;

public class Manager {

    private final Pathfinding plugin;

    private BlockPlane blockPlane;
    private PathfindingAlgorithmType algorithmType;
    private DrawMode drawMode;

    private int speed = 1;

    public Manager(Pathfinding plugin) {
        this.plugin = plugin;
    }

    public void setAlgorithmType(PathfindingAlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public PathfindingAlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public void setBlockPlane(World world, int yLevel, int x1, int z1, int x2, int z2) {
        blockPlane = new BlockPlane(new Location(world, x1, yLevel, z1), new Location(world, x2, yLevel, z2));
    }

    public BlockPlane getBlockPlane() {
        return blockPlane;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public void setSpeed(int speed) {
        if (speed == 0) {
            this.speed = 0;

        } else {
            this.speed = 10 / speed;
        }
    }

    public int getSpeed() {
        if (speed == 0) {
            return 0; // TODO: this whole system is stupid, redo
        }

        return 10 / speed;
    }

    public void clearBlockPlane() {
        blockPlane.clear();
    }

    public boolean getReady() {  // Check if we have all information needed
        return algorithmType != null && blockPlane != null && drawMode != null;
    }

    public PathFindingAlgorithmResult runSearch() {
        clearBlockPlane();
        Graph graph = blockPlane.getGraph();
        PathfindingAlgorithm algorithm = algorithmType.getAlgorithm();

        algorithm.run(graph);

        return algorithm.getResult();
    }

    public void drawPath(PathFindingAlgorithmResult result, Callback callback) {
        if (result.successful) { // TODO: unsuccessful results drawing
            PathDrawer pathDrawer = new PathDrawer(result, drawMode, callback);
            pathDrawer.runTaskTimer(plugin, 0, speed);
        }
    }

}
