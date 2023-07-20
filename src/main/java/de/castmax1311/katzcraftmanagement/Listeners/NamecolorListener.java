package de.castmax1311.katzcraftmanagement.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class NamecolorListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ChatColor color = ChatColor.WHITE; // Default color, white

        // If you have a database or any other method to store player's custom name color, you can fetch it here and assign it to 'color' variable.

        player.setDisplayName(color + player.getName() + ChatColor.RESET);
        player.setPlayerListName(color + player.getName() + ChatColor.RESET);
    }
}