package genkoku.city.spaceworld.Listener;

import de.tr7zw.itemnbtapi.NBTEntity;
import genkoku.city.spaceworld.SpaceWorld;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.logging.Logger;

public class EntityListener implements Listener {
    private SpaceWorld plugin;
    private Logger logger;

    public EntityListener(SpaceWorld plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        logger.info("EntityListener resisted");
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (plugin.isDebug()) {
            logger.info("onEntitySpawnEvent");
        }
        Entity entity = event.getEntity();
        Location location = event.getLocation();
        addNoGravityEntity(entity, location);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if (plugin.isDebug()) {
            logger.info("onItemSpawnEvent");
        }
        Entity entity = event.getEntity();
        Location location = event.getLocation();
        addNoGravityEntity(entity, location);
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if (plugin.isDebug()) {
            logger.info("onPlayerItemDrop");
        }
        Entity entity = event.getItemDrop();
        Location location = event.getPlayer().getLocation();
        addNoGravityEntity(entity, location);
    }

    @EventHandler
    public void onProjectileThrownEvent(ProjectileLaunchEvent event) {
        if (plugin.isDebug()) {
            logger.info("onProjectileThrownEvent");
        }
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        addNoGravityEntity(entity, location);
    }

    private void addNoGravityEntity(Entity entity, Location location) {
        if (plugin.getWorldSet().contains(location.getWorld().getName()) && !(entity instanceof Player)) {
            NBTEntity nbtEntity = new NBTEntity(entity);
            nbtEntity.setInteger("NoGravity", 1);
            if (plugin.isDebug()) {
                logger.info("Entity: " + entity.getName() + " effected");
            }
        }
    }
}
