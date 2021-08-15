package sk.infivi.pathfinding.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.algorithms.PathfindingAlgorithmType;

public class SetAlgorithm implements CommandExecutor {

    Manager manager;

    public SetAlgorithm(Manager manager) {

        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        PathfindingAlgorithmType algorithm = PathfindingAlgorithmType.BFS;

        // later we could send clickable bullshit

        if (args.length > 0) { // we have args

            String algorithmString = args[0];

            // TODO: Some smarter way

            if (algorithmString.equalsIgnoreCase("bfs")) {

                algorithm = PathfindingAlgorithmType.BFS;

            } else if (algorithmString.equalsIgnoreCase("greedy")
                    || algorithmString.equalsIgnoreCase("greedy-bfs")
                    || algorithmString.equalsIgnoreCase("greedy_bfs")) {

                algorithm = PathfindingAlgorithmType.GREEDY_BFS;

            } else if (algorithmString.equalsIgnoreCase("dijkstra")) {

                algorithm = PathfindingAlgorithmType.DIJKSTRA;

            } else if (algorithmString.equalsIgnoreCase("a_star")
                    || algorithmString.equalsIgnoreCase("a*")
                    || algorithmString.equalsIgnoreCase("astar")) {

                algorithm = PathfindingAlgorithmType.A_STAR;
            }

            manager.setAlgorithmType(algorithm);
            sender.sendMessage(ChatColor.GREEN + "Algorithm set: " + algorithm.toString());
            return true;
        }

        return false;
    }
}
