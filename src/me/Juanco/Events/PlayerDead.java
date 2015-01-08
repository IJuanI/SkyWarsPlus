package me.Juanco.Events;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDead implements Listener {

	ConfigArenas ca = ConfigArenas.getInstance();
	private PlayerDead() { }
	static PlayerDead instance = new PlayerDead();
	public static PlayerDead getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		for (File f : ca.getfolder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			try {
				if (fc.getString("playing." + e.getEntity().getName()).equalsIgnoreCase("true")) {
					String s = f.getName().substring(0, f.getName().length()-4);
					ArenaPlayerLose.getInstance().lose(e.getEntity(), s);
				}
			} catch(NullPointerException ee) {
				
			}
		}
	}
}
