package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Falldamage {

	ConfigArenas ca = ConfigArenas.getInstance();
	private Falldamage() { }
	static Falldamage instance = new Falldamage();
	public static Falldamage getInstance() {
		return instance;
	}
	
	public void falldamage(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.falldamage")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 1) {
			p.sendMessage(ChatColor.RED + "Especifica una arena!");
			return;
		}
		if (!args[1].equalsIgnoreCase("on") && !args[1].equalsIgnoreCase("off")) {
			try {
				boolean b = true;
				for (File f : ca.getfolder().listFiles()) {
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
			String v = "";
			try {
				if (ca.getA(args[1]).getString("Falldamage").equalsIgnoreCase("false")) {
					v = "true";
				} else {
					v = "false";
				}
			} catch(NullPointerException e) {
				v = "false";
			}
			ca.getA(args[1]).set("Falldamage", v);
			ca.saveA();
			if (v == "true") {
				p.sendMessage(ChatColor.GREEN + "Activado exitosamente el daño de caida en la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
				return;
			} else {
				p.sendMessage(ChatColor.DARK_GREEN + "Desactivado exitosamente el daño de caida en la arena " + ChatColor.AQUA + args[1] + ChatColor.DARK_GREEN + "!");
				return;
			}
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica una arena!");
			return;
		}
		try {
			boolean b = true;
			for (File f : ca.getfolder().listFiles()) {
				if (f.getName().substring(0, f.getName().length()-4).equalsIgnoreCase(args[2])) {
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
		if (args[1].equalsIgnoreCase("on")) {
			if (ca.getA(args[2]).getString("Falldamage").equalsIgnoreCase("true")) {
				p.sendMessage(ChatColor.RED + "Esa opcion ya esta activada en esa arena!");
				return;
			}
			ca.getAr().set("Falldamage", "true");
			ca.saveA();
			p.sendMessage(ChatColor.GREEN + "Activado exitosamente el daño de caida en la arena " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
			return;
		}
		if (args[1].equalsIgnoreCase("off")) {
			if (ca.getA(args[2]).getString("Falldamage").equalsIgnoreCase("false")) {
				p.sendMessage(ChatColor.RED + "Esa opcion ya esta desactivada en esa arena!");
				return;
			}
			ca.getAr().set("Falldamage", "false");
			ca.saveA();
			p.sendMessage(ChatColor.DARK_GREEN + "Desactivado exitosamente el daño de caida en la arena " + ChatColor.AQUA + args[2] + ChatColor.DARK_GREEN + "!");
			return;
		}
	}
}
