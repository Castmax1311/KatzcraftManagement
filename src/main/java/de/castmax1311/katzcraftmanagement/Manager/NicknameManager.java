package de.castmax1311.katzcraftmanagement.Manager;

import org.bukkit.ChatColor;
import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class NicknameManager {

    private Main plugin;
    private FileConfiguration config;

    public NicknameManager(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void setNickname(Player player, String nickname) {
        config.set("nicknames." + player.getUniqueId(), nickname);
        plugin.saveConfig(); // Save the configuration to the disk
    }

    public String getNickname(Player player) {
        return config.getString("nicknames." + player.getUniqueId());
    }

    public ChatColor getNameColor(Player player) {
        String colorName = config.getString("namecolors." + player.getUniqueId());
        if (colorName != null) {
            try {
                return ChatColor.valueOf(colorName);
            } catch (IllegalArgumentException e) {
                // Invalid color in the config, return default color
            }
        }
        return null; // Default color, white
    }

    public void setNameColor(Player player, ChatColor color) {
        config.set("namecolors." + player.getUniqueId(), color.name());
        plugin.saveConfig(); // Save the configuration to the disk
    }

    public void loadNicknames() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }
}
