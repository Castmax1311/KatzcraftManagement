package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage("You don't have permission to execute this command!");
            return true;
        }
        //kommentar
        if (Main.instance.isMaintenanceMode()) {
            // Wartungsmodus deaktivieren
            Main.instance.setMaintenanceMode(false);
            player.sendMessage("The server is no longer under maintenance");
        } else {
            // Wartungsmodus aktivieren
            Main.instance.setMaintenanceMode(true);
            player.sendMessage("The server is now under maintenance");
        }

        return true;
    }
}
