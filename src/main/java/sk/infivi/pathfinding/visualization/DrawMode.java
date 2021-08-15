package sk.infivi.pathfinding.visualization;

import sk.infivi.pathfinding.commands.MenuOption;

public enum DrawMode implements MenuOption  {

    PATH_ONLY ("Path-only", "Draw only the final path."),
    VISITS_ONLY ("Visits-only", "Draw only visits the algorithm makes."),
    PATH_AND_VISITS ("All", "Draw everything.");

    private static final String command = "/mode";
    private final String name;
    private final String description;

    DrawMode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
