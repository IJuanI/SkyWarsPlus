package me.Juanco.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Info {

	private Info() { }
	static Info instance = new Info();
	public static Info getInstance() {
		return instance;
	}
	
	public void info(Player p) {
		p.sendMessage("");
		p.sendMessage(ChatColor.DARK_GREEN + "===============" + ChatColor.GREEN + " INFO" + ChatColor.DARK_GREEN + " ===============");
		p.sendMessage(ChatColor.GOLD + "Nombre: " + ChatColor.WHITE + "SkyWarsPlus");
		p.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.WHITE + "1.0");
		p.sendMessage(ChatColor.GOLD + "Autor: " + ChatColor.WHITE + "Juanco");
		p.sendMessage(ChatColor.GOLD + "Server Oficial: " + ChatColor.AQUA + "terranex.mcph.co                       " + ChatColor.WHITE + "||" + ChatColor.GREEN + "NO PREMIUM" + ChatColor.WHITE + "||");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
	}
}
