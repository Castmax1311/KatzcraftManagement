package de.castmax1311.katzcraftmanagement.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MessagecolorListener implements Listener {
    private static final Map<Player, ChatColor> messageColors = new HashMap<>();

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ChatColor color = messageColors.getOrDefault(player, ChatColor.WHITE);
        event.setMessage(color + event.getMessage());
    }

    public static void setColor(Player player, ChatColor color) {
        messageColors.put(player, color);
    }
}