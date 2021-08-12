package sk.infivi.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sk.infivi.pathfinding.commands.*;

import java.util.logging.Logger;

public final class Pathfinding extends JavaPlugin {

    Logger logger = Bukkit.getLogger();
    Manager manager;

    @Override
    public void onEnable() {

        logger.info("Pathfinding enabled!");
        manager = new Manager(this);

        // command registration
        getCommand("options").setExecutor(new Options(manager));
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
        logger.info("Pathfinding disabled!");
    }
}