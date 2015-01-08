package me.Juanco.Events;

import java.io.File;

import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.ConfigKits;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.Plugin;

public class FoodLevelChange implements Listener {

	ConfigKits ck = ConfigKits.getInstance();
	ConfigArenas ca = ConfigArenas.getInstance();
	private FoodLevelChange() { }
	static FoodLevelChange instance = new FoodLevelChange();
	public static FoodLevelChange getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		for (File f : ca.getfolder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			try {
				if (fc.getConfigurationSection("playing").getKeys(false).contains(p.getName())) {
					String s = f.getName().substring(0, f.getName().length()-4);
					if (SkyWars.plugin.ArenaState.get(s).equalsIgnoreCase("Starting") || SkyWars.plugin.ArenaState.get(s).equalsIgnoreCase("Waiting")) {
						e.setCancelled(true);
					}
					if (SkyWars.plugin.ArenaState.get(s).equalsIgnoreCase("playing")) {
						if (SkyWars.plugin.usedkit.containsKey(p)) {
							if (ck.getF(SkyWars.plugin.usedkit.get(p)).getString("Options.no-hunger").equalsIgnoreCase("true")) {
								e.setCancelled(true);
							}
						}
					}
				}
			} catch(NullPointerException ee) { }
		}
	}
}
