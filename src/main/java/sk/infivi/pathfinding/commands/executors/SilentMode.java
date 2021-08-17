package sk.infivi.pathfinding.commands.executors;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;

public class SilentMode implements CommandExecutor {

    private final Manager manager;

    public SilentMode(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (manager.isSilent()) {
            manager.setSilent(false);
            sender.sendMessage(Component.text("Not silent anymore!", GlobalStyles.success));
        } else {
            sender.sendMessage(Component.text("Now silent.", GlobalStyles.silentInfo));
            manager.setSilent(true);
        }

        return true;
    }
}
