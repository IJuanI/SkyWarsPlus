package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitDelete {

	private KitDelete() { }
	static KitDelete instance = new KitDelete();
	public static KitDelete getInstance() {
		return instance;
	}
	
	public void delete(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.delete")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica un id!");
			return;
		}
		if (!ConfigKits.getInstance().getfile(args[2]).exists()) {
			p.sendMessage(ChatColor.RED + "Ese kit no existe!");
			return;
		}
		ConfigKits.getInstance().getfile(args[2]).delete();
		p.sendMessage(ChatColor.GREEN + "Borrado exitosamente el kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
	}
}
