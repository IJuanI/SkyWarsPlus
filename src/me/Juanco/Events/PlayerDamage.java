package me.Juanco.Events;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.Plugin;

public class PlayerDamage implements Listener {

	ConfigArenas ca = ConfigArenas.getInstance();
	private PlayerDamage() { }
	static PlayerDamage instance = new PlayerDamage();
	public static PlayerDamage getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			for (File f : ca.getfolder().listFiles()) {
				FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
				try {
					if (fc.getConfigurationSection("playing").getKeys(false).contains(p.getName())) {
						if (e.getCause().equals(DamageCause.FALL)) {
							if (fc.getString("Falldamage").equalsIgnoreCase("false")) {
								e.setCancelled(true);
							}
						}
					}
				} catch(NullPointerException ee) { }
			}
		}
	}
}
