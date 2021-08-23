package sk.infivi.pathfinding.visualization;

import org.bukkit.scheduler.BukkitRunnable;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.algorithms.PathFindingAlgorithmResult;
import sk.infivi.pathfinding.commands.Callback;
import sk.infivi.pathfinding.graph.Node;
import sk.infivi.pathfinding.graph.NodeState;

import java.util.List;

public class PathDrawer extends BukkitRunnable {

    private final PathFindingAlgorithmResult result;
    private final Callback callback;
    private final Manager manager;

    private Node previous;
    private int pathStep = 0;
    private int visitsStep = 0;

    public PathDrawer(PathFindingAlgorithmResult result, DrawMode mode, Callback callback, Manager manager) {
        this.result = result;
        this.callback = callback;
        this.manager = manager;

        switch (mode) {
            case PATH_ONLY -> visitsStep = result.visitedInOrder.size() + 1;  // Consider all visits drawn
            case VISITS_ONLY -> pathStep = result.path.size() + 1;  // Consider path drawn
        }
    }

    @Override
    public void run() {
        if (manager.getSpeed() == 0) { // Draw everything at once
            while (!isCancelled()) {
                step();
            }

        } else {
            int repeat = Math.max(manager.getSpeed() - 5, 1);  // For every speed level >5, draw one more step
            for (int i = 0; i < repeat; i++) {
                step();
            }
        }
    }

    private void step() {
        // Everything drawn
        if (visitsStep > result.visitedInOrder.size() && pathStep > result.path.size()) {
            cancel();
            callback.callback();
            return;
        }

        // Drawing visits
        if (visitsStep <= result.visitedInOrder.size()) {

            drawBlock(result.visitedInOrder, visitsStep, NodeState.VISITED);
            visitsStep++;

            // Drawing path
        } else { // Else if more readable?

            // First path step
            if (pathStep == 0) {
                drawBlock(result.path, pathStep, NodeState.VISITED);  // Finish that last visited node

            } else {
                drawBlock(result.path, pathStep, NodeState.PATH);
            }

            pathStep++;
        }
    }

    private void drawBlock(List<Node> sequence, int step, NodeState state) {
        if (previous != null) {
            previous.setState(state, false);
        }

        if (step < sequence.size()) {
            previous = sequence.get(step);
            previous.setState(NodeState.SELECTED, true);
        }
    }
}
