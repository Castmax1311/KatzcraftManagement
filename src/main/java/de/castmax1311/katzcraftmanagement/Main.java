package de.castmax1311.katzcraftmanagement;

import de.castmax1311.katzcraftmanagement.Listeners.PlayerJoinListener;
import de.castmax1311.katzcraftmanagement.Listeners.PlayerLeaveListener;
import de.castmax1311.katzcraftmanagement.Listeners.ServerListPingListener;
import de.castmax1311.katzcraftmanagement.commands.MaintenanceCommand;
import de.castmax1311.katzcraftmanagement.commands.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private boolean maintenanceMode = false;
    private String originalMotd;
    public static Main instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("MaintenanceMode plugin has been enabled.");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        originalMotd = Bukkit.getServer().getMotd();
        saveDefaultConfig();
        reloadConfig();

        // Won't work before publishing
        new UpdateChecker(this, 111324).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("No updates available");
            } else {
                getLogger().info("New update available!");
            }
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("MaintenanceMode plugin has been disabled.");
    }

    public boolean isMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public void sendMessageToOperators(String message) {
        for (Player player : getServer().getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(ChatColor.RED + "[MaintenanceMode] " + ChatColor.RESET + message);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (maintenanceMode && !player.isOp()) {
            player.kickPlayer(ChatColor.RED + "This server is under maintenance");
        } else {
            player.setGameMode(GameMode.CREATIVE); // Ändern Sie dies nach Bedarf
        }
    }
}