package de.castmax1311.katzcraftmanagement.TabCompleter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MessagecolorTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> colorList = new ArrayList<>();
            for (ChatColor color : ChatColor.values()) {
                colorList.add(color.name().toLowerCase());
            }
            return colorList;
        }
        return null;
    }
}