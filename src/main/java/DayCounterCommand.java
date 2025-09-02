import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DayCounterCommand implements CommandExecutor {
    private final DayCounter plugin;

    public DayCounterCommand(DayCounter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        String dayCountMessage = colorize(config.getString("daycount-message", "&7Current day: &e%day%"));

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("daycounter.reload")) {
                    plugin.reload();
                    sender.sendMessage(colorize("&7[&4DayCounter&7]: &fPlugin configuration reloaded."));
                } else {
                    sender.sendMessage(colorize("&7[&4DayCounter&7]: &fYou don't have permission to use this command."));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("help")) {
                sendHelp(sender);
                return true;
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                long day = player.getWorld().getFullTime() / 24000;
                player.sendMessage(dayCountMessage.replace("%day%", String.valueOf(day)));
            } else {
                sender.sendMessage(colorize("&cThis command can only be executed by a player."));
            }
            return true;
        }

        sender.sendMessage(colorize("&cUsage: /daycount"));
        return false;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(colorize("&7-------[&4DayCounter&7]-------"));
        sender.sendMessage(colorize("&7/daycount - Get current day"));
        sender.sendMessage(colorize("&7/daycounter reload - Reload plugin configuration"));
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
