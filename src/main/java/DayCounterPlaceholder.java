import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DayCounterPlaceholder extends PlaceholderExpansion {

    private final JavaPlugin plugin;

    public DayCounterPlaceholder(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "daycounter";
    }

    @Override
    public @NotNull String getAuthor() {
        return "4rft5";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) {
            return "";
        }

        if (identifier.equals("counter")) {
            long day = player.getWorld().getFullTime() / 24000;
            return String.valueOf(day);
        }

        return null;
    }
}
