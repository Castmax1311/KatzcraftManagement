package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players!"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            // Player is changing their own nickname
            String nickname = args[0];
            Main.instance.getNicknameManager().setNickname(player, nickname);
            updatePlayerName(player, nickname);

            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Your nickname has been updated!"));
        } else if (args.length == 2) {
            // Operator is changing another player's nickname
            String targetPlayerName = args[0];
            Player targetPlayer = Main.instance.getServer().getPlayer(targetPlayerName);

            if (targetPlayer == null) {
                player.sendMessage(Main.formatMessage(ChatColor.RED + "Player not found!"));
                return true;
            }

            String nickname = args[1];
            Main.instance.getNicknameManager().setNickname(targetPlayer, nickname);
            updatePlayerName(targetPlayer, nickname);

            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Nickname of " + targetPlayer.getName() + " has been updated!"));
        } else {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Use: /nickname <name> (to change your own nickname)"));
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Use: /nickname <player> <name> (to change other player's nickname - Operator only)"));
        }

        return true;
    }

    // Method to update the player's name in the tab list and above the player's head
    private void updatePlayerName(Player player, String nickname) {
        ChatColor color = Main.instance.getNicknameManager().getNameColor(player);
        if (color == null) {
            color = ChatColor.WHITE; // Default color, white
        }

        player.setDisplayName(color + nickname + ChatColor.RESET);
        player.setPlayerListName(color + nickname + ChatColor.RESET);
    }
}
