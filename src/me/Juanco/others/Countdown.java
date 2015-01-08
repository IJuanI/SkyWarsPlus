package me.Juanco.others;

import java.io.File;

import me.Juanco.Scoreboards.WaitingScoreboard;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

	ConfigArenas ca = ConfigArenas.getInstance();
	public SkyWars plugin;
	public Countdown(SkyWars plugin) {
		this.plugin = plugin;
	}
	public void run() {
		try {
			for(File f : ca.getfolder().listFiles()) {
				try {
					
				} catch(NullPointerException e) { }
				FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
				String name = f.getName().substring(0, f.getName().length()-4);
				try {
					for (String s : fc.getConfigurationSection("playing").getKeys(false)) {
						Player p = Bukkit.getPlayer(s);
						if (plugin.countdown.get(name) >= 0) {
							p.setLevel(plugin.countdown.get(name));	
						}
						if (plugin.countdown.get(name) == 25) {
							p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 0);
						}
						if (plugin.countdown.get(name) == 20) {
							p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 0);
						}
						if (plugin.countdown.get(name) == 15) {
							p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 1);
							p.sendMessage(ChatColor.AQUA + "15 segundos!");
						}
						if (plugin.countdown.get(name) == 10) {
							p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 2);
							p.sendMessage(ChatColor.AQUA + "10 segundos!");
						}
						if (plugin.countdown.get(name) == 5) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 0);
							p.sendMessage(ChatColor.AQUA + "5 segundos!");
						}
						if (plugin.countdown.get(name) == 4) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 0);
						}
						if (plugin.countdown.get(name) == 3) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 1);
						}
						if (plugin.countdown.get(name) == 2) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 1);
						}
						if (plugin.countdown.get(name) == 1) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 2);
						}
						if (plugin.countdown.get(name) == 0) {
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 0);
							p.sendMessage(ChatColor.GREEN + "La arena ha empezado!");
						}
					}	
				} catch(NullPointerException e) { }
				try {
					WaitingScoreboard.getInstance().starting(plugin.countdown.get(name), name);	
				} catch(NullPointerException e) { }
				try {
					plugin.countdown.put(name, plugin.countdown.get(name)-1);
				} catch(NullPointerException e) { }
			}
		} catch(NullPointerException e) { }
	}
}