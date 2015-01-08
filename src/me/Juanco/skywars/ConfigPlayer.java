package me.Juanco.skywars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigPlayer {

	private ConfigPlayer() { }
	static ConfigPlayer instance = new ConfigPlayer();
	public static ConfigPlayer getInstance() {
		return instance;
	}
	
	FileConfiguration player;
	File pfile;
	
	public void create(String id) {
		pfile = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator + id + ".yml");
		if (!SkyWars.plugin.getDataFolder().exists()) {
			SkyWars.plugin.getDataFolder().mkdir();
		}
		File pf = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator);
		if (!pf.exists()) {
			pf.mkdirs();
		}
		if (!pfile.exists()) {
			try {
				pfile.createNewFile();	
			} catch(IOException e) {
				Bukkit.getLogger().severe("No se ha podido crear " + id + ".yml!");
			}
		}
	}
	
	public File folder() {
		File pf = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator);
		return pf;
	}
	
	public FileConfiguration getP(String id) {
		pfile = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator + id + ".yml");
		player = YamlConfiguration.loadConfiguration(pfile);
		return player;
	}
	
	public void load(String id) {
		pfile = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator + id + ".yml");
		player = YamlConfiguration.loadConfiguration(pfile);
	}
	
	public FileConfiguration get() {
		return player;
	}
	
	public void save() {
		try {
			player.save(pfile);
		} catch(IOException e) {
			Bukkit.getLogger().severe("No se ha podido guardar el archivo de un jugador!");
		}
	}
	
	public File getFile(String id) {
		pfile = new File(SkyWars.plugin.getDataFolder(), "player data" + File.separator + id + ".yml");
		return pfile;
	}
}
