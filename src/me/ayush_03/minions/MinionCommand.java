package me.ayush_03.minions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinionCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("minion")) {
			if (args.length != 2) {
				sender.sendMessage(ChatColor.RED + "Usage: /minion <player> <type>");
				return true;
			}
			
			if (sender.hasPermission("minions.cmd")) {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Specified player not found!");
					return true;
				}
				
				MinionType type = MinionType.fromString(args[1].toUpperCase());
				
				if (type == null) {
					sender.sendMessage(ChatColor.RED + "Invalid Minion Type!");
					sender.sendMessage(ChatColor.YELLOW + "Available types: " + ChatColor.AQUA + "blocks, crops and mobs");
					return true;
				}
				
				MinionSpawner spawner = new MinionSpawner(++Main.TOTAL, type);
				
				sender.sendMessage(ChatColor.GREEN + "Minion spawner given!");
				p.sendMessage(ChatColor.GREEN + "You have received a Minion Spawner of type " + type.toString() + ".");
				
				if (p.getInventory().firstEmpty() == -1) {
					p.getWorld().dropItem(p.getLocation(), spawner.getItemStack());
					p.sendMessage(ChatColor.RED + "Since your inventory is full, the spawner has been dropped on the ground near you.");
				} else {
					p.getInventory().addItem(spawner.getItemStack());
					p.updateInventory();
				}
				
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				return true;
			}
		}
		return true;
		
	}
}
