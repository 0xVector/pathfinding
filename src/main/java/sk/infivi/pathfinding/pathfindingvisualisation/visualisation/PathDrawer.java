package sk.infivi.pathfinding.pathfindingvisualisation.visualisation;

import org.bukkit.scheduler.BukkitRunnable;
import sk.infivi.pathfinding.pathfindingvisualisation.algorithms.PathFindingAlgorithmResult;
import sk.infivi.pathfinding.pathfindingvisualisation.commands.Callback;
import sk.infivi.pathfinding.pathfindingvisualisation.graph.Node;
import sk.infivi.pathfinding.pathfindingvisualisation.graph.NodeState;

import java.util.List;

public class PathDrawer extends BukkitRunnable {

    private final PathFindingAlgorithmResult result;
    private final Callback callback;

    private Node previous;
    private int pathStep = 0;
    private int visitsStep = 0;

    public PathDrawer(PathFindingAlgorithmResult result, DrawMode mode, Callback callback) {
        this.result = result;
        this.callback = callback;

        switch (mode) {
            case PATH_ONLY:
                visitsStep = result.visitedInOrder.size() + 1;  // Consider all visits drawn
                break;

            case VISITS_ONLY:
                pathStep = result.distanceToEndNode + 1;  // Consider path drawn
                break;
        }
    }

    @Override
    public void run() {
        // Everything drawn
        if (visitsStep > result.visitedInOrder.size() && pathStep > result.path.size()) {
            cancel();
            callback.callback();
            return;
        }

        // Drawing visits
        if (visitsStep <= result.visitedInOrder.size()) {

            drawStep(result.visitedInOrder, visitsStep, NodeState.VISITED);
            visitsStep++;

        // Drawing path
        } else { // Else if more readable?

            // First path step
            if (pathStep == 0) {
                drawStep(result.path, pathStep, NodeState.VISITED);  // Finish that last visited node

            } else {
                drawStep(result.path, pathStep, NodeState.PATH);
            }

            pathStep++;
        }
    }

    private void drawStep(List<Node> sequence, int step, NodeState state) {
        if (previous != null) {
            previous.setState(state, false);
        }

        if (step < sequence.size()) {
            previous = sequence.get(step);
            previous.setState(NodeState.SELECTED, true);
        }
    }
}
