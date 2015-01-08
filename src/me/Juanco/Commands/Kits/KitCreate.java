package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class KitCreate {

	ConfigKits ck = ConfigKits.getInstance();
	private KitCreate() { }
	static KitCreate instance = new KitCreate();
	public static KitCreate getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public void create(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.create")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 2) {
			p.sendMessage(ChatColor.RED + "Especifica un id!");
			return;
		}
		if (ck.getfile(args[2]).exists()) {
			p.sendMessage(ChatColor.RED + "Ya existe un kit con ese nombre!");
			return;
		}
		if (args.length == 3) {
			p.sendMessage(ChatColor.RED + "Especifica un slot!");
			return;
		}
		try {
			Integer.parseInt(args[3]);
		} catch(IllegalArgumentException e) {
			p.sendMessage(ChatColor.RED + "El slot debe ser un numero!");
			return;
		}
		if (args[3].startsWith("-")) {
			p.sendMessage(ChatColor.RED + "El slot no puede ser negativo!");
			return;
		}
		if (args[3].equalsIgnoreCase("0")) {
			p.sendMessage(ChatColor.RED + "El slot no puede ser 0!");
			return;
		}
		if (args.length == 4) {
			p.sendMessage(ChatColor.RED + "Especifica un ItemID!");
			return;
		}
		String m;
		try {
			Integer.parseInt(args[4]);
			try {
				m = Material.getMaterial(Integer.parseInt(args[4])).toString();	
			} catch(NullPointerException e) {
				p.sendMessage(ChatColor.RED + "Ese ItemID no existe!");
				return;
			}
		} catch(IllegalArgumentException e) {
			try {
				m = Material.getMaterial(args[4].toUpperCase()).toString();	
			} catch(NullPointerException ee) {
				p.sendMessage(ChatColor.RED + "Ese ItemID no existe!");
				return;
			}
		}
		if (args.length == 5) {
			p.sendMessage(ChatColor.RED + "Especifica un nombre!");
			return;
		}
		int n = 1;
		String name = "";
		for (String arg : args) {
			if (n > 5) {
				name += arg + " ";
			}
			n++;
		}
		name = name.substring(0, name.length()-1);
		ck.create(args[2]);
		ck.load(args[2]);
		ck.get().set("Slot", Integer.parseInt(args[3]));
		ck.get().set("ID", m);
		ck.get().set("Name", name);
		ck.save();
		name = name.replaceAll("&", "§");
		p.sendMessage(ChatColor.GREEN + "Creado exitosamente el kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
		p.sendMessage(ChatColor.GREEN + "Slot: " + ChatColor.AQUA + args[3]);
		p.sendMessage(ChatColor.GREEN + "ID: " + ChatColor.AQUA + m);
		p.sendMessage(ChatColor.GREEN + "Nombre: " + ChatColor.AQUA + name);
	}
}
