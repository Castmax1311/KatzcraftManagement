package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class NamecolorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Main.formatMessage("Use: /namecolor <color>"));
            return true;
        }

        ChatColor color;
        try {
            color = ChatColor.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "Invalid color!"));
            return true;
        }

        Player player = (Player) sender;
        Main.instance.getNicknameManager().setNameColor(player, color);

        // Update the player's name in the tab list and above the player's head
        updatePlayerName(player);

        // Save the color to the configuration file
        saveColorToConfig(player, color);

        player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Your name color has been updated!"));
        return true;
    }

    // Method to update the player's name in the tab list and above the player's head
    private void updatePlayerName(Player player) {
        String nickname = Main.instance.getNicknameManager().getNickname(player);
        if (nickname == null) {
            nickname = player.getName(); // Use the player's original name if no nickname is set
        }

        ChatColor color = Main.instance.getNicknameManager().getNameColor(player);
        if (color == null) {
            color = ChatColor.WHITE; // Default color, white
        }

        player.setDisplayName(color + nickname + ChatColor.RESET);
        player.setPlayerListName(color + nickname + ChatColor.RESET);
    }

    // Method to save the player's color to the configuration file
    private void saveColorToConfig(Player player, ChatColor color) {
        File configFile = new File(Main.instance.getDataFolder(), "namecolors.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        // Save the color as a string representation (e.g., "AQUA")
        config.set(player.getUniqueId().toString(), color.name());

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
