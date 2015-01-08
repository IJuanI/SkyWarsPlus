package me.Juanco.Events;

import me.Juanco.Scoreboards.InGameScoreBoard;

import org.bukkit.entity.Player;

public class ArenaStart {

	private ArenaStart() { }
	static ArenaStart instance = new ArenaStart();
	public static ArenaStart getInstance() {
		return instance;
	}
	
	public void start(Player p) {
		InGameScoreBoard.getInstance().display(p);
	}
}
