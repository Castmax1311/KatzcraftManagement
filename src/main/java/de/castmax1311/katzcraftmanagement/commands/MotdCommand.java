package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MotdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (args.length > 0) {
                String newMotd = String.join(" ", args);
                Main.instance.getMotdListener().setMotd(newMotd);
                sender.sendMessage(Main.formatMessage(ChatColor.GREEN + "The server description has been updated!"));
            } else {
                sender.sendMessage(Main.formatMessage("Current server description: " + Main.instance.getMotdListener().getMotd()));
            }
            return true;
        }
        return false;
    }
}