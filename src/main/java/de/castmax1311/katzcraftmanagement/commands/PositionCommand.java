package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;

public class PositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Main instance = Main.instance;

            String positionSound = instance.getConfig().getString("positionSound");

            if (positionSound == null) {
                sender.sendMessage(Main.formatMessage(ChatColor.RED + "The position sound wasn't configured correctly..."));
                return true;
            }

            player.getWorld().playSound(player.getLocation(), Sound.valueOf(positionSound), SoundCategory.MASTER, 1.0f, 1.0f);
            Main.instance.getServer().broadcastMessage(Main.formatMessage(ChatColor.GREEN + player.getName() + ChatColor.AQUA + " has sent his position: " + ChatColor.RESET +  "X:" + player.getLocation().getBlockX() + " / Y:" + player.getLocation().getBlockY() + " / Z:" + player.getLocation().getBlockZ()));

        } else {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
        }
        return true;
    }
}