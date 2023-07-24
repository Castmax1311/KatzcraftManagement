package de.castmax1311.katzcraftmanagement.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdListener implements Listener {

    private String motd; // Variable zum Speichern der Serverbeschreibung (MOTD)

    public MotdListener(String initialMotd) {
        this.motd = initialMotd;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd(motd); // Setze die gespeicherte MOTD als Serverbeschreibung
    }

    // Methode zum Aktualisieren der MOTD
    public void setMotd(String motd) {
        this.motd = motd;
    }

    // Methode zum Abrufen der aktuellen MOTD
    public String getMotd() {
        return motd;
    }
}
