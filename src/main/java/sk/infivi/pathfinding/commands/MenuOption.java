package sk.infivi.pathfinding.commands;

public interface MenuOption {
    String getName();
    String getDescription();
    String getCommandName();

    default String getCommand() {
        return getCommandName() + " " + getName();
    }
}
