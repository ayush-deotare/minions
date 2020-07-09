package me.ayush_03.minions.listeners;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ayush_03.minions.Main;
import me.ayush_03.minions.Minion;
import me.ayush_03.minions.MinionInventoryHolder;
import me.ayush_03.minions.UpgradeGUI;

public class MinionGUIListener implements Listener {
	
	Main plugin;
	public MinionGUIListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Inventory inv = e.getInventory();
		if (!(e.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) e.getWhoClicked();
		
		if (inv.getHolder() != null && inv.getHolder() instanceof MinionInventoryHolder) {
			MinionInventoryHolder holder = (MinionInventoryHolder) inv.getHolder();
			Minion m = holder.getMinion();
			
			if (m.getOwnerID().equals(p.getUniqueId())) {
				
				if (e.getRawSlot() < 54 && e.getRawSlot() != 11) {
					e.setCancelled(true);
					
					ItemStack current = e.getCurrentItem();
					
					if (current == null) return;
					
					if (current.getType() == Material.GOLD_BLOCK) {
						p.openInventory(UpgradeGUI.createUpgradeGUI(p, m));
					} else if (current.getType() == Material.ENDER_CHEST) {
						
						for (int i : m.getContentSlots()) {
							ItemStack item = inv.getItem(i);
							if (item != null && item.getType() != Material.AIR && item.getType() != Material.GRAY_STAINED_GLASS_PANE) {
								Map<Integer, ItemStack> map = p.getInventory().addItem(item);
								
								if (!map.isEmpty()) {
									p.getWorld().dropItem(m.getLocation(), map.get(0));
								}
								inv.setItem(i, new ItemStack(Material.AIR));
							}
						}
					} else if (current.getType() == Material.BEDROCK) {
						e.setCancelled(true);
						for (int i : m.getContentSlots()) {
							ItemStack item = inv.getItem(i);
							if (item != null && item.getType() != Material.AIR && item.getType() != Material.GRAY_STAINED_GLASS_PANE) {
								Map<Integer, ItemStack> map = p.getInventory().addItem(item);
								
								if (!map.isEmpty()) {
									p.getWorld().dropItem(m.getLocation(), map.get(0));
								}
								inv.setItem(i, new ItemStack(Material.AIR));
							}
						}
						p.closeInventory();
						m.destroy();
						
					}
					
				}
				
			}
		}
		
	}
	
}
