package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NicknameListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // On player join, check if the player has a saved nickname and set it
        Player player = event.getPlayer();
        String savedNickname = Main.instance.getNicknameManager().getNickname(player);

        if (savedNickname != null) {
            player.setDisplayName(savedNickname);
            player.setPlayerListName(savedNickname);
        }
    }
}