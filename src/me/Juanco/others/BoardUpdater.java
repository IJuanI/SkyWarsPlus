package me.Juanco.others;

import me.Juanco.Scoreboards.InGameScoreBoard;
import me.Juanco.Scoreboards.WaitingScoreboard;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BoardUpdater extends BukkitRunnable {

	ConfigArenas ca = ConfigArenas.getInstance();
	public SkyWars plugin;
	public BoardUpdater(SkyWars plugin) {
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	public void run() {
		if (SkyWars.plugin.c == true) {
			try {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if (p.getScoreboard() == SkyWars.plugin.board) {
						WaitingScoreboard.getInstance().update(p);
					}
					if (p.getScoreboard() == SkyWars.plugin.board1) {
						InGameScoreBoard.getInstance().update(p);
					}
				}
			} catch(NullPointerException e) { }	
		}
	}
}