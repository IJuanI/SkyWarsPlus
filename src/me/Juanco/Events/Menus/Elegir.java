package me.Juanco.Events.Menus;

import me.Juanco.skywars.ConfigPlayer;
import me.Juanco.skywars.SkyWars;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Elegir {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private Elegir() { }
	static Elegir instance = new Elegir();
	public static Elegir getInstance() {
		return instance;
	}
	
	public void elegir(Player p, String name, FileConfiguration fc) {
		try {
			 if (!cp.getP(p.getName()).getConfigurationSection("Kits").getKeys(false).contains(name)) {
				 p.sendMessage(ChatColor.RED + "No tienes ese kit!");
			 } else {
				 Boolean b = true;
				 try {
					 for (String s : fc.getConfigurationSection("Pemissions").getKeys(false)) {
						 s = s.replace(",", ".");
						 if (!p.hasPermission(s)) b = false;
					 }
				 } catch(NullPointerException e) { }
				 if (b == true) {
					 if (fc.getString("Cost-Per-Use") != null && fc.getInt("Cost-Per-Use") > Integer.parseInt(cp.getP(p.getName()).getString("Money"))) {
						 p.sendMessage(ChatColor.RED + "Te falta dinero!");
						 return;
					 }
					 SkyWars.plugin.usedkit.put(p, name);
					 p.closeInventory();
					 p.sendMessage(ChatColor.GREEN + "Kit elegido exisotamente!");
				 } else {
					 p.sendMessage(ChatColor.RED + "No tienes el kit desbloqueado!");
				 }
			 }
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "No tienes ese kit!");
		}
	}
}
