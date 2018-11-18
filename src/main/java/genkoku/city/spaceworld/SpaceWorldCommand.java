package genkoku.city.spaceworld;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpaceWorldCommand implements CommandExecutor {
    private SpaceWorld plugin;

    SpaceWorldCommand(SpaceWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if ("reload".equalsIgnoreCase(args[0]) && sender.hasPermission("spaceworld.reload")) {
                plugin.loadConfig();
                return true;
            }
        }
        return false;
    }
}
