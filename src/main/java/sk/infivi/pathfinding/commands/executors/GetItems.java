package sk.infivi.pathfinding.commands.executors;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.infivi.pathfinding.commands.constants.BlockItems;
import sk.infivi.pathfinding.commands.constants.GlobalStyles;


public class GetItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            player.getInventory().addItem(BlockItems.floorBlock, BlockItems.wallBlock, BlockItems.startBlock, BlockItems.endBlock, BlockItems.cornerBlock);

        } else {
            sender.sendMessage(Component.text("You have to be a player.", GlobalStyles.fail));
        }

        return true;
    }
}
