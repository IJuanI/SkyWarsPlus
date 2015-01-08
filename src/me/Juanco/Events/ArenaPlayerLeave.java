package me.Juanco.Events;

import java.io.File;

import me.Juanco.others.RestoreItems;
import me.Juanco.skywars.Config;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.ConfigPlayer;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ArenaPlayerLeave {
	
	ConfigPlayer cp = ConfigPlayer.getInstance();
	Config c = Config.getInstance();
	ConfigArenas ca = ConfigArenas.getInstance();
	private ArenaPlayerLeave() { }
	static ArenaPlayerLeave instance = new ArenaPlayerLeave();
	public static ArenaPlayerLeave getInstance() {
		return instance;
	}
	
	public void leavec(Player p) {
		boolean b = true;
		String s = "";
		for (File f : ca.getfolder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			try {
				if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
					b = false;
					s = f.getName().substring(0, f.getName().length()-4);
				}
			} catch(NullPointerException e) {
				
			}
		}
		if (b == true) {
			p.sendMessage(ChatColor.RED + "No estas en ninguna lista de espera!");
			return;
		}
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		ca.getA(s).set("playing." + p.getName(), null);
		ca.saveA();
		RestoreItems.getInstance().ri(p);
		if (SkyWars.plugin.usedkit.containsKey(p)) SkyWars.plugin.usedkit.remove(p);
		p.sendMessage(ChatColor.DARK_GREEN + "Has salido exitosamente de la lista de espera!");
		for (String pls : ca.getAr().getConfigurationSection("playing").getKeys(false)) {
			Player pl = Bukkit.getPlayer(pls);
			pl.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.DARK_AQUA + " ha abandonado la partida!");
		}
		if (ca.getAr().getConfigurationSection("playing").getKeys(false).size() == 1) {
			String ss = SkyWars.plugin.ArenaState.get(s);
			SkyWars.plugin.ArenaState.put(s, "Waiting");
			SkyWars.plugin.countdown.put(s, -1);
			Player p2 = Bukkit.getPlayer(ca.getAr().getConfigurationSection("playing").getKeys(false).iterator().next());
			p2.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			try {
				if (ss == "playing") {
					RestoreItems.getInstance().ri(p2);
					if (c.getConfig().getString("Broadcast-Win") == null) {
						c.getConfig().set("Broadcast-Win", "true");
						c.saveConfig();
					}
					if (c.getConfig().getString("Broadcast-Win").equalsIgnoreCase("true")) {
						Bukkit.broadcastMessage(ChatColor.DARK_AQUA + p2.getName() + ChatColor.GREEN + " ha ganado la arena " + ChatColor.AQUA + s + ChatColor.DARK_GREEN + " (por default!)");
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
					ca.getA(s).set("playing." + p2.getName(), null);
					ca.saveA();
				}
			} catch(NullPointerException e) { }
		}
		return;
	}
}
