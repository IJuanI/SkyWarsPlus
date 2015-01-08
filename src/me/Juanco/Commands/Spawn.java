package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Spawn {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Spawn() { }
	static Spawn instance = new Spawn();
	public static Spawn getInstance() {
		return instance;
	}
	
	public void spawn(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.spawn")) {
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
		if (settings.getA(args[1]).get("Size") == null) {
			p.sendMessage("");
			p.sendMessage(ChatColor.YELLOW + "Configura primero el size!");
			p.sendMessage(ChatColor.DARK_GREEN + "/sw size <arena> <cantidad>");
			return;
		}
		int b = 0;
		if (args.length > 2) {
			try {
				Integer.parseInt(args[2]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El numero de jugador deber ser un numero!");
				return;
			}
			if (args[2].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El numero de jugador no puede ser negativo!");
				return;
			}
			b = Integer.parseInt(args[2])-1;
		}
		if (args.length < 3) {
			try {
				b = settings.getA(args[1]).getConfigurationSection("spawns").getKeys(false).size();	
			} catch(NullPointerException e) {
				
			}
		}
		b += 1;
		if (Integer.parseInt(settings.getA(args[1]).getString("Size")) < b) {
			p.sendMessage(ChatColor.RED + "Ya hay demaciados spawns!");
			return;
		}
		int n = 1;
		if (args.length > 3) {
			try {
				Integer.parseInt(args[3]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El valor debe ser un numero!");
				return;
			}
			if (args[3].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El valor no puede ser negativo!");
				return;
			}
			n = Integer.parseInt(args[3]);
		}
		settings.loadA(args[1]);
		settings.getAr().set("spawns." + b + "." + n + ".world", p.getWorld().getName());
		settings.getAr().set("spawns." + b + "." + n + ".x", p.getLocation().getX());
		settings.getAr().set("spawns." + b + "." + n + ".y", p.getLocation().getY());
		settings.getAr().set("spawns." + b + "." + n + ".z", p.getLocation().getZ());
		settings.getAr().set("spawns." + b + "." + n + ".yaw", p.getLocation().getYaw());
		settings.getAr().set("spawns." + b + "." + n + ".pitch", p.getLocation().getPitch());
		settings.saveA();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el spawn " + ChatColor.AQUA + b + ChatColor.GREEN + "(" + ChatColor.AQUA + n + ChatColor.GREEN + ") de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
	}
}
