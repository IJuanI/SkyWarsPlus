package me.Juanco.Events;

import me.Juanco.others.RestoreItems;
import me.Juanco.skywars.Config;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.ConfigPlayer;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArenaPlayerLose {
	
	ConfigPlayer cp = ConfigPlayer.getInstance();
	Config c = Config.getInstance();
	ConfigArenas ca = ConfigArenas.getInstance();
	private ArenaPlayerLose() { }
	static ArenaPlayerLose instance = new ArenaPlayerLose();
	public static ArenaPlayerLose getInstance() {
		return instance;
	}
	
	public void lose(Player p, String ar) {
		ca.getA(ar).set("playing." + p.getName(), null);
		ca.saveA();
		RestoreItems.getInstance().ri(p);
		if (SkyWars.plugin.usedkit.containsKey(p)) SkyWars.plugin.usedkit.remove(p);
		p.sendMessage(ChatColor.RED + "Has perdido!");
		for (String pls : ca.getAr().getConfigurationSection("playing").getKeys(false)) {
			Player pl = Bukkit.getPlayer(pls);
			pl.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.DARK_AQUA + " ha perdido!");
		}
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		if (ca.getAr().getConfigurationSection("playing").getKeys(false).size() == 1) {
			String s = SkyWars.plugin.ArenaState.get(ar);
			SkyWars.plugin.ArenaState.put(ar, "Waiting");
			SkyWars.plugin.countdown.put(ar, -1);
			Player p2 = Bukkit.getPlayer(ca.getAr().getConfigurationSection("playing").getKeys(false).iterator().next());
			p2.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			try {
				if (s == "playing") {
					RestoreItems.getInstance().ri(p2);
					if (c.getConfig().getString("Broadcast-Win") == null) {
						c.getConfig().set("Broadcast-Win", "true");
						c.saveConfig();
					}
					if (c.getConfig().getString("Broadcast-Win").equalsIgnoreCase("true")) {
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + p2.getName() + ChatColor.GREEN + " ha ganado la arena " + ChatColor.AQUA + ar);
					}
					p2.sendMessage(ChatColor.GREEN + "Has ganado la arena!");
					int money = Integer.parseInt(cp.getP(p2.getName()).getString("Money"));
					if (c.getConfig().getString("Money-Per-Win") == null) {
						c.getConfig().set("Money-Per-Win", 10);
						c.saveConfig();
					}
					int emoney = Integer.parseInt(c.getConfig().getString("Money-Per-Win"));
					money += emoney;
					cp.get().set("Money", money);
					cp.save();
					p2.sendMessage(ChatColor.AQUA + "Has ganado " + ChatColor.DARK_AQUA + emoney + " Monedas" + ChatColor.AQUA + "!");
					ca.getA(ar).set("playing." + p2.getName(), null);
					ca.saveA();
				}
			} catch(NullPointerException e) { }
		}
		return;
	}
}
