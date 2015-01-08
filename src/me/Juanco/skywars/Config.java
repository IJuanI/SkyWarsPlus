package me.Juanco.skywars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private Config() { }
	static Config instance = new Config();
	public static Config getInstance() {
		return instance;
	}
	
	FileConfiguration config;
	File cfile;
	
	public void Setup() {
		cfile = new File(SkyWars.plugin.getDataFolder(), "config.yml");
		if (!cfile.exists()) {
			try {
				cfile.createNewFile();	
			} catch(IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "No se ha podido crear config.yml!");
				return;
			}
		}
		if (!SkyWars.plugin.getDataFolder().exists()) {
			SkyWars.plugin.getDataFolder().mkdir();
		}
		config = YamlConfiguration.loadConfiguration(cfile);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void saveConfig() {
		try {
			config.save(cfile);
		} catch(IOException e) {
			Bukkit.getLogger().severe(ChatColor.RED + "No se ha podido guardar config.yml!");
			return;
		}
	}
}
