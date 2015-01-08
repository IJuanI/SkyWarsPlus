package me.Juanco.skywars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigKits {

	private ConfigKits() { }
	static ConfigKits instance = new ConfigKits();
	public static ConfigKits getInstance() {
		return instance;
	}
	
	FileConfiguration kits;
	File ffile;
	
	public void create(String id) {
		ffile = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator + id + ".yml");
		if (!SkyWars.plugin.getDataFolder().exists()) {
			SkyWars.plugin.getDataFolder().mkdir();	
		}
		File folder = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		if (!ffile.exists()) {
			try {
				ffile.createNewFile();
			} catch(IOException e) {
				Bukkit.getLogger().severe("Error al crear " + id + ".yml!");
			}
		}
	}
	
	public FileConfiguration get() {
		return kits;
	}
	
	public File folder() {
		File folder = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator);
		return folder;
	}
	
	public File getfile(String id) {
		ffile = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator + id + ".yml");
		kits = YamlConfiguration.loadConfiguration(ffile);
		return ffile;
	}
	
	public void load(String id) {
		ffile = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator + id + ".yml");
		kits = YamlConfiguration.loadConfiguration(ffile);
	}
	
	public FileConfiguration getF(String id) {
		ffile = new File(SkyWars.plugin.getDataFolder(), "kits" + File.separator + id + ".yml");
		kits = YamlConfiguration.loadConfiguration(ffile);
		return kits;
	}
	
	public void save() {
		try {
			kits.save(ffile);
		} catch(IOException e) {
			Bukkit.getLogger().severe("Error al guardar " + ffile.getName() + "!");
		}
	}
}
