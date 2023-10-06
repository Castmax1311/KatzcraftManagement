package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import de.castmax1311.katzcraftmanagement.commands.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateListener implements Listener {

    private final Main plugin;

    public UpdateListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().isOp()) {
            new UpdateChecker(plugin, 111324).getVersion(version -> {
                if (!plugin.getDescription().getVersion().equals(version)) {
                    event.getPlayer().sendMessage(Main.formatMessage(ChatColor.GREEN + "New update available! Download the latest version on Spigot: " + ChatColor.BLUE + "https://www.spigotmc.org/resources/katzcraftmanagement.111324/"));
                }
            });
        }
    }
}