package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class OpswordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("katzcraftmanagement.opsword")) {
                ItemStack opsword = new ItemStack(Material.NETHERITE_SWORD);
                ItemMeta meta = opsword.getItemMeta();

                meta.setDisplayName("Operator Sword");
                meta.addEnchant(Enchantment.DAMAGE_ALL, 255, true);
                meta.addEnchant(Enchantment.FIRE_ASPECT, 255, true);
                meta.setUnbreakable(true);
                meta.addEnchant(Enchantment.MENDING, 1, true);
                meta.setLore(Collections.singletonList("By KatzcraftManagement"));

                opsword.setItemMeta(meta);

                player.getInventory().addItem(opsword);
                player.sendMessage(Main.formatMessage(ChatColor.GREEN + "You received the Operator Sword!"));
            } else {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            }
        }
        return true;
    }
}
