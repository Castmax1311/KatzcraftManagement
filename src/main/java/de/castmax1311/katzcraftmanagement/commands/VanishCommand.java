package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Listeners.VanishListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage("Du musst ein Operator sein, um diesen Befehl zu verwenden!");
            return true;
        }

        if (player.hasPermission("vanish.use")) {
            VanishListener.setVanished(player, !VanishListener.isVanished(player));
        } else {
            player.sendMessage("Du hast keine Berechtigung, den Vanish-Befehl zu verwenden!");
        }

        return true;
    }
}