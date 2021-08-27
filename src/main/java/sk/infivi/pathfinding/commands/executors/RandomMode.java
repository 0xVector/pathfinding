package sk.infivi.pathfinding.commands.executors;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

public class RandomMode implements  CommandExecutor{

    private final Manager manager;

    public RandomMode(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        manager.setRandom(!manager.isRandom());

        if (!manager.isSilent()) {

            if (manager.isRandom()) {
                sender.sendMessage(Component.text("Randomness turned on.", GlobalStyles.success));
            } else {
                sender.sendMessage(Component.text("Randomness turned off.", GlobalStyles.quietInfo));
            }
        }

        if (manager.getRefresh() && sender instanceof Player) {
            ((Player) sender).performCommand("options");
        }

        return true;
    }
}
