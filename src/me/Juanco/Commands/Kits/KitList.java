package me.Juanco.Commands.Kits;

import java.io.File;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitList {

	ConfigKits ck = ConfigKits.getInstance();
	private KitList() { }
	static KitList instance = new KitList();
	public static KitList getInstance() {
		return instance;
	}
	
	public void list(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.list")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		String l = "";
		try {

			for (File f : ck.folder().listFiles()) {
				l += f.getName().substring(0, f.getName().length()-4) + ", ";
			}	
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "No hay ningun kit!");
			return;
		}
		l = l.substring(0, l.length()-2);
		p.sendMessage(ChatColor.GREEN + "Kits: " + ChatColor.AQUA + l);
	}
}
