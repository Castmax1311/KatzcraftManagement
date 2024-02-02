package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("katzcraftmanagement.inventory")) {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
                return true;
            }

            if (args.length != 1) {
                player.sendMessage(Main.formatMessage("Use: /inventory <player>"));
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "Player not found or not online"));
                return true;
            }

            player.openInventory(targetPlayer.getInventory());
        } else {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
        }
        return true;
    }
}