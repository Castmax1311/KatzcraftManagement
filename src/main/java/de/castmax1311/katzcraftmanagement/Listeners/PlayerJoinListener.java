package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.clip.placeholderapi.PlaceholderAPI;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if(Main.instance.getConfig().getString("customJoinMessage") != null) {

            String joinText = Main.instance.getConfig().getString("customJoinMessage");

            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
                joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
            }

            event.setJoinMessage(joinText);
        }
    }
}