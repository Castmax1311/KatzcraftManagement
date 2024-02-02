package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class HeadCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("katzcraftmanagement.head")) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            return true;
        }

        if (args.length < 1 || args.length > 2) {
            player.sendMessage(Main.formatMessage("Use: /head <player> <amount>"));
            return true;
        }

        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Player not found or not online"));
            return true;
        }

        int amount = 1;

        if (args.length == 2) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "The specified quantity is invalid!"));
                return true;
            }
        }

        if (amount < 1 || amount > 64) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "The amount must be between 1 and 64!"));
            return true;
        }

        ItemStack head = new ItemStack(org.bukkit.Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(target);
        head.setItemMeta(meta);

        player.getInventory().addItem(head);
        player.sendMessage(Main.formatMessage(ChatColor.GREEN + "You received " + amount + " head/heads from " + target.getName()));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        } else if (args.length == 2 && sender instanceof Player && sender.isOp()) {
            List<String> quantities = new ArrayList<>();
            for (int i = 1; i <= 64; i++) {
                quantities.add(String.valueOf(i));
            }
            return quantities;
        }
        return null;
    }
}
