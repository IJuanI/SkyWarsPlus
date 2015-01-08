package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitAddcost {

	ConfigKits ck = ConfigKits.getInstance();
	private KitAddcost() { }
	static KitAddcost instance = new KitAddcost();
	public static KitAddcost getInstance() {
		return instance;
	}
	
	public void addcost(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.addcost")) {
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
		try {
			if (ck.get().getString("Unlockable").equalsIgnoreCase("false")) {
				p.sendMessage(ChatColor.RED + "El kit debe ser desbloqueable!");
				return;
			}	
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "El kit debe ser desbloqueable!");
			return;
		}
		if (args.length == 3) {
			p.sendMessage(ChatColor.RED + "Especifica un monto!");
			return;
		}
		try {
			Integer.parseInt(args[3]);
		} catch(IllegalArgumentException e) {
			p.sendMessage(ChatColor.RED + "El monto debe ser un numero!");
			return;
		}
		if (args[3].startsWith("-")) {
			p.sendMessage(ChatColor.RED + "El monto no puede se negativo!");
			return;
		}
		ck.get().set("Cost", Integer.parseInt(args[3]));
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el costo del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[3]);
	}
}
