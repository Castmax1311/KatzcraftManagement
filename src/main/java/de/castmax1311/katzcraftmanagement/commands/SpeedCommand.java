package de.castmax1311.katzcraftmanagement.commands;

import de.castmax1311.katzcraftmanagement.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpeedCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.formatMessage(ChatColor.RED + "This command can only be executed by players."));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "You don't have permission to use this command."));
            return true;
        }

        if (args.length < 2) {
            player.sendMessage(Main.formatMessage("Use: /speed <fly / walk> <speed>"));
            return true;
        }


        String type = args[0].toLowerCase();
        float speed;

        try {
            speed = Float.parseFloat(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Invalid speed indication!"));
            return true;
        }

        speed = Math.min(10f, Math.max(0f, speed));

        if (type.equals("fly")) {
            player.setFlySpeed(speed / 10f);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Flying speed set to " + speed));
        } else if (type.equals("walk")) {
            player.setWalkSpeed(speed / 10f);
            player.sendMessage(Main.formatMessage(ChatColor.GREEN + "Walking speed set to " + speed));
        } else {
            player.sendMessage(Main.formatMessage(ChatColor.RED + "Invalid type! Use: /speed <fly / walk> <speed>"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("fly");
            completions.add("walk");
        } else if (args.length == 2) {
            for (int i = 1; i <= 10; i++) {
                completions.add(String.valueOf(i));
            }
        }

        return completions;
    }
}

