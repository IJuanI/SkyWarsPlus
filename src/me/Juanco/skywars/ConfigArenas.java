package me.Juanco.skywars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigArenas {

	private ConfigArenas() { }
	static ConfigArenas instance = new ConfigArenas();
	public static ConfigArenas getInstance() {
		return instance;
	}
    FileConfiguration arena;
    File afile;
    
	public void CreateA(String id, Player p) {
		afile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator + id.toLowerCase() + ".yml");
		if (!SkyWars.plugin.getDataFolder().exists()) {
			SkyWars.plugin.getDataFolder().mkdir();
		}
		File af = new File(SkyWars.plugin.getDataFolder(), File.separator + "arenas" + File.separator);
		if (!af.exists()) {
			af.mkdirs();
		}
		try {
			afile.createNewFile();
		}
		catch (IOException e) {
			p.sendMessage(ChatColor.RED + "Fallo al crear " + ChatColor.AQUA + id.toLowerCase() + ChatColor.RED + ".yml!");
			return;
		}
		p.sendMessage(ChatColor.GREEN + "Creada exitosamente la arena " + id + "!");
	}
	
	public void DeleteA(String id) {
		File dfile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator + id.toLowerCase() + ".yml");
		dfile.delete();
	}
	
	public File getfolder() {
		afile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator);
		return afile;
	}
	
	public void loadA(String id) {
		afile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator + id.toLowerCase() + ".yml");
		arena = YamlConfiguration.loadConfiguration(afile);
	}
	
	public FileConfiguration getAr() {
		return arena;
	}
	
	public File getfile(String id) {
		afile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator + id.toLowerCase() + ".yml");
		return afile;
	}
	
	public FileConfiguration getA(String id) {
		afile = new File(SkyWars.plugin.getDataFolder(), "arenas" + File.separator + id.toLowerCase() + ".yml");
		arena = YamlConfiguration.loadConfiguration(afile);
		return arena;
	}
	
	public void saveA() {
        try {
            arena.save(afile);
        }
        catch (IOException e) {
        	Bukkit.getServer().getLogger().severe("No se ha podido guardar el archivo de una arena!");
        }
	}
}
