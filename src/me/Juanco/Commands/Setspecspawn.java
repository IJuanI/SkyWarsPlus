package me.Juanco.Commands;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Setspecspawn {

	ConfigArenas settings = ConfigArenas.getInstance();
	private Setspecspawn() { }
	static Setspecspawn instance = new Setspecspawn();
	public static Setspecspawn getInstance() {
		return instance;
	}
	
	public void setspecspawn(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.setspecspawn")) {
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
		settings.loadA(args[1]);
		settings.getAr().set("specspawn.world", p.getWorld().getName());
		settings.getAr().set("specspawn.x", p.getLocation().getX());
		settings.getAr().set("specspawn.y", p.getLocation().getY());
		settings.getAr().set("specspawn.z", p.getLocation().getZ());
		settings.getAr().set("specspawn.yaw", p.getLocation().getYaw());
		settings.getAr().set("specspawn.pitch", p.getLocation().getPitch());
		settings.saveA();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el specspawn de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
	}
}
