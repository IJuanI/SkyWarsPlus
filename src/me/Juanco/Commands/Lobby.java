package me.Juanco.Commands;

import me.Juanco.skywars.Config;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Lobby {

	Config settings = Config.getInstance();
	private Lobby() { }
	static Lobby instance = new Lobby();
	public static Lobby getInstance() {
		return instance;
	}
	
	public void lobby(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.lobby")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		settings.getConfig().set("lobby.world", p.getWorld().getName());
		settings.getConfig().set("lobby.x", p.getLocation().getX());
		settings.getConfig().set("lobby.y", p.getLocation().getY());
		settings.getConfig().set("lobby.z", p.getLocation().getZ());
		settings.getConfig().set("lobby.yaw", p.getLocation().getYaw());
		settings.getConfig().set("lobby.pitch", p.getLocation().getPitch());
		settings.saveConfig();
		p.sendMessage(ChatColor.GREEN + "Establecido exitosamente el lobby!");
	}
}
