package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FreezeCommand implements CommandExecutor {
    private static final HashMap<UUID, Boolean> frozenPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("katzcraftmanagement.freeze")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            return true;
        }

        if (args.length == 0) {
            toggleFreeze(player);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Player not found or not online"));
            return true;
        }

        toggleFreeze(target, player);

        return true;
    }

    private void toggleFreeze(Player player) {
        boolean isFrozen = frozenPlayers.getOrDefault(player.getUniqueId(), false);
        frozenPlayers.put(player.getUniqueId(), !isFrozen);

        if (!isFrozen) {
            player.sendMessage(Main.formatMessage(ChatColor.AQUA + "You have been frozen"));
        } else {
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "You have been unfrozen"));
        }
    }

    private void toggleFreeze(Player target, Player executor) {
        boolean isFrozen = frozenPlayers.getOrDefault(target.getUniqueId(), false);
        frozenPlayers.put(target.getUniqueId(), !isFrozen);

        if (!isFrozen) {
            executor.sendMessage(Main.formatMessage(ChatColor.AQUA + target.getName() + " has been frozen"));
            target.sendMessage(Main.formatMessage(ChatColor.AQUA + "You have been frozen"));
        } else {
            executor.sendMessage(Main.formatMessage(ChatColor.GREEN + target.getName() + " has been unfrozen"));
            target.sendMessage(Main.formatMessage(ChatColor.GREEN + "You have been unfrozen"));
        }
    }

    public static boolean isFrozen(UUID playerUUID) {
        return frozenPlayers.getOrDefault(playerUUID, false);
    }
}







