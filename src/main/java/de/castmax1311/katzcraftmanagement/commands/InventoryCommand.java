package de.castmax1311.katzcraftmanagement.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.isOp()) {
                player.sendMessage("You don't have permission to execute this command!");
                return true;
            }

            if (args.length != 1) {
                player.sendMessage("Use: /inventory <player>");
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                player.sendMessage("Player not found or not online");
                return true;
            }

            player.openInventory(targetPlayer.getInventory());
        } else {
            sender.sendMessage("This command can only be executed by players!");
        }
        return true;
    }
}