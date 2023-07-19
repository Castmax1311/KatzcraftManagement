package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MuteListener implements Listener {

    private static final Map<UUID, Long> mutedPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (mutedPlayers.containsKey(playerUUID) && System.currentTimeMillis() < mutedPlayers.get(playerUUID)) {
            event.setCancelled(true);
            player.sendMessage("You are currently muted and cannot send messages in chat");
        }
    }

    public static void mutePlayer(Player player, long durationInSeconds) {
        long muteTime = System.currentTimeMillis() + (durationInSeconds * 1000);
        mutedPlayers.put(player.getUniqueId(), muteTime);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            unmutePlayer(player);
            player.sendMessage("You are no longer muted");
        }, durationInSeconds * 20L);

        player.sendMessage("You have been muted for " + durationInSeconds + " seconds");
    }

    private static void unmutePlayer(Player player) {
        mutedPlayers.remove(player.getUniqueId());
    }

    public static boolean isMuted(Player player) {
        return mutedPlayers.containsKey(player.getUniqueId()) && System.currentTimeMillis() < mutedPlayers.get(player.getUniqueId());
    }
}

