package me.Juanco.Events;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoin implements Listener {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private PlayerJoin() { }
	static PlayerJoin instance = new PlayerJoin();
	public static PlayerJoin getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!cp.getFile(e.getPlayer().getName()).exists()) {
			cp.create(e.getPlayer().getName());
			cp.getP(e.getPlayer().getName()).set("Money", "0");
			cp.save();
		}
	}
}
