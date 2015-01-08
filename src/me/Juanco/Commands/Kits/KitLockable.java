package me.Juanco.Commands.Kits;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitLockable {

	ConfigKits ck = ConfigKits.getInstance();
	private KitLockable() { }
	static KitLockable instance = new KitLockable();
	public static KitLockable getInstance() {
		return instance;
	}
	
	public void blockeable(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.lockable")) {
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
		String v = "";
		if (args.length == 3) {
			try {
				if (ck.get().getString("Unlockable").equalsIgnoreCase("true")) {
					v = "false";
				} else {
					v = "true";
				}	
			} catch(NullPointerException e) {
				v = "true";
			}
			ck.get().set("Unlockable", v);
			ck.save();
			p.sendMessage(ChatColor.GREEN + "Establecida la blockeablidad del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
			if (v.equalsIgnoreCase("true")) {
				p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "ON");
			} else {
				p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "OFF");
			}
			return;
		}
		if (args[3].equalsIgnoreCase("off")) {
			try {
				if (ck.get().getString("Unlockable").equalsIgnoreCase("false")) {
					p.sendMessage(ChatColor.RED + "Ese item ya tiene desactivada la bloqueabilidad!");
					return;
				} else {
					ck.get().set("Unlockable", "false");
					ck.save();
					p.sendMessage(ChatColor.GREEN + "Establecida la blockeablidad del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
					p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "OFF");
					return;
				}		
			} catch(NullPointerException e) {
				ck.get().set("Unlockable", "false");
				ck.save();
				p.sendMessage(ChatColor.GREEN + "Establecida la blockeablidad del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
				p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "OFF");
				return;
			}
		}
		if (args[3].equalsIgnoreCase("on")) {
			try {
				if (ck.get().getString("Unlockable").equalsIgnoreCase("true")) {
					p.sendMessage(ChatColor.RED + "Ese item ya tiene activada la bloqueabilidad!");
					return;
				} else {
					ck.get().set("Unlockable", "true");
					ck.save();
					p.sendMessage(ChatColor.GREEN + "Establecida la blockeablidad del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
					p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "ON");
					return;
				}	
			} catch(NullPointerException e) {
				ck.get().set("Unlockable", "true");
				ck.save();
				p.sendMessage(ChatColor.GREEN + "Establecida la blockeablidad del kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
				p.sendMessage(ChatColor.GREEN + "Desbloqueable: " + ChatColor.AQUA + "ON");
				return;
			}
		}
		p.sendMessage(ChatColor.RED + "Uso Incorrecto!");
	}
}
