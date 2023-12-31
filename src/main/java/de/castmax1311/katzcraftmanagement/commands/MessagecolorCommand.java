package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Listeners.MessagecolorListener;
import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessagecolorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Main.formatMessage("Use: /messagecolor <color>"));
            return true;
        }

        ChatColor color;
        try {
            color = ChatColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "Invalid color!"));
            return true;
        }

        Player player = (Player) sender;
        MessagecolorListener.setColor(player, color);
        player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Your message color has been updated!"));

        return true;
    }
}