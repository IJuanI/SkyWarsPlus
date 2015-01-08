package me.Juanco.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {

	private Help() { }
	static Help instance = new Help();
	public static Help getInstance() {
		return instance;
	}
	
	public void help(Player p) {
		p.sendMessage("");
		p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " AYUDA" + ChatColor.BLUE + " ===============");
		p.sendMessage(ChatColor.GREEN + "/sw info: " + ChatColor.GRAY + "Te muestra informacion sobre el plugin!"); //
		p.sendMessage(ChatColor.GREEN + "/sw join <arena>: " + ChatColor.GRAY + "Unete a una arena!");
		p.sendMessage(ChatColor.GREEN + "/sw leave: " + ChatColor.GRAY + "Sal de una arena!");
		p.sendMessage(ChatColor.GREEN + "/sw kit: " + ChatColor.GRAY + "Elije un kit!");
		if (p.hasPermission("sw.admin")) {
			p.sendMessage(ChatColor.GREEN + "/sw admin: " + ChatColor.GRAY + "Comandos Admin!");	
		}
		if (!p.hasPermission("sw.admin")) {
			p.sendMessage("");
		}
		p.sendMessage(ChatColor.GREEN + "/sw stats: " + ChatColor.GRAY + "Mira tus stats!");
		p.sendMessage(ChatColor.GOLD + "/sw 2: " + ChatColor.WHITE + "Siguiente pagina!");
		p.sendMessage("");
	}
	
	public void help2(Player p) {
		p.sendMessage("");
		p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " AYUDA 2" + ChatColor.BLUE + " ===============");
		p.sendMessage(ChatColor.GREEN + "/sw top: " + ChatColor.GRAY + "Admira el top!");
		p.sendMessage(ChatColor.GREEN + "/sw balance (player): " + ChatColor.GRAY + "Mira dinero!"); //
		p.sendMessage(ChatColor.GREEN + "/sw list: " + ChatColor.GRAY + "Un listado de las arenas!");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
	}
}
