package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import net.kyori.adventure.platform.facet.Facet;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("discord")) {
                String discordLink = Main.instance.getConfig().getString("discordLink");
                if (discordLink != null && !discordLink.isEmpty()) {
                    player.sendMessage(Main.formatMessage("Visit our " + ChatColor.DARK_BLUE + "Discord: " + ChatColor.BLUE + discordLink));
                } else {
                    player.sendMessage(Main.formatMessage(ChatColor.RED + "No Discord link configured"));
                }
            } else if (command.getName().equalsIgnoreCase("youtube")) {
                String youtubeLink = Main.instance.getConfig().getString("youtubeLink");
                if (youtubeLink != null && !youtubeLink.isEmpty()) {
                    player.sendMessage(Main.formatMessage("Visit our " + ChatColor.RED + "YouTube Channel: " + ChatColor.BLUE + youtubeLink));
                } else {
                    player.sendMessage(Main.formatMessage(ChatColor.RED + "No YouTube link configured"));
                }
            } else if (command.getName().equalsIgnoreCase("instagram")) {
                String instagramLink = Main.instance.getConfig().getString("instagramLink");
                if (instagramLink != null && !instagramLink.isEmpty()) {
                    player.sendMessage(Main.formatMessage("Visit our " + ChatColor.DARK_PURPLE + "Instagram page: " + ChatColor.BLUE +  instagramLink));
                } else {
                    player.sendMessage(Main.formatMessage(ChatColor.RED + "No Instagram link configured"));
                }
            }
        } else {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
        }
        return true;
    }
}