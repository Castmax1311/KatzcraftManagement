package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {

        if(Main.instance.getConfig().getString("customLeaveMessage") != null) {

            String quitText = Main.instance.getConfig().getString("customLeaveMessage");

            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
                quitText = PlaceholderAPI.setPlaceholders(event.getPlayer(), quitText);
            }

            event.setQuitMessage(quitText);
        }
    }
}
