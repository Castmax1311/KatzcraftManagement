package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Listeners.MuteListener;
import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("katzcraftmanagement.mute")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(Main.formatMessage("Use: /mute <player> <time in seconds>"));
            return true;
        }

        Player target = Main.instance.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Player not found or not online"));
            return true;
        }

        long muteTime;
        try {
            muteTime = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Invalid time"));
            return true;
        }

        MuteListener.mutePlayer(target, muteTime);
        player.sendMessage(Main.formatMessage(ChatColor.GREEN + target.getName() + " was muted for " + muteTime + " seconds"));
        return true;
    }
}
