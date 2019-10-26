package city.genkoku.spaceworld;

import city.genkoku.spaceworld.Listener.EntityListener;
import city.genkoku.spaceworld.Listener.PlayerListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class SpaceWorld extends JavaPlugin {

    private Set<String> worldSet = new HashSet<>();

    private boolean debug = false;

    @Override
    public void onEnable() {
        // Plugin startup logic

        loadConfig();
        getCommand(getName().toLowerCase()).setExecutor(new SpaceWorldCommand(this));
        getServer().getPluginManager().registerEvents(new EntityListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    void loadConfig() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        worldSet = new HashSet<>(config.getStringList("world"));
        debug = config.getBoolean("debug");
        getLogger().info("enable world: " + worldSet.toString());
        getLogger().info("debug: " + debug);
    }

    public Set<String> getWorldSet() {
        return worldSet;
    }

    public boolean isDebug() {
        return debug;
    }
}
