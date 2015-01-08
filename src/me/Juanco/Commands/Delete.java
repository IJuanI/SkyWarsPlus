package me.Juanco.Commands;


import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Delete {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Delete() { }
	static Delete instance = new Delete();
	public static Delete getInstance() {
		return instance;
	}
	
	public void delete(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.delete")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 1) {
			p.sendMessage(ChatColor.RED + "Especifica una arena!");
			return;
		}
		try {
			boolean b = true;
			for (File f : settings.getfolder().listFiles()) {
				if (f.getName().substring(0, f.getName().length()-4).equalsIgnoreCase(args[1])) {
					b = false;
				}
			}
			if (b == true) {
				p.sendMessage(ChatColor.RED + "Esa arena no existe!");
				return;
			}
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "No hay arenas!");
			return;
		}
		settings.DeleteA(args[1]);
		p.sendMessage(ChatColor.GREEN + "Eliminada exitosamente la arena " + args[1] + "!");
	}
}
