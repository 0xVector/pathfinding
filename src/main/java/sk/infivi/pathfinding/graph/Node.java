package sk.infivi.pathfinding.graph;

import org.bukkit.Effect;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class Node {

    public final Location location;
    public final NodeType type;

    private final Map<Node, Integer> adjacentNodesByDistance;
    private NodeState state = NodeState.NORMAL;

    public Node(Location location, NodeType nodeType){
        this.location = location;
        this.type = nodeType;
        adjacentNodesByDistance = new HashMap<>();
    }

    public void addAdjacent(Node adjacent, int distance) {
        adjacentNodesByDistance.put(adjacent, distance);
    }

    public Map<Node, Integer> getAdjacentByDistance() {
        return adjacentNodesByDistance;
    }

    public void setState(NodeState state, boolean effect) {
        this.state = state;

        if (effect) {
            location.getWorld().playEffect(location, Effect.STEP_SOUND, 11);
        }

        location.getBlock().setType(type.getBlockForState(state));
    }

    public NodeState getState() {
        return state;
    }
}