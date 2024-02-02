package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players."));
            return true;
        }

        Player player = (Player) sender;

        // Check if the player is an operator (OP)
        if (!player.hasPermission("katzcraftmanagement.fly")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to use this command."));
            return true;
        }

        // Toggle the flying mode for the player
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false); // Disable flying mode
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Flying disabled"));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Flying enabled"));
        }

        return true;
    }
}