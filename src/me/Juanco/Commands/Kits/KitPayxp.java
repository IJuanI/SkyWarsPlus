package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitPayxp {

	ConfigKits ck = ConfigKits.getInstance();
	private KitPayxp() { }
	static KitPayxp instance = new KitPayxp();
	public static KitPayxp getInstance() {
		return instance;
	}
	
	public void payxp(Player p, String[] args) {
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
			if (ck.get().getString("Payment").equalsIgnoreCase("Experience")) {
				p.sendMessage(ChatColor.RED + "Este kit ya se paga asi!");
				return;
			}
		} catch(NullPointerException e) {
			
		}
		ck.get().set("Payment", "Experience");
		ck.save();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el modo de pago del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " a " + ChatColor.AQUA + "Experiencia" + ChatColor.GREEN + "!");
	}
}
