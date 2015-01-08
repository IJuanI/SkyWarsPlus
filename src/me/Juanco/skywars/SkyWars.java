package me.Juanco.skywars;

import java.util.ArrayList;
import java.util.HashMap;

import me.Juanco.Commands.Commands;
import me.Juanco.Events.FoodLevelChange;
import me.Juanco.Events.ItemsHandler;
import me.Juanco.Events.KitsInvseeHandlers;
import me.Juanco.Events.PlayerDamage;
import me.Juanco.Events.PlayerJoin;
import me.Juanco.Events.PlayerMove;
import me.Juanco.others.BoardUpdater;
import me.Juanco.others.Countdown;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

public final class SkyWars extends JavaPlugin {

	public static SkyWars plugin;
	public boolean c;
	public Countdown cd;
	public Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
	public Scoreboard board1;
	public HashMap<String, Integer> countdown;
	public HashMap<String, String> ArenaState = new HashMap<String, String>();
	public HashMap<Player, String> usedkit = new HashMap<Player, String>();
	public ArrayList<Player> denymove = new ArrayList<Player>();
	
	@SuppressWarnings("unused")
	public void onEnable() {
		plugin = this;
		BukkitTask Countdown = new Countdown(this).runTaskTimer(this, 20, 20);
		BukkitTask BoardUpdater = new BoardUpdater(this).runTaskTimer(this, 20, 20);
		countdown = new HashMap<String, Integer>();
		KitsInvseeHandlers.getInstance().register(this);
		ItemsHandler.getInstance().register(this);
		PlayerDamage.getInstance().register(this);
		PlayerJoin.getInstance().register(this);
		PlayerMove.getInstance().register(this);
		FoodLevelChange.getInstance().register(this);
		Config.getInstance().Setup();
	}
	
	public void onDisable() {
		plugin = null;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		Commands.getInstance().onCommand(sender, cmd, CommandLabel, args);
		return true;
	}
}