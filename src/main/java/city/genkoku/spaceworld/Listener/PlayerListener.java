package city.genkoku.spaceworld.Listener;

import city.genkoku.spaceworld.SpaceWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Logger;

public class PlayerListener implements Listener {
    private SpaceWorld plugin;
    private Logger logger;

    public PlayerListener(SpaceWorld plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        logger.info("PlayerListener resisted");
    }

    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        if (plugin.isDebug()) {
            logger.info("player: " + event.getPlayer().getName() + " change to " + event.getPlayer().getWorld().getName());
        }
        if (plugin.getWorldSet().contains(event.getPlayer().getWorld().getName())) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 99999, 254, true));
            if (plugin.isDebug()) {
                logger.info("player: " + event.getPlayer().getName() + " effected");
            }
        } else {
            event.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
            if (plugin.isDebug()) {
                logger.info("player: " + event.getPlayer().getName() + " effect removed");
            }
        }
    }
}
