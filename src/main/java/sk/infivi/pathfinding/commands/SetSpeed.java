package sk.infivi.pathfinding.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;

public class SetSpeed implements CommandExecutor {

    private Manager manager;

    public SetSpeed(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length > 0) {

            int speed;

            try {
                speed = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {
                return false;
            }

            manager.setSpeed(speed);
            sender.sendMessage(ChatColor.GREEN + "Speed set: " + speed);
            return true;
        }

        return false;
    }
}
