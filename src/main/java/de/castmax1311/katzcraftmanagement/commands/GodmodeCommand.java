package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GodmodeCommand implements CommandExecutor {

    private static final Map<Player, Boolean> godmodePlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            return true;
        }

        boolean godmodeEnabled = godmodePlayers.getOrDefault(player, false);

        if (godmodeEnabled) {
            godmodePlayers.put(player, false);
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Godmode disabled!"));
        } else {
            godmodePlayers.put(player, true);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Godmode enabled!"));
        }

        return true;
    }

    public static boolean isGodmodeEnabled(Player player) {
        return godmodePlayers.getOrDefault(player, false);
    }
}