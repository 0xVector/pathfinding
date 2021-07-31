package sk.infivi.pathfinding.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sk.infivi.pathfinding.Manager;

public class ClearPlane implements CommandExecutor {

    Manager manager;

    public ClearPlane(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (manager.getReady()) {
            manager.clearBlockPlane();
            sender.sendMessage(ChatColor.GREEN + "\nPlane cleared!\n");

        } else {
            sender.sendMessage(ChatColor.RED + "You have to select the block plane first.");
        }
        return true;
    }
}
