package sk.infivi.pathfinding.pathfindingvisualisation.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sk.infivi.pathfinding.pathfindingvisualisation.Manager;
import sk.infivi.pathfinding.pathfindingvisualisation.algorithms.PathFindingAlgorithmResult;

public class StartSearch implements CommandExecutor, Callback {

    private final Manager manager;
    private CommandSender sender;

    public StartSearch(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        this.sender = sender;

        if (manager.getReady()) {

            sender.sendMessage(ChatColor.GREEN + "\nSearch started!");
            PathFindingAlgorithmResult result = manager.runSearch();

            if (result.successful) {
                sender.sendMessage(ChatColor.GREEN + "...done!");
                sender.sendMessage(ChatColor.AQUA + "Distance: " + result.distanceToEndNode);

                sender.sendMessage(ChatColor.GOLD + "Drawing...");
                manager.drawPath(result, this);

            } else {
                sender.sendMessage(ChatColor.RED + "No path found.");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "You have to specify all information first.");
        }

        return true;
    }

    @Override
    public void callback() {
        if (sender != null) {
            sender.sendMessage(ChatColor.GOLD + "...done!\n");
        }
    }
}
