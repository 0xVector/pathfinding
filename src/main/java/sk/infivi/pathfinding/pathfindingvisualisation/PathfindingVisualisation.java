package sk.infivi.pathfinding.pathfindingvisualisation;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sk.infivi.pathfinding.pathfindingvisualisation.commands.*;

import java.util.logging.Logger;

public final class PathfindingVisualisation extends JavaPlugin {

    Logger logger = Bukkit.getLogger();
    Manager manager;

    @Override
    public void onEnable() {

        logger.info("Pathfinding Visualisation enabled!");
        manager = new Manager(this);

        // command registration
        getCommand("startsearch").setExecutor(new StartSearch(manager));
        getCommand("setalgorithm").setExecutor(new SetAlgorithm(manager));
        getCommand("selectblockplane").setExecutor(new SelectBlockPlane(manager));
        getCommand("mode").setExecutor(new SetMode(manager));
        getCommand("speed").setExecutor(new SetSpeed(manager));
        getCommand("getitems").setExecutor(new GetItems());
        getCommand("clearplane").setExecutor(new ClearPlane(manager));
    }

    @Override
    public void onDisable() {
        logger.info("Pathfinding Visualisation disabled!");
    }
}