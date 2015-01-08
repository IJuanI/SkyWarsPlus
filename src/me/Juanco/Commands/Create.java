package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Create {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Create() { }
	static Create instance = new Create();
	public static Create getInstance() {
		return instance;
	}
	
	public void create(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.create")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 1) {
			p.sendMessage(ChatColor.RED + "Elije un nombre para tu arena!");
			return;
		}
		try {
			for (File f : settings.getfolder().listFiles()) {
				if (f.getName().substring(0, f.getName().length()-4).equalsIgnoreCase(args[1])) {
					p.sendMessage(ChatColor.RED + "Esa arena ya existe!");
					return;
				}
			}	
		} catch(NullPointerException e) {
			
		}
		settings.CreateA(args[1], p);
	}
}
