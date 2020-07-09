package me.ayush_03.minions;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MinionInventoryHolder implements InventoryHolder {
	
	OfflinePlayer p;
	Inventory inv;
	Minion m;
	
	public MinionInventoryHolder(OfflinePlayer p, Minion m) {
		this.p = p;
		this.m = m;
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return p;
	}
	
	public Minion getMinion() {
		return m;
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

}
