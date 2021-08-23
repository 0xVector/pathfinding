package sk.infivi.pathfinding.commands.executors;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

public class ClearPlane implements CommandExecutor {

    Manager manager;

    public ClearPlane(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (manager.getBlockPlane() != null) {
            manager.clearBlockPlane();

            if (!manager.isSilent()) {
                sender.sendMessage(Component.text("Plane cleared!", GlobalStyles.success));
            }

        } else {
            if (!manager.isSilent()) {
                sender.sendMessage(Component.text("You have to select the block plane first.", GlobalStyles.fail));
            }
        }

        if (manager.getRefresh() && sender instanceof Player) {
            ((Player) sender).performCommand("options");
        }

        return true;
    }
}
