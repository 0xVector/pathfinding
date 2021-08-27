package sk.infivi.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sk.infivi.pathfinding.commands.executors.*;
import sk.infivi.pathfinding.events.BlockChange;

import java.util.logging.Logger;

public final class Pathfinding extends JavaPlugin {

    Logger logger = Bukkit.getLogger();
    Manager manager;

    @Override
    public void onEnable() {

        logger.info("Pathfinding enabled!");
        manager = new Manager(this);

        // Command registration
        getCommand("pathfinding").setExecutor(new ChatMenu(manager));
        getCommand("startsearch").setExecutor(new StartSearch(manager));
        getCommand("algorithm").setExecutor(new SetAlgorithm(manager));
        getCommand("selectblockplane").setExecutor(new SelectBlockPlane(manager));
        getCommand("mode").setExecutor(new SetMode(manager));
        getCommand("speed").setExecutor(new SetSpeed(manager));
        getCommand("getitems").setExecutor(new GetItems());
        getCommand("clearplane").setExecutor(new ClearPlane(manager));
        getCommand("silent").setExecutor(new SilentMode(manager));
        getCommand("refresh").setExecutor(new RefreshMode(manager));
        getCommand("random").setExecutor(new RandomMode(manager));

        // Listener registration
        getServer().getPluginManager().registerEvents(new BlockChange(manager), this);
    }

    @Override
    public void onDisable() {
        logger.info("Pathfinding disabled!");
    }
}