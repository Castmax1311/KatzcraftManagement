package de.castmax1311.katzcraftmanagement.commands;
import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearchatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("katzcraftmanagement.clearchat")) {
                // Clear chat for all players
                for (int i = 0; i < 100; i++) {
                    player.getServer().broadcastMessage("");
                }
                player.getServer().broadcastMessage(Main.formatMessage(ChatColor.GREEN + "Chat has been cleared by " + player.getName()));
            } else {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to execute this command!"));
            }
        }
        return true;
    }
}

