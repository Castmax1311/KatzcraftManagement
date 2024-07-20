package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OparmorCommand implements CommandExecutor {

    private Map<Player, ItemStack[]> savedArmor = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("katzcraftmanagement.oparmor")) {
                if (isWearingOpArmor(player)) {
                    removeOpArmor(player);
                    player.sendMessage(Main.formatMessage(ChatColor.RED + "OP armor removed!"));
                } else {
                    saveAndGiveOpArmor(player);
                    player.sendMessage(Main.formatMessage(ChatColor.GREEN + "You received the OP armor!"));
                }
            } else {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            }
        }
        return true;
    }

    private boolean isWearingOpArmor(Player player) {
        ItemStack[] armor = player.getInventory().getArmorContents();
        for (ItemStack piece : armor) {
            if (piece == null || !piece.hasItemMeta()) {
                return false;
            }
            ItemMeta meta = piece.getItemMeta();
            if (!meta.hasLore() || !meta.getLore().contains("By KatzcraftManagement")) {
                return false;
            }
        }
        return true;
    }

    private void removeOpArmor(Player player) {
        if (savedArmor.containsKey(player)) {
            player.getInventory().setArmorContents(savedArmor.get(player));
            savedArmor.remove(player);
        } else {
            player.getInventory().setArmorContents(new ItemStack[4]);
        }
    }

    private void saveAndGiveOpArmor(Player player) {
        ItemStack[] currentArmor = player.getInventory().getArmorContents();
        savedArmor.put(player, currentArmor);

        ItemStack[] opArmor = new ItemStack[4];
        opArmor[0] = createOpArmorPiece(Material.NETHERITE_BOOTS, "Operator Boots");
        opArmor[1] = createOpArmorPiece(Material.NETHERITE_LEGGINGS, "Operator Leggings");
        opArmor[2] = createOpArmorPiece(Material.NETHERITE_CHESTPLATE, "Operator Chestplate");
        opArmor[3] = createOpArmorPiece(Material.NETHERITE_HELMET, "Operator Helmet");

        player.getInventory().setArmorContents(opArmor);
    }

    private ItemStack createOpArmorPiece(Material material, String name) {
        ItemStack armorPiece = new ItemStack(material);
        ItemMeta meta = armorPiece.getItemMeta();

        meta.setDisplayName(name);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 255, true);
        meta.addEnchant(Enchantment.THORNS, 255, true);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 255, true);
        meta.addEnchant(Enchantment.DURABILITY, 255, true);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.setLore(Collections.singletonList("By KatzcraftManagement"));

        armorPiece.setItemMeta(meta);

        return armorPiece;
    }
}
