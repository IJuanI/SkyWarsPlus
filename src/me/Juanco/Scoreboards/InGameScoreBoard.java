package me.Juanco.Scoreboards;

import java.io.File;

import me.Juanco.skywars.Config;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.ConfigPlayer;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;


public class InGameScoreBoard {
	
	ConfigArenas ca = ConfigArenas.getInstance();
	ConfigPlayer cp = ConfigPlayer.getInstance();
	Config c = Config.getInstance();
	private Scoreboard bor1 = SkyWars.plugin.board1;
	private InGameScoreBoard() { }
	static InGameScoreBoard instance = new InGameScoreBoard();
	public static InGameScoreBoard getInstance() {
		return instance;
	}
	
	public void display(Player p) {
		Objective o;
		try {
			o = bor1.registerNewObjective("sc game", "dummy");
		} catch(IllegalArgumentException e) {
			o = bor1.getObjective(DisplaySlot.SIDEBAR);
		}
		if (c.getConfig().getString("Board.InGame.Title") == null) c.getConfig().set("Board.InGame.Title", "&6SkyWars &fPlus");
		if (c.getConfig().getString("Board.InGame.1") == null) c.getConfig().set("Board.InGame.1", "&5Jugadores: &9%ps%"); {
			if (c.getConfig().getString("Board.InGame.2") == null) c.getConfig().set("Board.InGame.2", "&5Espec: &90");
			if (c.getConfig().getString("Board.InGame.3") == null) c.getConfig().set("Board.InGame.3", "&5Tiempo: &90");
		}
		c.saveConfig();
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (int n = 0; n < c.getConfig().getConfigurationSection("Board.InGame").getKeys(false).size(); n++) {
			String title = c.getConfig().getString("Board.InGame.Title");
			title = title.replace("&", "§");
			if (n == 0) {
				o.setDisplayName(title);
			} else {
				String cont = c.getConfig().getString("Board.InGame." + n);
				cont = cont.replace("&", "§");
				cont = cont.replace("%m%", cp.getP(p.getName()).getString("Money"));
				cont = cont.replace("%wp%", String.valueOf(p.getWorld().getPlayers().size()));
				cont = cont.replace("%p%", p.getName());
				for (File f : ca.getfolder().listFiles()) {
					FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
					try {
						if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
							cont = cont.replace("%ps%", String.valueOf(fc.getConfigurationSection("playing").getKeys(false).size()));
						}
					} catch(NullPointerException e) {
						
					}
				}
				if (SkyWars.plugin.usedkit.containsKey(p)) {
					cont = cont.replace("%k%", SkyWars.plugin.usedkit.get(p));
				} else {
					cont = cont.replace("%k%", "Sin kit!");
				}
				if (cont.length() > 16) {
					cont = cont.substring(0, 16);
				}
				Score score = o.getScore(cont);
				score.setScore(c.getConfig().getConfigurationSection("Board.InGame").getKeys(false).size()-n);
			}
		}
		SkyWars.plugin.c = true;
		SkyWars.plugin.board1 = bor1;

	}
	
	public void update(Player p) {
		bor1 = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o;
		try {
			o = bor1.registerNewObjective("sc game", "dummy");
		} catch(IllegalArgumentException e) {
			o = bor1.getObjective(DisplaySlot.SIDEBAR);
		}
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (int n = 0; n < c.getConfig().getConfigurationSection("Board.InGame").getKeys(false).size(); n++) {
			String title = c.getConfig().getString("Board.InGame.Title");
			title = title.replace("&", "§");
			if (n == 0) {
				o.setDisplayName(title);
			} else {
				String cont = c.getConfig().getString("Board.InGame." + n);
				cont = cont.replace("&", "§");
				cont = cont.replace("%m%", cp.getP(p.getName()).getString("Money"));
				cont = cont.replace("%wp%", String.valueOf(p.getWorld().getPlayers().size()));
				cont = cont.replace("%p%", p.getName());
				for (File f : ca.getfolder().listFiles()) {
					FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
					try {
						if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
							cont = cont.replace("%ps%", String.valueOf(fc.getConfigurationSection("playing").getKeys(false).size()));
						}
					} catch(NullPointerException e) {
						
					}
				}
				if (SkyWars.plugin.usedkit.containsKey(p)) {
					cont = cont.replace("%k%", SkyWars.plugin.usedkit.get(p));
				} else {
					cont = cont.replace("%k%", "Sin kit!");
				}
				if (cont.length() > 16) {
					cont = cont.substring(0, 16);
				}
				bor1.getObjective(DisplaySlot.SIDEBAR).getScore(cont).setScore(c.getConfig().getConfigurationSection("Board.InGame").getKeys(false).size()-n);
			}
		}
		p.setScoreboard(bor1);
		SkyWars.plugin.board = bor1;
	}
}