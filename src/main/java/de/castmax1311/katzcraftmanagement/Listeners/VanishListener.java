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
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "You are now in vanish mode"));
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.hidePlayer(Main.instance, player);
            }
            player.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " left the game");
        } else if (!vanished && vanishedPlayers.contains(player)) {
            vanishedPlayers.remove(player);
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You are no longer in vanish mode"));
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(Main.instance, player);
            }
            player.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " joined the game");
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