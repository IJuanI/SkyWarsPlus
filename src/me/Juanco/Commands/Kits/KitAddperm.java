package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitAddperm {

	ConfigKits ck = ConfigKits.getInstance();
	private KitAddperm() { }
	static KitAddperm instance = new KitAddperm();
	public static KitAddperm getInstance() {
		return instance;
	}
	
	public void addperm(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.addperm")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica un id!");
			return;
		}
		if (!ck.getfile(args[2]).exists()) {
			p.sendMessage(ChatColor.RED + "Ese kit no existe!");
			return;
		}
		if (args.length == 3) {
			p.sendMessage(ChatColor.RED + "Especifica un permiso!");
			return;
		}
		try {
			Integer.parseInt(args[3]);
			p.sendMessage(ChatColor.RED + "El permiso no puede ser un numero!");
			return;
		} catch(IllegalArgumentException e) {
			
		}
		ck.getF(args[2]).createSection("Permissions." + args[3].replace(".", ","));
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el permiso de " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
	}
}
