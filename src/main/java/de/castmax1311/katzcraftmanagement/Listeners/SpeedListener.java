package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.File;
import java.io.IOException;

public class SpeedListener implements Listener {

    private File playerFile;
    private FileConfiguration playerConfig;

    public SpeedListener() {
        playerFile = new File(Main.instance.getDataFolder(), "players.yml");
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        if (playerConfig.contains(playerUUID)) {
            float flySpeed = (float) playerConfig.getDouble(playerUUID + ".fly", 0.1);
            float walkSpeed = (float) playerConfig.getDouble(playerUUID + ".walk", 0.2);
            player.setFlySpeed(flySpeed);
            player.setWalkSpeed(walkSpeed);
        } else {
            player.setFlySpeed(0.1f);
            player.setWalkSpeed(0.2f);
            playerConfig.set(playerUUID + ".fly", 0.1);
            playerConfig.set(playerUUID + ".walk", 0.2);
            savePlayerConfig();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        playerConfig.set(playerUUID + ".fly", player.getFlySpeed());
        playerConfig.set(playerUUID + ".walk", player.getWalkSpeed());
        savePlayerConfig();
    }

    private void savePlayerConfig() {
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
