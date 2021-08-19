package sk.infivi.pathfinding.commands.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;
import sk.infivi.pathfinding.visualization.DrawMode;

public class SetMode implements CommandExecutor {

    Manager manager;

    public SetMode(Manager manager) {

        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        DrawMode mode = DrawMode.PATH_ONLY;

        if (args.length > 0) { // We have args

            String modeString = args[0];

            if (modeString.equalsIgnoreCase("path")) {

                mode = DrawMode.PATH_ONLY;

            } else if (modeString.equalsIgnoreCase("visits")
                    || modeString.equalsIgnoreCase("visits-only")) {

                mode = DrawMode.VISITS_ONLY;

            } else if (modeString.equalsIgnoreCase("both")
                    || modeString.equalsIgnoreCase("all")
                    || modeString.equalsIgnoreCase("everything")) {

                mode = DrawMode.PATH_AND_VISITS;

            }

            manager.setDrawMode(mode);

            if (!manager.isSilent()) {
                sender.sendMessage(
                    GlobalStyles.getOptionSetComponent("Drawing mode set: ", mode.getName()));
            }
            return true;
        }

        return false;
    }
}
