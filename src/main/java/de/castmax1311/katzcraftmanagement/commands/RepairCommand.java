package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to use this command"));
            return true;
        }

        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null || itemInHand.getType().isAir()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED +"You need to hold an item in your hand to use this command!"));
            return true;
        }

        itemInHand.setDurability((short) 0);
        player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Item repaired!"));

        return true;
    }
}
