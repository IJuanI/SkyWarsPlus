package me.Juanco.Events;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class PlayerMove implements Listener {

	ConfigArenas ca = ConfigArenas.getInstance();
	private PlayerMove() { }
	static PlayerMove instance = new PlayerMove();
	public static PlayerMove getInstance() {
		return instance;
	}
	
	public void register(Plugin pl) {
		Bukkit.getPluginManager().registerEvents(this, pl);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (SkyWars.plugin.denymove.contains(e.getPlayer())) {
			if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				e.getPlayer().teleport(e.getFrom());
			}
		}
		Player p = e.getPlayer();
		for (File f : ca.getfolder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			try {
				if (fc.getConfigurationSection("playing").getKeys(false).contains(p.getName())) {
					String s = f.getName().substring(0, f.getName().length()-4);
					if (SkyWars.plugin.ArenaState.get(s).equalsIgnoreCase("playing")) {
						if (p.getLocation().getBlockY() < fc.getInt("loselevel")) {
							ArenaPlayerLose.getInstance().lose(p, s);
						}	
					}
				}
			} catch(NullPointerException ee) { }
		}
	}
}
