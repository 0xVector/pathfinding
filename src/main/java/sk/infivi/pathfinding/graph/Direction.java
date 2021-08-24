package sk.infivi.pathfinding.graph;

public enum Direction {
    UP (0, 1),
    DOWN (0, -1),
    RIGHT (1, 0),
    LEFT (-1 ,0);

    public final int relativeX;
    public final int relativeZ;
    Direction(int relativeX, int relativeZ) {
        this.relativeX = relativeX;
        this.relativeZ = relativeZ;
    }
}
