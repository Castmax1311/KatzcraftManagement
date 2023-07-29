package de.castmax1311.katzcraftmanagement;

import de.castmax1311.katzcraftmanagement.Listeners.*;
import de.castmax1311.katzcraftmanagement.Manager.NicknameManager;
import de.castmax1311.katzcraftmanagement.TabCompleter.MessagecolorTabCompleter;
import de.castmax1311.katzcraftmanagement.TabCompleter.NamecolorTabCompleter;
import de.castmax1311.katzcraftmanagement.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private boolean maintenanceMode = false;
    private String originalMotd;

    public static Main instance;
    private MotdListener motdListener;
    private NicknameManager nicknameManager;

    @Override
    public void onLoad() {
        instance = this;
        this.nicknameManager = new NicknameManager(this);
    }

    @Override
    public void onEnable() {
        loadNicknames();
        getCommand("nickname").setExecutor(new NicknameCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getServer().getPluginManager().registerEvents(new MuteListener(), this);
        getCommand("vanish").setExecutor(new VanishCommand());
        getServer().getPluginManager().registerEvents(new VanishListener(), this);
        getCommand("namecolor").setExecutor(new NamecolorCommand());
        getCommand("namecolor").setTabCompleter(new NamecolorTabCompleter());
        getServer().getPluginManager().registerEvents(new NamecolorListener(), this);
        getCommand("messagecolor").setExecutor(new MessagecolorCommand());
        getCommand("messagecolor").setTabCompleter(new MessagecolorTabCompleter());
        getServer().getPluginManager().registerEvents(new MessagecolorListener(), this);
        getCommand("speed").setExecutor(new SpeedCommand());
        getServer().getPluginManager().registerEvents(new SpeedListener(), this);
        motdListener = new MotdListener(getServer().getMotd());
        getServer().getPluginManager().registerEvents(motdListener, this);
        getCommand("motd").setExecutor(new MotdCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("repair").setExecutor(new RepairCommand());
        getCommand("head").setExecutor(new HeadCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("godmode").setExecutor(new GodmodeCommand());
        getServer().getPluginManager().registerEvents(new GodmodeListener(), this);
        getCommand("freeze").setExecutor(new FreezeCommand());
        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getCommand("inventory").setExecutor(new InventoryCommand());
        getLogger().info("KatzcraftManagement plugin has been enabled.");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        originalMotd = Bukkit.getServer().getMotd();
        saveDefaultConfig();
        reloadConfig();
        nicknameManager.loadNicknames();

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
        saveNicknames();
        getLogger().info("KatzcraftManagement plugin has been disabled.");
    }
    public MotdListener getMotdListener() {
        return motdListener;
    }
    public NicknameManager getNicknameManager() {
        return nicknameManager;
    }

    private void loadNicknames() {
        // Implement code to load saved nicknames from file or database (if you want to persist data across server restarts).
        // For simplicity, we'll just use an empty method for now.
    }

    private void saveNicknames() {
        // Implement code to save nicknames to file or database (if you want to persist data across server restarts).
        // For simplicity, we'll just use an empty method for now.
    }

    public static String formatMessage(String message) {
        return "[" + ChatColor.BLUE + "KatzcraftManagement" + ChatColor.RESET + "] " + message;
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
    }
}