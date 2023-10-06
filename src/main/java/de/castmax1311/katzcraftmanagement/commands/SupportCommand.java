package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SupportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("support")) {
            if (args.length >= 1) {
                StringBuilder reason = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    reason.append(args[i]).append(" ");
                }
                String message = (Main.formatMessage(ChatColor.RED + "Support request from " + ChatColor.GREEN + sender.getName() + ": " + ChatColor.RESET + reason.toString().trim()));

                for (String operator : Main.instance.getServer().getOperators().stream().map(op -> op.getName()).toArray(String[]::new)) {
                    Main.instance.getServer().getPlayer(operator).sendMessage(message);

                    String soundName = Main.instance.getConfig().getString("supportSound");
                    float volume = 1.0f;
                    float pitch = 1.0f;
                    Sound sound = Sound.valueOf(soundName);
                    Main.instance.getServer().getPlayer(operator).playSound(Main.instance.getServer().getPlayer(operator).getLocation(), sound, volume, pitch);
                }
                return true;
            } else {
                sender.sendMessage(Main.formatMessage("Use: /support <reason>"));
                return true;
            }
        }
        return false;
    }
}