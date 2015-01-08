package me.Juanco.Commands;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Balance {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private Balance() { }
	static Balance instance = new Balance();
	public static Balance getInstance() {
		return instance;
	}
	
	public void balance(Player p, String[] args) {
		if (args.length == 1) {
			String m = cp.getP(p.getName()).getString("Money");
			p.sendMessage(ChatColor.GREEN + "Balance actual: " + ChatColor.AQUA + m);
			return;
		}
		if (!cp.getFile(args[1]).exists()) {
			p.sendMessage(ChatColor.RED + "El jugador no existe o no tiene balance!");
			return;
		}
		String m = cp.getP(args[1]).getString("Money");
		p.sendMessage(ChatColor.GREEN + "Balance de " + cp.getFile(args[1]).getName().substring(0, cp.getFile(args[1]).getName().length()-4) + ": " + ChatColor.AQUA + m);
	}
}
