package city.genkoku.spaceworld.Listener;


import city.genkoku.spaceworld.SpaceWorld;
import com.bergerkiller.bukkit.common.events.EntityAddEvent;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class EntityListener implements Listener {

    private static final Set<EntityType> ENTITY_TYPE_SET = new HashSet<>();

    private SpaceWorld plugin;
    private Logger logger;

    static {
        ENTITY_TYPE_SET.add(EntityType.MINECART);
        ENTITY_TYPE_SET.add(EntityType.MINECART_CHEST);
        ENTITY_TYPE_SET.add(EntityType.MINECART_FURNACE);
        ENTITY_TYPE_SET.add(EntityType.MINECART_HOPPER);
        ENTITY_TYPE_SET.add(EntityType.MINECART_TNT);
        ENTITY_TYPE_SET.add(EntityType.MINECART_MOB_SPAWNER);
        ENTITY_TYPE_SET.add(EntityType.PRIMED_TNT);
        ENTITY_TYPE_SET.add(EntityType.BOAT);
    }

    public EntityListener(SpaceWorld plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        logger.info("EntityListener resisted");
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (plugin.isDebug()) {
            logger.info("CreatureSpawnEvent");
        }
        Entity entity = event.getEntity();
        Location location = event.getLocation();
        addNoGravityEntity(entity, location);
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if (plugin.isDebug()) {
            logger.info("ItemSpawnEvent");
        }
        Entity entity = event.getEntity();
        Location location = event.getLocation();
        addNoGravityEntity(entity, location);
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if (plugin.isDebug()) {
            logger.info("PlayerDropItemEvent");
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

    @EventHandler
    public void onEntityAdd(EntityAddEvent event){
        if (plugin.isDebug()) {
            logger.info("EntityAddEvent");
        }
        EntityType entityType = event.getEntityType();
        if (ENTITY_TYPE_SET.contains(entityType)){
            Entity entity = event.getEntity();
            Location location = entity.getLocation();
            addNoGravityEntity(entity, location);
        }
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
