package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("katzcraftmanagement.maintenance")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            return true;
        }
        if (Main.instance.isMaintenanceMode()) {
            Main.instance.setMaintenanceMode(false);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "The server is no longer under maintenance"));
        } else {
            Main.instance.setMaintenanceMode(true);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "The server is now under maintenance"));
        }

        return true;
    }
}
