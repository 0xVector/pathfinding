package sk.infivi.pathfinding;

import org.bukkit.Location;
import org.bukkit.World;
import sk.infivi.pathfinding.algorithms.PathFindingAlgorithmResult;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithm;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;
import sk.infivi.pathfinding.commands.Callback;
import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.visualization.BlockPlane;
import sk.infivi.pathfinding.visualization.DrawMode;
import sk.infivi.pathfinding.visualization.PathDrawer;

public class Manager {

    private final Pathfinding plugin;
    private final Settings settings;

    public Manager(Pathfinding plugin) {
        this.plugin = plugin;
        this.settings = new Settings();
    }

    public void setAlgorithmType(PathfindingAlgorithmType algorithmType) {
        settings.algorithm = algorithmType;
    }
    public PathfindingAlgorithmType getAlgorithmType() {
        return settings.algorithm;
    }

    public void setBlockPlane(World world, int yLevel, int x1, int z1, int x2, int z2) {
        settings.blockPlane = new BlockPlane(new Location(world, x1, yLevel, z1), new Location(world, x2, yLevel, z2));
    }
    public BlockPlane getBlockPlane() {
        return settings.blockPlane;
    }

    public void setDrawMode(DrawMode drawMode) {
        settings.drawMode = drawMode;
    }
    public DrawMode getDrawMode() {
        return settings.drawMode;
    }

    public void setSpeed(int speed) {
        if (speed == 0) {
            settings.speed = 0;

        } else {
            settings.speed = 10 / speed;
        }
    }
    public int getSpeed() {
        if (settings.speed == 0) {
            return 0; // TODO: this whole system is stupid, redo
        }
        return 10 / settings.speed;
    }

    public boolean isSilent() {
        return settings.silent;
    }
    public void setSilent(boolean silent) {
        settings.silent = silent;
    }

    public boolean getRefresh() {
        return settings.refresh;
    }
    public void setRefresh(boolean refresh) {
        settings.refresh = refresh;
    }

    public boolean getReady() {  // Check if we have all information needed
        return settings.algorithm != null && settings.blockPlane != null && settings.drawMode != null
                && settings.blockPlane.getGraph().checkGraph(); //TODO: better way
    }

    public PathFindingAlgorithmResult runSearch() {
        settings.blockPlane.clear();
        settings.blockPlane.setModified(); // Refresh, just to be sure

        Graph graph = settings.blockPlane.getGraph();
        PathfindingAlgorithm algorithm = settings.algorithm.getAlgorithm();

        algorithm.run(graph);

        return algorithm.getResult();
    }

    public void drawPath(PathFindingAlgorithmResult result, Callback callback) {
        if (result.successful) { // TODO: unsuccessful results drawing
            PathDrawer pathDrawer = new PathDrawer(result, settings.drawMode, callback);
            pathDrawer.runTaskTimer(plugin, 0, settings.speed);
        }
    }

    public void clearBlockPlane() {
        settings.blockPlane.clear();
    }
}
