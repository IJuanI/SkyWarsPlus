package me.Juanco.Commands.Kits;

import java.util.ArrayList;

import me.Juanco.skywars.ConfigKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class KitSetoptions {

	ConfigKits ck = ConfigKits.getInstance();
	ArrayList<String> l = new ArrayList<String>();
	private KitSetoptions() { }
	static KitSetoptions instance = new KitSetoptions();
	public static KitSetoptions getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public void setoptions(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits.setoptions")) {
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
			message1(p);
			return;
		}
		l.add("unarmed-damage"); l.add("extra-damage"); l.add ("effect"); l.add("jump-boost"); l.add("speed"); l.add("no-hunger"); l.add("extra-hearts"); l.add("nlifes");
		if (!l.contains(args[3].toLowerCase())) {
			message2(p, args[3]);
			return;
		}
		if (args[3].equalsIgnoreCase("no-hunger")) {
			ck.get().set("Options." + args[3].toLowerCase(), "true");
			ck.save();
			p.sendMessage(ChatColor.GREEN + "Establecida exitosamente la opcion " + ChatColor.AQUA + args[3] + ChatColor.GREEN + " en el kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
			return;
		}
		if (args.length == 4) {
			p.sendMessage(ChatColor.RED + "Especifica un valor!");
			return;
		}
		String effect = "";
		if (args[3].equalsIgnoreCase("effect")) {
			try {
				Integer.parseInt(args[4]);
				try {
					effect = PotionEffectType.getById(Integer.parseInt(args[4])).getName();
				} catch(NullPointerException ee) {
					p.sendMessage(ChatColor.RED + "No hay efecto con ese id!");
					return;
				}
			} catch(IllegalArgumentException e) {
				try {
					effect = PotionEffectType.getByName(args[4].toUpperCase()).getName();	
				} catch(NullPointerException ee) {
					p.sendMessage(ChatColor.RED + "No existe el efecto " + args[4] + "!");
					return;
				}
			}
			if (args.length == 5) {
				p.sendMessage(ChatColor.RED + "Especifica un nivel!");
				return;
			}
			try {
				Integer.parseInt(args[5]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El nivel debe ser un numero!");
				return;
			}
			if (args[5].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El nivel no puede ser negativo!");
			}
		}
		if (!args[3].equalsIgnoreCase("effect") && !args[3].equalsIgnoreCase("no-hunger")) {
			try {
				Boolean.parseBoolean(args[4]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El valor debe ser un numero!");
				return;
			}
			if (args[4].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El valor no puede ser negativo!");
				return;
			}
		}
		if (l.contains(args[3].toLowerCase())) {
			if (args[3].equalsIgnoreCase("effect")) {
				ck.get().set("Options." + args[3].toLowerCase() + ".name", effect);
				ck.get().set("Options." + args[3].toLowerCase() + ".level", args[5]);
				ck.save();
				p.sendMessage(ChatColor.GREEN + "Establecida exitosamente la opcion " + ChatColor.AQUA + args[3] + ChatColor.GREEN + " en el kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
				return;
			}
			ck.get().set("Options." + args[3].toLowerCase(), args[4]);
			ck.save();
			p.sendMessage(ChatColor.GREEN + "Establecida exitosamente la opcion " + ChatColor.AQUA + args[3] + ChatColor.GREEN + " en el kit " + ChatColor.AQUA + args[2] + ChatColor.GREEN + "!");
			return;
		}
	}
	
	private void message1(Player p) {
		p.sendMessage(ChatColor.RED + "Opcion Invalida!");
		p.sendMessage(ChatColor.DARK_GREEN + "Opciones validas: " + ChatColor.YELLOW + options());
	}
	
	private void message2(Player p, String s) {
		p.sendMessage(ChatColor.RED + "La opcion " + s + " no existe!");
		p.sendMessage(ChatColor.DARK_GREEN + "Opciones validas: " + ChatColor.YELLOW + options());
	}
	
	private String options() {
		String s = "Unarmed-Damage, Extra-Damage, Effect, Jump-boost, Speed, No-Hunger, Extra-Hearts, nLifes";
		return s;
	}
}
