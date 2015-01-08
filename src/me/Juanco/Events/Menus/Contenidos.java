package me.Juanco.Events.Menus;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Contenidos {

	private Contenidos() { }
	static Contenidos instance = new Contenidos();
	public static Contenidos getInstance() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void contenidos(Player p, FileConfiguration fc) {
		String name1 = "&a&oContenidos " + fc.getString("Name") + "§a§o:";
		name1 = name1.replaceAll("&", "§");
		Inventory inv = Bukkit.createInventory(null, 45, name1);
		try {
			Set<String>b = fc.getConfigurationSection("Inv.Items").getKeys(false);
			for (String s : b) {
				ItemStack i = fc.getItemStack("Inv.Items." + s);
				int slot = Integer.parseInt(s);
				inv.setItem(slot, i);
			}
		} catch(NullPointerException ee) { }
		ItemStack i1 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		ItemMeta im1 = i1.getItemMeta();
		im1.setDisplayName("§5§oVolver");
		i1.setItemMeta(im1);
		inv.setItem(36, i1);
		for (int n = 1; n < 5; n++) {
			ItemStack i = new ItemStack(Material.ENDER_PORTAL, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("§8");
			i.setItemMeta(im);
			int slot = n+36;
			inv.setItem(slot, i);
		}
		if (fc.getString("Inv.Helmet") != null) {
			ItemStack i = fc.getItemStack("Inv.Helmet");
			inv.setItem(41, i);
		}
		if (fc.getString("Inv.Chestplate") != null) {
			ItemStack i = fc.getItemStack("Inv.Chestplate");
			inv.setItem(42, i);
		}
		if (fc.getString("Inv.Leggings") != null) {
			ItemStack i = fc.getItemStack("Inv.Leggings");
			inv.setItem(43, i);
		}
		if (fc.getString("Inv.Boots") != null) {
			ItemStack i = fc.getItemStack("Inv.Boots");
			inv.setItem(44, i);
		}
		p.closeInventory();
		p.openInventory(inv);
	}
}
