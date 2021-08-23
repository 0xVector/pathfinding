package sk.infivi.pathfinding.commands.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

public class SetSpeed implements CommandExecutor {

    private final Manager manager;

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

            if (!manager.isSilent()) {
                sender.sendMessage(
                    GlobalStyles.getOptionSetComponent("Speed set: ", String.valueOf(speed)));
            }

            if (manager.getRefresh() && sender instanceof Player) {
                ((Player) sender).performCommand("options");
            }

            return true;
        }

        return false;
    }
}
