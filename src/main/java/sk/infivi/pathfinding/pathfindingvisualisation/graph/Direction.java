package sk.infivi.pathfinding.pathfindingvisualisation.graph;

public enum Direction {
    UP (0, 1),
    DOWN (0, -1),
    RIGHT (1, 0),
    LEFT (-1 ,0);

    public int relativeX;
    public int relativeZ;
    Direction(int relativeX, int relativeZ) {
        this.relativeX = relativeX;
        this.relativeZ = relativeZ;
    }
}
