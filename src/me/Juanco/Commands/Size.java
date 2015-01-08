package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Size {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Size() { }
	static Size instance = new Size();
	public static Size getInstance() {
		return instance;
	}
	
	public void size(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.size")) {
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
			p.sendMessage(ChatColor.RED + "Especifica una cantidad!");
			return;
		}
		try {
			Integer.parseInt(args[2]);
		} catch(IllegalArgumentException e) {
			p.sendMessage(ChatColor.RED + "La cantidad debe ser un numero!");
			return;
		}
		if (args[2].startsWith("-")) {
			p.sendMessage(ChatColor.RED + "La cantidad no puede ser negativa!");
			return;
		}
		settings.getA(args[1]).set("Size", args[2]);
		settings.saveA();
		p.sendMessage(ChatColor.GREEN + "Establecida la cantidad de jugadores de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
	}
}
