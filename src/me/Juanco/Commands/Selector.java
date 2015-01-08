package me.Juanco.Commands;

import me.Juanco.skywars.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Selector {

	Config settings = Config.getInstance();
	private Selector() { }
	static Selector instance = new Selector();
	public static Selector getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public void selector(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.selector")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 1) {
			p.sendMessage(ChatColor.RED + "Especifica un ItemID");
			return;
		}
		Material m;
		try {
			Integer.parseInt(args[1]);
			try {
				Material.getMaterial(Integer.parseInt(args[1])).toString();
				m = Material.getMaterial(Integer.parseInt(args[1]));
			} catch(NullPointerException e) {
				p.sendMessage(ChatColor.RED + "No hay item con ese id!");
				return;
			}
		} catch(IllegalArgumentException e) {
			try {
				Material.getMaterial(args[1].toUpperCase()).toString();
				m = Material.getMaterial(args[1].toUpperCase());
			} catch(NullPointerException ee) {
				p.sendMessage(ChatColor.RED + "No hay item con ese id!");
				return;
			}
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica un slot!");
			return;
		}
		try {
			Integer.parseInt(args[2]);
		} catch(IllegalArgumentException e) {
			p.sendMessage(ChatColor.RED + "El slot debe ser un numero!");
			return;
		}
		if (args[2].startsWith("-")) {
			p.sendMessage(ChatColor.RED + "El slot no puede ser negativo!");
			return;
		}
		if (args[2].equalsIgnoreCase("0")) {
			p.sendMessage(ChatColor.RED + "El slot no puede ser 0!");
			return;
		}
		settings.getConfig().set("Selector.Slot", Integer.parseInt(args[2]));
		settings.getConfig().set("Selector.ID", m.toString());
		settings.saveConfig();
		p.sendMessage(ChatColor.GREEN + "Modificado exitosamente el selector!");
		p.sendMessage(ChatColor.GREEN + "Slot: " + ChatColor.AQUA + Integer.parseInt(args[2]));
		p.sendMessage(ChatColor.GREEN + "ID: " + ChatColor.AQUA + m.toString());
	}
}
