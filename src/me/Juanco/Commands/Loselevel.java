package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Loselevel {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Loselevel() { }
	static Loselevel instance = new Loselevel();
	public static Loselevel getInstance() {
		return instance;
	}
	
	public void loselevel(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.loselevel")) {
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
		if (args.length == 2) {
			settings.getA(args[1]).set("loselevel", p.getLocation().getBlockY());
			settings.saveA();
			p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el loselevel de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
			return;
		}
		try {
			Integer.parseInt(args[2]);
		} catch(IllegalArgumentException e) {
			p.sendMessage(ChatColor.RED + "El valor debe ser un numero!");
			return;
		}
		if (args[2].startsWith("-")) {
			p.sendMessage(ChatColor.RED + "El valor no puede ser negativo!");
			return;
		}
		settings.getA(args[1]).set("loselevel", Integer.parseInt(args[2]));
		settings.saveA();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el loselevel de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
	}
}
