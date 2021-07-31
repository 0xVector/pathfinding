package sk.infivi.pathfinding.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GetItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            player.getInventory().addItem(BlockItems.floorBlock, BlockItems.wallBlock, BlockItems.startBlock, BlockItems.endBlock, BlockItems.cornerBlock);

        } else {
            sender.sendMessage("You have to be a player.");
        }

        return true;
    }
}
