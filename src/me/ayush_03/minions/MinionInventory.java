package me.ayush_03.minions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MinionInventory {
	
	@SuppressWarnings("deprecation")
	public static Inventory createInventory(OfflinePlayer p, Minion m) {
		
		Inventory inv = Bukkit.createInventory(new MinionInventoryHolder(p, m), 54, "Minion");
		
		ItemStack comb = icon(Material.HONEYCOMB, ChatColor.BLACK + "");
		ItemStack pane = icon(Material.WHITE_STAINED_GLASS_PANE, ChatColor.BLACK + "");
		ItemStack gold = icon(Material.GOLD_BLOCK, ChatColor.YELLOW + "Upgrade Minion");
		ItemStack chest = icon(Material.ENDER_CHEST, ChatColor.GREEN + "Get Items");
		ItemStack bedrock = icon(Material.BEDROCK, ChatColor.RED + "Remove Minion");
		ItemStack black = icon(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RED + "Locked Slot");
		
		inv.setItem(1, pane);
		inv.setItem(2, pane);
		inv.setItem(3, pane);
		inv.setItem(4, pane);
		inv.setItem(5, pane);
		inv.setItem(6, pane);
		inv.setItem(7, pane);
		
		inv.setItem(46, pane);
		inv.setItem(47, pane);
		inv.setItem(48, pane);
		inv.setItem(49, pane);
		inv.setItem(50, pane);
		inv.setItem(51, pane);
		inv.setItem(52, pane);
		
		inv.setItem(10, pane);
		inv.setItem(12, pane);
		inv.setItem(14, pane);
		inv.setItem(16, pane);
		inv.setItem(19, pane);
		inv.setItem(25, pane);
		inv.setItem(28, pane);
		inv.setItem(34, pane);
		inv.setItem(37, pane);
		inv.setItem(38, pane);
		inv.setItem(40, pane);
		inv.setItem(42, pane);
		inv.setItem(43, pane);
		
		inv.setItem(39, chest);
		inv.setItem(41, bedrock);
		inv.setItem(21, black);
		inv.setItem(22, black);
		inv.setItem(23, black);
		inv.setItem(24, black);
		inv.setItem(29, black);
		inv.setItem(30, black);
		inv.setItem(31, black);
		inv.setItem(32, black);
		inv.setItem(33, black);
		inv.setItem(15, gold);
		
		
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner("Ayush_03");
		skull.setItemMeta(sm);
		
		inv.setItem(13, skull);
		
		
		for (int i = 9; i < 55; i+=9) {
			inv.setItem(i-1, comb);
		}
		
		for (int i = 1; i < 47; i+=9) {
			inv.setItem(i-1, comb);
		}
		
		return inv;
		
	}
	
	private static ItemStack icon(Material mat, String displayName) {
		
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		
		return item;
	}

}
