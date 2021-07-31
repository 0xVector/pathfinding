package sk.infivi.pathfinding.graph;

import org.bukkit.Effect;
import org.bukkit.Location;

import java.util.*;

public class Node {

    public final Location location;
    public final NodeType type;
    private final Map<Node, Integer> adjacentNodesAndDistance;

    public NodeState state = NodeState.NORMAL;

    public Node(Location location, NodeType nodeType){
        this.location = location;
        this.type = nodeType;
        adjacentNodesAndDistance = new HashMap<>();
    }

    public void addAdjacentNode(Node destination, int distance) {
        adjacentNodesAndDistance.put(destination, distance);
    }

    public Map<Node, Integer> getAdjacentNodesAndDistance() {
        return adjacentNodesAndDistance;
    }

    public void setState(NodeState state, boolean effect) {
        this.state = state;

        if (effect) {
            location.getWorld().playEffect(location, Effect.STEP_SOUND, 11);
        }

        location.getBlock().setType(type.getBlockForState(state));
    }
}