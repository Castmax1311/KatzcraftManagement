package de.castmax1311.katzcraftmanagement.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdListener implements Listener {

    private String motd;

    public MotdListener(String initialMotd) {
        this.motd = initialMotd;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd(motd);
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getMotd() {
        return motd;
    }
}
