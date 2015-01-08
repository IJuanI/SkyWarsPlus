package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitPayeco {

	ConfigKits ck = ConfigKits.getInstance();
	private KitPayeco() { }
	static KitPayeco instance = new KitPayeco();
	public static KitPayeco getInstance() {
		return instance;
	}
	
	public void payeco(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.payxp")) {
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
			if (ck.get().getString("Payment").equalsIgnoreCase("Economy")) {
				p.sendMessage(ChatColor.RED + "Este kit ya se paga asi!");
				return;
			}
		} catch(NullPointerException e) {
			
		}
		ck.get().set("Payment", "Economy");
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el modo de pago del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " a " + ChatColor.AQUA + "Dinero" + ChatColor.GREEN + "!");
	}
}
