package de.castmax1311.katzcraftmanagement.Listeners;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void OnServerListPing(ServerListPingEvent event){
        if(Main.instance.isMaintenanceMode()){
            event.setMotd(ChatColor.RED + "This server is under maintenance");
        }
    }
}
