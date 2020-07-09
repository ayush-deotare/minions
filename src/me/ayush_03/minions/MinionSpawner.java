package me.ayush_03.minions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.ayush_03.minions.types.BlockMinion;
import me.ayush_03.minions.types.CropMinion;
import me.ayush_03.minions.types.MobMinion;

public class MinionSpawner {

	int id;
	MinionType type;
	ItemStack item;

	public MinionSpawner(int id, MinionType type, ItemStack item) {
		this.id = id;
		this.type = type;
		this.item = item;
	}

	@SuppressWarnings("deprecation")
	public MinionSpawner(int id, MinionType type) {
		this.id = id;
		this.type = type;

		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();

		if (type == MinionType.MOBS) {
			sm.setOwner("Hunter");
		} else if (type == MinionType.BLOCKS) {
			sm.setOwner("Miner");
		} else {
			sm.setOwner("Farmer");
		}
		skull.setItemMeta(sm);

		this.item = skull;
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Minion Spawner" + HiddenStringUtils.encodeString("@!" + type.toString() + "_" + id));
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.YELLOW + "" + WordUtils.capitalize(type.toString().toLowerCase()) + " Minion");
		lore.add(ChatColor.RED + "Right click on the block to spawn the Minion!");
		
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public MinionSpawner(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			String displayName = item.getItemMeta().getDisplayName();
			if (HiddenStringUtils.hasHiddenString(displayName)) {
				String hidden = HiddenStringUtils.extractHiddenString(displayName);
				if (hidden.contains("@!")) {
					hidden = hidden.replace("@!", "");
					String[] split = hidden.split("_");
					this.type = MinionType.fromString(split[0]);
					this.id = Integer.parseInt(split[1]);
				}
			}
		}
	}
	
	public static boolean isSpawner(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			String displayName = item.getItemMeta().getDisplayName();
			if (HiddenStringUtils.hasHiddenString(displayName)) {
				String hidden = HiddenStringUtils.extractHiddenString(displayName);
				if (hidden.contains("@!")) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void spawn(Location loc, Player owner) {
		
		Minion m;
		
		if (type == MinionType.BLOCKS) {
			m = new BlockMinion(id, loc, owner.getUniqueId());

		} else if (type == MinionType.CROPS) {
			m = new CropMinion(id, loc, owner.getUniqueId());
		} else {
			m = new MobMinion(id, loc, owner.getUniqueId());
		}
		
		m.spawn();
		Main.MINIONS.add(m);
	}

	public int getID() {
		return id;
	}

	public MinionType getMinionType() {
		return type;
	}

	public ItemStack getItemStack() {
		return item;
	}
}