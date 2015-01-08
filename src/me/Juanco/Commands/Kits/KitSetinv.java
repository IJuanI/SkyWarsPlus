package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitSetinv {

	ConfigKits ck = ConfigKits.getInstance();
	private KitSetinv() { }
	static KitSetinv instance = new KitSetinv();
	public static KitSetinv getInstance() {
		return instance;
	}
	
	public void setinv(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.setinv")) {
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
		if (args.length > 3) {
			if (args[3].equalsIgnoreCase("null")) {
				ck.getF(args[2]).set("Inv", null);
				ck.save();
				p.sendMessage(ChatColor.GREEN + "Borrado exitosamente el inventario del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
				return;
			}
		}
		ck.load(args[2]);
		for (int n = 0; n < p.getInventory().getContents().length; n++) {
			ItemStack i = p.getInventory().getItem(n);
			if (i != null) {
				ck.get().set("Inv.Items." + n, i);
			} else {
				ck.get().set("Inv.Items." + n, null);
			}
		}
		ItemStack h = p.getInventory().getHelmet();
		if (h != null) {
			ck.get().set("Inv.Helmet", h);
		} else {
			ck.get().set("Inv.Helmet", null);
		}
		ItemStack c = p.getInventory().getChestplate();
		if (h != null) {
			ck.get().set("Inv.Chestplate", c);
		} else {
			ck.get().set("Inv.Chestplate", null);
		}
		ItemStack l = p.getInventory().getLeggings();
		if (h != null) {
			ck.get().set("Inv.Leggings", l);
		} else {
			ck.get().set("Inv.Leggings", null);
		}
		ItemStack b = p.getInventory().getBoots();
		if (h != null) {
			ck.get().set("Inv.Boots", b);
		} else {
			ck.get().set("Inv.Boots", null);
		}
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el inv del kit " + ChatColor.AQUA + args[2]);
	}
}
