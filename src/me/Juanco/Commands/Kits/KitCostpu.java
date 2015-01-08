package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitCostpu {

	ConfigKits ck = ConfigKits.getInstance();
	private KitCostpu() { }
	static KitCostpu instance = new KitCostpu();
	public static KitCostpu getInstance() {
		return instance;
	}
	
	public void costpu(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.costpu")) {
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
			p.sendMessage(ChatColor.RED + "El monto no puede ser negativo!");
			return;
		}
		ck.get().set("Cost-Per-Use", Integer.parseInt(args[3]));
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el costo por uso del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
	}
}
