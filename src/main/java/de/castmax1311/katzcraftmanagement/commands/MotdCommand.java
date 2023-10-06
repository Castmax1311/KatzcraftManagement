package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MotdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    sender.sendMessage(Main.formatMessage(ChatColor.GREEN + "Current server description: " + ChatColor.WHITE + Main.instance.getMotdListener().getMotd()));
                } else {
                    sender.sendMessage(Main.formatMessage(ChatColor.GREEN + "Current server description: " + ChatColor.WHITE + Main.instance.getMotdListener().getMotd()));
                }
            } else {
                sender.sendMessage(Main.formatMessage("Use: /motd"));
            }
            return true;
        }
        return false;
    }
}