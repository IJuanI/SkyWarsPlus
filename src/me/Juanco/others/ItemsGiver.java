package me.Juanco.others;

import me.Juanco.skywars.Config;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsGiver {

	Config c = Config.getInstance();
	private ItemsGiver() { }
	static ItemsGiver instance = new ItemsGiver();
	public static ItemsGiver getInstance() {
		return instance;
	}
	
	public void selector(Player p) {
		if (c.getConfig().getString("Selector.Slot") == null) {
			c.getConfig().set("Selector.Slot", 9);
		}
		if (c.getConfig().getString("Selector.ID") == null) {
			c.getConfig().set("Selector.ID", "FIREWORK");
		}
		if (c.getConfig().getString("Selector.World") == null) {
			c.getConfig().set("Selector.World", Bukkit.getServer().getWorlds().get(0).getName());
		}
		c.saveConfig();
		ItemStack i = new ItemStack(Material.getMaterial(c.getConfig().getString("Selector.ID")), 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("§b§l§oKits");
		i.setItemMeta(im);
		p.getInventory().setItem(c.getConfig().getInt("Selector.Slot")-1, i);
		p.updateInventory();
	}
}
