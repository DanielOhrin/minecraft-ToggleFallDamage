package com.daniel.togglefalldamage.commands;

import com.daniel.togglefalldamage.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleFallDamageCommand implements CommandExecutor {
    final private Main MAIN;
    public ToggleFallDamageCommand(Main main) {
        MAIN = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check for permission
        if ((sender instanceof Player)) {
            if (!sender.hasPermission("falldamage.toggle")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return false;
            }
        } else {
            System.out.println("Only a player can use this command.");
            return false;
        }

        // Check format of command
        if (args.length != 1 || (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))) {
            sender.sendMessage(ChatColor.RED + "/togglefalldamage <on/off>");
            return false;
        }

        boolean newValue = args[0].equalsIgnoreCase("on");
        UUID playerUUID = ((Player) sender).getUniqueId();

        if (!MAIN.getConfig().isSet("players." + playerUUID + ".falldamage")) {
            MAIN.getConfig().set("players." + playerUUID + ".falldamage", newValue);

            if (newValue) {
                sender.sendMessage(ChatColor.RED + "Fall damage is already enabled");
                return true;
            }

            sender.sendMessage(ChatColor.GREEN + "Fall damage is now disabled");
        } else {
            boolean currentValue = MAIN.getConfig().getBoolean("players." + playerUUID + ".falldamage");
            if (currentValue == newValue) {
                sender.sendMessage(ChatColor.RED + "Fall damage is already " + (currentValue ? "enabled" : "disabled"));
                return false;
            }

            MAIN.getConfig().set("players." + playerUUID + ".falldamage", newValue);
            sender.sendMessage(ChatColor.GREEN + "Fall damage is now " + (newValue ? "enabled" : "disabled"));
        }
        return true;
    }
}
