package me.Juanco.others;

import java.util.Set;

import me.Juanco.skywars.ConfigKits;
import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveKit {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	ConfigKits ck = ConfigKits.getInstance();
	private GiveKit() { }
	static GiveKit instance = new GiveKit();
	public static GiveKit getInstance() {
		return instance;
	}
	
	public void gk(Player p, String kit) {
		ck.load(kit);
		p.getInventory().clear();
		try {
			Set<String> b = ck.get().getConfigurationSection("Inv.Items").getKeys(false);
			for (String s : b) {
				ItemStack i = ck.get().getItemStack("Inv.Items." + s);
				int slot = Integer.parseInt(s);
				p.getInventory().setItem(slot, i);
			}
		} catch(NullPointerException ee) { }
		if (ck.get().getString("Inv.Helmet") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Helmet");
			p.getEquipment().setHelmet(i);
		}
		if (ck.get().getString("Inv.Chestplate") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Chestplate");
			p.getEquipment().setChestplate(i);
		}
		if (ck.get().getString("Inv.Leggings") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Leggings");
			p.getEquipment().setLeggings(i);
		}
		if (ck.get().getString("Inv.Boots") != null) {
			ItemStack i = ck.get().getItemStack("Inv.Boots");
			p.getEquipment().setBoots(i);
		}
		if (ck.get().getString("Options.extra-hearts") != null) {
			int extra = Integer.parseInt(ck.get().getString("Options.extra-hearts"))*2;
			Double health = 20.0 + extra;
			p.setMaxHealth(health);
			p.setHealth(health);
		} else {
			p.setHealth(20.0);	
		}
		int money;
		try {
			money = Integer.parseInt(cp.getP(p.getName()).getString("Money"));	
		} catch(NullPointerException e) { money = 0; }
		int cost = ck.get().getInt("Cost");
		money -= cost;
		cp.get().set("Money", money);
		cp.save();
	}
}
