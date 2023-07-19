package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.Set;

public class VanishListener implements Listener {

    private static final Set<Player> vanishedPlayers = new HashSet<>();

    public static boolean isVanished(Player player) {
        return vanishedPlayers.contains(player);
    }

    public static void setVanished(Player player, boolean vanished) {
        if (vanished && !vanishedPlayers.contains(player)) {
            vanishedPlayers.add(player);
            player.sendMessage(ChatColor.YELLOW + "Du bist nun im Vanish-Modus!");
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.hidePlayer(Main.instance, player);
            }
            player.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " hat den Server verlassen.");
        } else if (!vanished && vanishedPlayers.contains(player)) {
            vanishedPlayers.remove(player);
            player.sendMessage(ChatColor.YELLOW + "Du bist nicht mehr im Vanish-Modus!");
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(Main.instance, player);
            }
            player.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " hat den Server betreten.");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player vanishedPlayer : vanishedPlayers) {
            player.hidePlayer(Main.instance, vanishedPlayer);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (Player vanishedPlayer : vanishedPlayers) {
            player.hidePlayer(Main.instance, vanishedPlayer);
        }
    }
}