package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().isAir()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't hold an item in your hand"));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "The item cannot be renamed"));
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(Main.formatMessage("Use: /rename <name>"));
            return true;
        }

        String newName = String.join(" ", args);
        meta.setDisplayName(newName);
        item.setItemMeta(meta);
        player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Item successfully renamed"));

        return true;
    }
}
