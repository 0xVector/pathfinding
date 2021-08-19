package sk.infivi.pathfinding.commands.executors;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

public class SelectBlockPlane implements CommandExecutor {

    private Manager manager;

    public SelectBlockPlane(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 5) { // both coordinates specified

            Player player = (Player) sender;
            World world = player.getWorld();

            int yLevel, x1, z1, x2, z2;

            // try to convert arguments to integer coordinates
            try {
                yLevel = Integer.parseInt(args[0]);

                x1 = Integer.parseInt(args[1]);
                z1 = Integer.parseInt(args[2]);

                x2 = Integer.parseInt(args[3]);
                z2 = Integer.parseInt(args[4]);
            }

            catch (NumberFormatException exception) {
                return false;
            }

            manager.setBlockPlane(world, yLevel, x1, z1, x2, z2);

            if (!manager.isSilent()) {
                sender.sendMessage(
                    GlobalStyles.getOptionSetComponent(
                    "Block plane set: ", String.format("[%d, %d] to [%d, %d] at y-level %d", x1, z1, x2, z2, yLevel)));
            }

            return true;

        } else {
            return false;
        }
    }
}
