package sk.infivi.pathfinding.visualization;

import org.bukkit.Location;
import org.bukkit.World;
import sk.infivi.pathfinding.graph.*;

public class BlockPlane {

    public final World world;
    public final int yLevel;
    public final int x1;
    public final int z1;
    public final int x2;
    public final int z2;

    private Graph graph;
    private boolean modified = false;

    public BlockPlane(Location point1, Location point2) {
        this.world = point1.getWorld();
        this.yLevel = point1.getBlockY();
        this.x1 = Math.min(point1.getBlockX(), point2.getBlockX());
        this.x2 = Math.max(point1.getBlockX(), point2.getBlockX());
        this.z1 = Math.min(point1.getBlockZ(), point2.getBlockZ());
        this.z2 = Math.max(point1.getBlockZ(), point2.getBlockZ());

        assert(point1.getWorld() == point2.getWorld());  // TODO: unnecessary?
        assert(point1.getBlockY() == point2.getBlockY());
    }

    public void setModified() {
        modified = true;
    }

    public void clear() {
        for (Node node : getGraph().getNodes()) {
            node.setState(NodeState.NORMAL, false);
        }
    }

    public Graph getGraph() {

        if (modified || graph == null) {
            graph = new Graph();
            modified = false;

            for (int x = x1; x <= x2; x++) {
                for (int z = z1; z <= z2; z++) {
                    NodeType type = NodeType.getTypeFromMaterial(world.getBlockAt(x, yLevel, z).getType());
                    if (type != NodeType.OTHER) {
                        graph.addNode(new Node(new Location(world, x, yLevel, z), type));
                    }
                }
            }

            for (Node node : graph.getNodes()) {
                for (Direction direction : Direction.values()) {
                    checkAndAddDirection(node, direction, graph);
                }
            }
        }
        return graph;
    }

    private void checkAndAddDirection(Node node, Direction direction, Graph graph) {
        Node adjacentNode = graph.getNodeAt(node.location.getBlockX() + direction.relativeX,
                node.location.getBlockZ() +  direction.relativeZ);

        if (adjacentNode != null && adjacentNode.type.walkable) {
            node.addAdjacent(adjacentNode, 1);
        }
    }
}