package me.Juanco.Commands;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AdminBalance {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private AdminBalance() { }
	static AdminBalance instance = new AdminBalance();
	public static AdminBalance getInstance() {
		return instance;
	}
	
	public void balance(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.balance")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 2) {
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " BALANCE" + ChatColor.BLUE + " ===============");
			if (p.hasPermission("sw.admin.balance.set")) {
				p.sendMessage(ChatColor.GREEN + "/sw admin balance set <player> <amount>:");
				p.sendMessage(ChatColor.GRAY + " Establece un balance!");	
			}
			if (!p.hasPermission("sw.admin.balance.set")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.balance.add")) {
				p.sendMessage(ChatColor.GREEN + "/sw admin balance add <player> <amount>: " + ChatColor.GRAY + "Añade dinero!");
			}
			if (!p.hasPermission("sw.admin.balance.add")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.balance.remove")) {
				p.sendMessage(ChatColor.GREEN + "/sw admin balance remove <player> <amount>: " + ChatColor.GRAY + "Quita Dinero!");
			}
			if (!p.hasPermission("sw.admin.balance.remove")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.balance.clear")) {
				p.sendMessage(ChatColor.GREEN + "/sw admin balance clear <player>: " + ChatColor.GRAY + "Borra un balance!");
			}
			if (!p.hasPermission("sw.admin.balance.clear")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.balance.create")) {
				p.sendMessage(ChatColor.GREEN + "/sw admin balance create <player>: " + ChatColor.GRAY + "Crea un balance!");
			}
			if (!p.hasPermission("sw.admin.balance.create")) {
				p.sendMessage("");
			}
			p.sendMessage("");
			p.sendMessage("");
			return;
		}
		if (args[2].equalsIgnoreCase("set")) {
			if (!p.hasPermission("sw.admin.balance.set")) {
				p.sendMessage("Comando Desconocido!");
				return;
			}
			if (args.length == 3) {
				p.sendMessage(ChatColor.RED + "Especifica un jugador!");
				return;
			}
			if (!cp.getFile(args[3]).exists()) {
				p.sendMessage(ChatColor.RED + "Ese jugador no existe o no tiene balance!");
				return;
			}
			if (args.length == 4) {
				p.sendMessage(ChatColor.RED + "Especifica un monto!");
				return;
			}
			try {
				Integer.parseInt(args[4]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El monto debe ser un numero!");
				return;
			}
			if (args[4].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El monto no puede ser negativo!");
				return;
			}
			cp.getP(args[3]).set("Money", Integer.parseInt(args[4]));
			cp.save();
			p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el balance de " + ChatColor.AQUA + args[3] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[4] + ChatColor.GREEN + "!");
			return;
		}
		
		if (args[2].equalsIgnoreCase("add")) {
			if (!p.hasPermission("sw.admin.balance.add")) {
				p.sendMessage("Comando Desconocido!");
				return;
			}
			if (args.length == 3) {
				p.sendMessage(ChatColor.RED + "Especifica un jugador!");
				return;
			}
			if (!cp.getFile(args[3]).exists()) {
				p.sendMessage(ChatColor.RED + "Ese jugador no existe o no tiene balance!");
				return;
			}
			if (args.length == 4) {
				p.sendMessage(ChatColor.RED + "Especifica un monto!");
				return;
			}
			try {
				Integer.parseInt(args[4]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El monto debe ser un numero!");
				return;
			}
			if (args[4].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El monto no puede ser negativo!");
				return;
			}
			int m = Integer.parseInt(cp.getP(args[3]).getString("Money"));
			m += Integer.parseInt(args[4]);
			cp.getP(args[3]).set("Money", m);
			cp.save();
			p.sendMessage(ChatColor.GREEN + "Añadido exitosamente " + ChatColor.AQUA + "$" + args[4] + ChatColor.GREEN + " a " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
			p.sendMessage(ChatColor.GREEN + "Balance de " + args[3] + ": " + ChatColor.AQUA + "$" + m);
			return;
		}
		
		if (args[2].equalsIgnoreCase("remove")) {
			if (!p.hasPermission("sw.admin.balance.remove")) {
				p.sendMessage("Comando Desconocido!");
				return;
			}
			if (args.length == 3) {
				p.sendMessage(ChatColor.RED + "Especifica un jugador!");
				return;
			}
			if (!cp.getFile(args[3]).exists()) {
				p.sendMessage(ChatColor.RED + "Ese jugador no existe o no tiene balance!");
				return;
			}
			if (args.length == 4) {
				p.sendMessage(ChatColor.RED + "Especifica un monto!");
				return;
			}
			try {
				Integer.parseInt(args[4]);
			} catch(IllegalArgumentException e) {
				p.sendMessage(ChatColor.RED + "El monto debe ser un numero!");
				return;
			}
			if (args[4].startsWith("-")) {
				p.sendMessage(ChatColor.RED + "El monto no puede ser negativo!");
				return;
			}
			int m = Integer.parseInt(cp.getP(args[3]).getString("Money"));
			m -= Integer.parseInt(args[4]);
			cp.getP(args[3]).set("Money", m);
			cp.save();
			p.sendMessage(ChatColor.GREEN + "Removido exitosamente " + ChatColor.AQUA + "$" + args[4] + ChatColor.GREEN + " de " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
			p.sendMessage(ChatColor.GREEN + "Balance de " + args[3] + ": " + ChatColor.AQUA + "$" + m);
			return;
		}
		
		if (args[2].equalsIgnoreCase("clear")) {
			if (!p.hasPermission("sw.admin.balance.clear")) {
				p.sendMessage("Comando Desconocido!");
				return;
			}
			if (args.length == 3) {
				p.sendMessage(ChatColor.RED + "Especifica un jugador!");
				return;
			}
			if (!cp.getFile(args[3]).exists()) {
				p.sendMessage(ChatColor.RED + "Ese jugador no existe o no tiene balance!");
				return;
			}
			cp.getP(args[3]).set("Money", "0");
			cp.save();
			p.sendMessage(ChatColor.GREEN + "Borrado exitosamente el balance de " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
			return;
		}
		
		if (args[2].equalsIgnoreCase("create")) {
			if (!p.hasPermission("sw.admin.balance.create")) {
				p.sendMessage("Comando Desconocido!");
				return;
			}
			if (args.length == 3) {
				p.sendMessage(ChatColor.RED + "Especifica un jugador!");
				return;
			}
			if (cp.getFile(args[3]).exists()) {
				p.sendMessage(ChatColor.RED + "El jugador " + args[3] + " ya existe!");
				return;
			}
			cp.create(args[3]);
			p.sendMessage(ChatColor.GREEN + "Creado exitosamente el balance de " + ChatColor.AQUA + args[3] + ChatColor.GREEN + "!");
			return;
		}
	}
}
