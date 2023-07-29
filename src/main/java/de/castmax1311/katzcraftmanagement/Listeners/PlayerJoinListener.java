package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.clip.placeholderapi.PlaceholderAPI;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Main.instance.isMaintenanceMode() && !player.isOp()) {
            player.kickPlayer(ChatColor.RED + "This server is under maintenance");
            return;
        }

        if (Main.instance.getConfig().getString("customJoinMessage") != null) {
            String joinText = Main.instance.getConfig().getString("customJoinMessage");
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
            }
            event.setJoinMessage(joinText);
        }

        // Fetch the saved nickname for the player from the NicknameManager
        String savedNickname = Main.instance.getNicknameManager().getNickname(player);

        // Set the player's display name to their nickname (if it exists)
        if (savedNickname != null) {
            player.setDisplayName(savedNickname);
        }
        if (savedNickname != null) {
            player.setPlayerListName(savedNickname);
        }

        // Update the player list (tab list) name and the name above the player's head
        updatePlayerName(player, savedNickname);
    }

    // Method to update the player's name in the player list and above the player's head
    private void updatePlayerName(Player player, String nickname) {
        // The code from the previous response to update the player name in the scoreboard
        // should be added here again, as it was previously in the Main class.
    }
}
