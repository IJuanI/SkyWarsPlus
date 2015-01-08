package me.Juanco.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AdminHelp {

	private AdminHelp() { }
	static AdminHelp instance = new AdminHelp();
	public static AdminHelp getInstance() {
		return instance;
	}
	
	public void adminhelp(Player p) {
		if (!p.hasPermission("sw.admin")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		p.sendMessage("");
		p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " ADMIN" + ChatColor.BLUE + " ===============");
		if (p.hasPermission("sw.admin.create")) {
			p.sendMessage(ChatColor.GREEN + "/sw create <arena>: " + ChatColor.GRAY + "Crea una arena!"); //	
		}
		if (!p.hasPermission("sw.admin.create")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.delete")) {
			p.sendMessage(ChatColor.GREEN + "/sw delete <arena>: " + ChatColor.GRAY + "Elimina una arena!"); //
		}
		if (!p.hasPermission("sw.admin.delete")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.size")) {
			p.sendMessage(ChatColor.GREEN + "/sw size <arena> <valor>: " + ChatColor.GRAY + "Cambia el tamaño de una arena!"); //
		}
		if (!p.hasPermission("sw.admin.size")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.spawn")) {
			p.sendMessage(ChatColor.GREEN + "/sw spawn <arena> (nplayer) (n): " + ChatColor.GRAY + "Establece los spawns!"); //
		}
		if (!p.hasPermission("sw.admin.spawn")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.lobby")) {
			p.sendMessage(ChatColor.GREEN + "/sw lobby: " + ChatColor.GRAY + "Establece un lobby!"); //
		}
		if (!p.hasPermission("sw.admin.lobby")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.prespawn")) {
			p.sendMessage(ChatColor.GREEN + "/sw prespawn <arena> (nplayer): " + ChatColor.GRAY + "Establece un prespawn!"); //
		}
		if (!p.hasPermission("sw.admin.prespawn")) {
			p.sendMessage("");
		}
		p.sendMessage(ChatColor.GOLD + "/sw admin 2: " + ChatColor.WHITE + "Siguiente pagina!");
		p.sendMessage("");
	}
	
	public void adminhelp2(Player p) {
		if (!p.hasPermission("sw.admin")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		p.sendMessage("");
		p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " ADMIN 2" + ChatColor.BLUE + " ===============");
		if (p.hasPermission("sw.admin.waitroom")) {
			p.sendMessage(ChatColor.GREEN + "/sw waitroom <arena>: " + ChatColor.GRAY + "Establece una sala de espera!"); //
		}
		if (!p.hasPermission("sw.admin.waitroom")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.setspecspawn")) {
			p.sendMessage(ChatColor.GREEN + "/sw setspecspawn <arena>: " + ChatColor.GRAY + "Establece spawn espectadores!"); //
		}
		if (!p.hasPermission("sw.admin.setspecspawn")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.loselevel")) {
			p.sendMessage(ChatColor.GREEN + "/sw loselevel <arena> (Valor): " + ChatColor.GRAY + "Establece un loselevel!"); //
		}
		if (!p.hasPermission("sw.admin.loselevel")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.balance")) {
			p.sendMessage(ChatColor.GREEN + "/sw admin balance: " + ChatColor.GRAY + "Edita el balance!"); //
		}
		if (!p.hasPermission("sw.admin.balance")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.kits")) {
			p.sendMessage(ChatColor.GREEN + "/sw kits: " + ChatColor.GRAY + "Kits!"); //
		}
		if (!p.hasPermission("sw.admin.kits")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.falldamage")) {
			p.sendMessage(ChatColor.GREEN + "/sw falldamage (on/off) <arena>: " + ChatColor.GRAY + "Altera el daño de caida!"); //
		}
		if (!p.hasPermission("sw.admin.falldamage")) {
			p.sendMessage("");
		}
		p.sendMessage(ChatColor.GOLD + "/sw admin 3: " + ChatColor.WHITE + "Siguiente pagina!");
		p.sendMessage("");
	}
	
	public void adminhelp3(Player p) {
		if (!p.hasPermission("sw.admin")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		p.sendMessage("");
		p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " ADMIN 3" + ChatColor.BLUE + " ===============");
		if (p.hasPermission("sw.admin.start")) {
			p.sendMessage(ChatColor.GREEN + "/sw start <arena>: " + ChatColor.GRAY + "Empieza una arena!");	
		}
		if (!p.hasPermission("sw.admin.start")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.stop")) {
			p.sendMessage(ChatColor.GREEN + "/sw stop <arena>: " + ChatColor.GRAY + "Para una arena!");	
		}
		if (!p.hasPermission("sw.admin.stop")) {
			p.sendMessage("");
		}
		if (p.hasPermission("sw.admin.selector")) {
			p.sendMessage(ChatColor.GREEN + "/sw selector <ItemID> <slot>: " + ChatColor.GRAY + "Modifica el selector!");	
		}
		if (!p.hasPermission("sw.admin.selector")) {
			p.sendMessage("");
		}
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
	}
}
