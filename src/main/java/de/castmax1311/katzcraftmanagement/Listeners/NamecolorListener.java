package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class NamecolorListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ChatColor color = getColorFromConfig(player);

        if (color == null) {
            color = ChatColor.WHITE; // Default color, white
        }

        player.setDisplayName(color + player.getName() + ChatColor.RESET);
        player.setPlayerListName(color + player.getName() + ChatColor.RESET);
    }

    // Method to load the player's color from the configuration file
    private ChatColor getColorFromConfig(Player player) {
        File configFile = new File(Main.instance.getDataFolder(), "namecolors.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        String colorName = config.getString(player.getUniqueId().toString());

        if (colorName != null && !colorName.isEmpty()) {
            try {
                return ChatColor.valueOf(colorName);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
