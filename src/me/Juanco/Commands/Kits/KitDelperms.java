package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitDelperms {

	ConfigKits ck = ConfigKits.getInstance();
	private KitDelperms() { }
	static KitDelperms instance = new KitDelperms();
	public static KitDelperms getInstance() {
		return instance;
	}
	
	public void delperms(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.delperms")) {
			p.sendMessage("Comando Desconocido");
			return;
		}
		if (args.length == 2) {
			p.sendMessage(org.bukkit.ChatColor.RED + "Especifica un id!");
			return;
		}
		if (!ck.getfile(args[2]).exists()) {
			p.sendMessage(ChatColor.RED + "Ese kit no existe!");
			return;
		}
		try {
			ck.get().getConfigurationSection("Permissions").getKeys(false);
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "Ese kit no tiene pemisos!");
			return;
		}
		ck.get().set("Pemissions", null);
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Eliminados exitosamente los permisos del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
	}
}
