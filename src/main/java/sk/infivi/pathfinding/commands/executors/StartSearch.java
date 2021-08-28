package sk.infivi.pathfinding.commands.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.algorithms.PathFindingAlgorithmResult;
import sk.infivi.pathfinding.commands.Callback;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

import static net.kyori.adventure.text.Component.newline;
import static net.kyori.adventure.text.Component.text;

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

            if (!manager.isSilent()) {
                sender.sendMessage(
                    newline()
                    .append(text("Search started!", GlobalStyles.success))
                    .append(GlobalStyles.getSpace())
                    .append(text(String.format("(%s)", manager.getAlgorithmType().getName()),
                        GlobalStyles.chatOptionSetValue)));
            }
            PathFindingAlgorithmResult result = manager.runSearch();

            if (result.successful) {
                if (!manager.isSilent()) {
                    sender.sendMessage(text("...done!", GlobalStyles.success));
                    sender.sendMessage(text("Distance: ", GlobalStyles.infoName)
                        .append(text(result.distanceToEndNode, GlobalStyles.infoValue)));

                    sender.sendMessage(text("Drawing...", GlobalStyles.statusInfo));
                }

                manager.drawPath(result, this);

            } else {
                if (!manager.isSilent()) {
                    sender.sendMessage(text("No path found.", GlobalStyles.fail));
                }
            }

        } else {
            if (!manager.isSilent()) {
                sender.sendMessage(text("You have to specify all options first.", GlobalStyles.fail));
            }
        }

        return true;
    }

    @Override
    public void callback() {
        if (sender != null && !manager.isSilent()) {
            sender.sendMessage(text("...done!", GlobalStyles.statusInfo));
        }

        if (manager.getRefresh() && sender instanceof Player) {
            ((Player) sender).performCommand("options");
        }
    }
}
