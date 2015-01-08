package me.Juanco.Commands;

import java.io.File;
import java.util.Set;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prespawn {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Prespawn() { }
	static Prespawn instance = new Prespawn();
	public static Prespawn getInstance() {
		return instance;
	}
	
	public void prespawn(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.prespawn")) {
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
		int n = 1;
		if (args.length < 3) {
			try {
				Set<String> b = settings.getA(args[1]).getConfigurationSection("spawns").getKeys(false);
				if (b.size() < Integer.parseInt(settings.getA(args[1]).getString("Size"))) {
					n = b.size() + 1;
				} else {
					for (int a = 0; a < b.size();) {
						String s = b.iterator().next();
						b.remove(s);
						if (settings.getA(args[1]).getString("spawns." + s + ".pre") == null) {
							n = Integer.parseInt(s);
							continue;
						}
					}		
				}
			} catch(NullPointerException e) {
				
			}
		}
		if (args.length > 2) {
			try {
				Integer.parseInt(args[2]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El numero de jugador debe ser un numero!");
				return;
			}
			if (args[2].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El numero de jugador no puede ser negativo!");
				return;
			}
			if (Integer.parseInt(args[2]) > Integer.parseInt(settings.getA(args[1]).getString("Size"))) {
				p.sendMessage(ChatColor.RED + "Ese numero es muy grande!");
				return;
			}
			n = Integer.parseInt(args[2]);
		}
		settings.loadA(args[1]);
		settings.getAr().set("spawns." + n + ".pre.world", p.getWorld().getName());
		settings.getAr().set("spawns." + n + ".pre.x", p.getLocation().getX());
		settings.getAr().set("spawns." + n + ".pre.y", p.getLocation().getY());
		settings.getAr().set("spawns." + n + ".pre.z", p.getLocation().getZ());
		settings.getAr().set("spawns." + n + ".pre.yaw", p.getLocation().getYaw());
		settings.getAr().set("spawns." + n + ".pre.pitch", p.getLocation().getPitch());
		settings.saveA();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el prespawn " + ChatColor.AQUA + n + ChatColor.GREEN + " para la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
	}
}
