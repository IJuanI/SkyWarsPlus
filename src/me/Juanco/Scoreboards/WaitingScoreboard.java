package me.Juanco.Scoreboards;

import java.io.File;
import java.util.HashMap;

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


public class WaitingScoreboard {
	
	ConfigArenas ca = ConfigArenas.getInstance();
	ConfigPlayer cp = ConfigPlayer.getInstance();
	Config c = Config.getInstance();
	private HashMap<String, Integer> i1 = new HashMap<String, Integer>();
	private Scoreboard bor = SkyWars.plugin.board;
	private WaitingScoreboard() { }
	static WaitingScoreboard instance = new WaitingScoreboard();
	public static WaitingScoreboard getInstance() {
		return instance;
	}
	
	public void display(Player p) {
		Objective o;
		try {
			o = bor.registerNewObjective("sc", "dummy");
		} catch(IllegalArgumentException e) {
			o = bor.getObjective(DisplaySlot.SIDEBAR);
		}
		if (c.getConfig().getString("Board.Waiting.Title") == null) c.getConfig().set("Board.Waiting.Title", "&6SkyWars &fPlus");
		if (c.getConfig().getString("Board.Waiting.1") == null) c.getConfig().set("Board.Waiting.1", "&5Kit: &9%k%"); {
			if (c.getConfig().getString("Board.Waiting.2") == null) c.getConfig().set("Board.Waiting.2", "&5Dinero: &9%m%");
			if (c.getConfig().getString("Board.Waiting.3") == null) c.getConfig().set("Board.Waiting.3", "&5En lobby: &9%wp%");
			if (c.getConfig().getString("Board.Waiting.4") == null) c.getConfig().set("Board.Waiting.4", "&5En Espera: &9%e%");
		}
		if (c.getConfig().getString("Board.Starting") == null) c.getConfig().set("Board.Starting", "&bEmpieza: &e%t%");
		c.saveConfig();
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (int n = 0; n < c.getConfig().getConfigurationSection("Board.Waiting").getKeys(false).size(); n++) {
			String title = c.getConfig().getString("Board.Waiting.Title");
			title = title.replace("&", "§");
			if (n == 0) {
				o.setDisplayName(title);
			} else {
				String cont = c.getConfig().getString("Board.Waiting." + n);
				cont = cont.replace("&", "§");
				cont = cont.replace("%m%", cp.getP(p.getName()).getString("Money"));
				cont = cont.replace("%wp%", String.valueOf(p.getWorld().getPlayers().size()));
				cont = cont.replace("%p%", p.getName());
				for (File f : ca.getfolder().listFiles()) {
					FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
					try {
						if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
							cont = cont.replace("%e%", String.valueOf(fc.getConfigurationSection("playing").getKeys(false).size()));
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
				score.setScore(c.getConfig().getConfigurationSection("Board.Waiting").getKeys(false).size()-n);
			}
		}
		SkyWars.plugin.c = true;
		SkyWars.plugin.board = bor;

	}
	
	public void update(Player p) {
		bor = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o;
		try {
			o = bor.registerNewObjective("sc", "dummy");
		} catch(IllegalArgumentException e) {
			o = bor.getObjective(DisplaySlot.SIDEBAR);
		}
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (int n = 0; n < c.getConfig().getConfigurationSection("Board.Waiting").getKeys(false).size(); n++) {
			String title = c.getConfig().getString("Board.Waiting.Title");
			title = title.replace("&", "§");
			if (n == 0) {
				o.setDisplayName(title);
			} else {
				String cont = c.getConfig().getString("Board.Waiting." + n);
				cont = cont.replace("&", "§");
				cont = cont.replace("%m%", cp.getP(p.getName()).getString("Money"));
				cont = cont.replace("%wp%", String.valueOf(p.getWorld().getPlayers().size()));
				cont = cont.replace("%p%", p.getName());
				for (File f : ca.getfolder().listFiles()) {
					FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
					try {
						if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
							cont = cont.replace("%e%", String.valueOf(fc.getConfigurationSection("playing").getKeys(false).size()));
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
				bor.getObjective(DisplaySlot.SIDEBAR).getScore(cont).setScore(c.getConfig().getConfigurationSection("Board.Waiting").getKeys(false).size()-n);
			}
		}
		for (File f : ca.getfolder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			try {
				if (fc.getString("playing." + p.getName()).equalsIgnoreCase("true")) {
					String name = f.getName().substring(0, f.getName().length()-4);
					if (i1.get(name) >= 0) {
						String cont = c.getConfig().getString("Board.Starting");
						cont = cont.replace("&", "§");
						cont = cont.replace("%t%", String.valueOf(i1.get(name)));
						if (cont.length() > 16) {
							cont = cont.substring(0, 16);
						}
						bor.getObjective(DisplaySlot.SIDEBAR).getScore(cont).setScore(c.getConfig().getConfigurationSection("Board.Waiting").getKeys(false).size()+1);
					}
				}
			} catch(NullPointerException e) { }
		}
		p.setScoreboard(bor);
		SkyWars.plugin.board = bor;
	}
	
	public void starting(int Int, String name) {
		i1.put(name, Int-1);
	}
}
