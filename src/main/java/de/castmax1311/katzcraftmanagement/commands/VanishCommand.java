package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Listeners.VanishListener;
import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.command.Command;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players."));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("katzcraftmanagement.vanish")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to use this command."));
            return true;
        }

        if (player.hasPermission("katzcraftmanagement.vanish")) {
            VanishListener.setVanished(player, !VanishListener.isVanished(player));
        } else {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to use this command."));
        }

        return true;
    }
}