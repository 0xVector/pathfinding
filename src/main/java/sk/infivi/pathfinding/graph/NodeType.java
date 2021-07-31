package sk.infivi.pathfinding.graph;

import org.bukkit.Material;

import java.util.EnumMap;

import static org.bukkit.Material.*;

public enum NodeType {

    NORMAL (WHITE_CONCRETE, SEA_LANTERN, LIGHT_GRAY_CONCRETE, BLUE_CONCRETE, true),
    START (LIME_CONCRETE, LIME_CONCRETE, LIME_CONCRETE, GREEN_CONCRETE, true),
    END (PINK_CONCRETE, PINK_CONCRETE, PINK_CONCRETE, RED_CONCRETE, true),
    WALL (BLACK_CONCRETE, GRAY_CONCRETE, BLACK_CONCRETE, GRAY_CONCRETE, false),
    GAP (AIR, BLUE_STAINED_GLASS, LIGHT_BLUE_STAINED_GLASS, BLUE_STAINED_GLASS, false),
    OTHER (BEDROCK, BEDROCK, BEDROCK, BEDROCK,false);


    public final Material block;
    public final Material selectedBlock;
    public final Material visitedBlock;
    public final Material pathBlock;
    public final boolean walkable;

    private final EnumMap<NodeState, Material> stateToMaterial;

    NodeType(Material block, Material selectedBlock, Material visitedBlock, Material pathBlock, boolean walkable) {
        this.block = block;
        this.selectedBlock = selectedBlock;
        this.visitedBlock = visitedBlock;
        this.pathBlock = pathBlock;
        this.walkable = walkable;

        this.stateToMaterial = new EnumMap<>(NodeState.class);
        stateToMaterial.put(NodeState.NORMAL, block);
        stateToMaterial.put(NodeState.SELECTED, selectedBlock);
        stateToMaterial.put(NodeState.VISITED, visitedBlock);
        stateToMaterial.put(NodeState.PATH, pathBlock);
    }

    public Material getBlockForState(NodeState state) {
        return stateToMaterial.getOrDefault(state, OTHER.block);
    }

    public static NodeType getTypeFromMaterial(Material blockMaterial) {
        for (NodeType nodeType : values()) {
            if (nodeType.block == blockMaterial
                    || nodeType.selectedBlock == blockMaterial
                    || nodeType.visitedBlock == blockMaterial
                    || nodeType.pathBlock == blockMaterial) {

                return nodeType;
            }
        }
        return NodeType.OTHER;
    }

    public static Material cleanBlockType(Material highlightedBlock) {
        for (NodeType nodeType : values()) {
            if (nodeType.block == highlightedBlock
                    || nodeType.selectedBlock == highlightedBlock
                    || nodeType.visitedBlock == highlightedBlock
                    || nodeType.pathBlock == highlightedBlock) {

                return nodeType.block;
            }
        }
        return NodeType.OTHER.block;
    }
}
