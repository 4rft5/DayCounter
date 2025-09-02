import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class DayCounter extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("DayCounter plugin has been enabled!");

        // Save default config
        this.saveDefaultConfig();

        // Register /daycount command
        PluginCommand dayCountCommand = this.getCommand("daycount");
        if (dayCountCommand != null) {
            dayCountCommand.setExecutor(new DayCounterCommand(this));
        } else {
            getLogger().warning("Command /daycount not found in plugin.yml");
        }

        // Register PlaceholderAPI placeholders
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new DayCounterPlaceholder(this).register();
            getLogger().info("PlaceholderAPI placeholders registered.");
        } else {
            getLogger().warning("PlaceholderAPI not found! Placeholder functionality will be disabled.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("DayCounter plugin has been disabled!");
    }

    public void reload() {
        // Reload plugin configuration
        reloadConfig();
        getLogger().info("Plugin configuration reloaded!");
    }
}
