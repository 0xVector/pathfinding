package sk.infivi.pathfinding.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import sk.infivi.pathfinding.Manager;
import sk.infivi.pathfinding.visualization.BlockPlane;

public class BlockChange implements Listener {

    private final Manager manager;

    public BlockChange(Manager manager) {
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockChange(BlockBreakEvent event) {
        commonEventHandler(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockChange(BlockPlaceEvent event) {
        commonEventHandler(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockChange(BlockExplodeEvent event) {
        commonEventHandler(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockChange(EntityChangeBlockEvent event) {
        commonEventHandler(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockChange(EntityExplodeEvent event) {
        commonEventHandler(event);
    }

    // Handle all sorts of block events
    private void commonEventHandler(Event event) {

        Location location = null;

        if (event instanceof BlockEvent) {
            location = ((BlockEvent) event).getBlock().getLocation();

        } else if (event instanceof EntityChangeBlockEvent) {
            location = ((EntityChangeBlockEvent) event).getBlock().getLocation();

        } else if (event instanceof EntityExplodeEvent) {

            for (Block block : ((EntityExplodeEvent) event).blockList()) {

                if (inBlockPlane(block.getLocation())) {
                    manager.getBlockPlane().setModified();
                    return;
                }
            }

        } else {
            return;
        }

        assert location != null;
        if (inBlockPlane(location)) {
            manager.getBlockPlane().setModified();
        }
    }

    private boolean inBlockPlane(Location location) {
        BlockPlane blockPlane = manager.getBlockPlane();

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return y == blockPlane.yLevel
                && blockPlane.xCoord1 <= x
                && x <= blockPlane.xCoord2
                && blockPlane.zCoord1 <= z
                && z <= blockPlane.zCoord2;
    }
}
