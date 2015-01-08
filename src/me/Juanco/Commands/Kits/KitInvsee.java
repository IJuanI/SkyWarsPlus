package me.Juanco.Commands.Kits;

import java.util.Set;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitInvsee {

	public Inventory inv;
	public ItemStack a;
	
	ConfigKits ck = ConfigKits.getInstance();
	private KitInvsee() { }
	static KitInvsee instance = new KitInvsee();
	public static KitInvsee getInstance() {
		return instance;
	}
	
	public void invsee(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.invsee")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica un id!");
			return;
		}
		if (!ck.getfile(args[2]).exists()) {
			p.sendMessage(ChatColor.RED + "Ese kit no existe!");
			return;
		}
		try {
			ck.get().getConfigurationSection("Inv").getKeys(false);
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "Este kit no tiene inv!");
			return;
		}
		String name = "&a&oKit: " + ck.get().getString("Name");
		name = name.replaceAll("&", "§");
		inv = Bukkit.getServer().createInventory(null, 45, name);
		Set<String> b = ck.get().getConfigurationSection("Inv.Items").getKeys(false);
		for (String s : b) {
			ItemStack i = ck.get().getItemStack("Inv.Items." + s);
			int slot = Integer.parseInt(s);
			inv.setItem(slot, i);
		}
		for (int n = 0; n < 5; n++) {
			ItemStack i = new ItemStack(Material.ENDER_PORTAL, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("§8");
			i.setItemMeta(im);
			int slot = n+36;
			inv.setItem(slot, i);
		}
		if (ck.get().getString("Inv.Helmet") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Helmet");
			inv.setItem(41, i);
		}
		if (ck.get().getString("Inv.Chestplate") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Chestplate");
			inv.setItem(42, i);
		}
		if (ck.get().getString("Inv.Leggings") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Leggings");
			inv.setItem(43, i);
		}
		if (ck.get().getString("Inv.Boots") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Boots");
			inv.setItem(44, i);
		}
		p.openInventory(inv);
	}
}
