package sk.infivi.pathfinding.visualization;

import org.bukkit.Location;
import org.bukkit.World;
import sk.infivi.pathfinding.graph.Direction;
import sk.infivi.pathfinding.graph.Graph;
import sk.infivi.pathfinding.graph.Node;
import sk.infivi.pathfinding.graph.NodeType;

public class BlockPlane {

    public final World world;
    public final int yLevel;
    public final int xCoord1;
    public final int zCoord1;
    public final int xCoord2;
    public final int zCoord2;

    public BlockPlane(Location point1, Location point2) {
        this.world = point1.getWorld();
        this.yLevel = point1.getBlockY();
        this.xCoord1 = Math.min(point1.getBlockX(), point2.getBlockX());
        this.xCoord2 = Math.max(point1.getBlockX(), point2.getBlockX());
        this.zCoord1 = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.zCoord2 = Math.max(point1.getBlockZ(), point2.getBlockZ());

        assert(point1.getWorld() == point2.getWorld());
        assert(point1.getBlockY() == point2.getBlockY());
    }

    public void clear() { // TODO: unify with setHighlighted method in Node
        for (int x = xCoord1; x <= xCoord2; x++) {
            for (int z = zCoord1; z <= zCoord2; z++) {
                world.getBlockAt(x, yLevel, z).setType(NodeType.cleanBlockType(world.getBlockAt(x, yLevel, z).getType()));
            }
        }
    }

    public Graph getGraph() {
        // TODO validity check: multiple starts

        Graph graph = new Graph();

        for (int x = xCoord1; x <= xCoord2; x++) {
            for (int z = zCoord1; z <= zCoord2; z++) {
                NodeType type = NodeType.getTypeFromMaterial(world.getBlockAt(x, yLevel, z).getType());
                graph.addNode(new Node(new Location(world, x, yLevel, z), type));
            }
        }

        for (Node node : graph.getNodes()) {
            for (Direction direction : Direction.values()) {
                checkAndAddDirection(node, direction, graph);
            }
        }

        return graph;
    }

    private void checkAndAddDirection(Node node, Direction direction, Graph graph) {
        Node addAdjacentNode = graph.getNodeAt(node.location.getBlockX() + direction.relativeX,
                node.location.getBlockZ() +  direction.relativeZ);

        if (addAdjacentNode != null && addAdjacentNode.type.walkable) {
            node.addAdjacentNode(addAdjacentNode, 1);
        }
    }
}