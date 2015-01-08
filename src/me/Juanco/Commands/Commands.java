package me.Juanco.Commands;

import me.Juanco.Events.ArenaPlayerJoin;
import me.Juanco.Events.ArenaPlayerLeave;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands {

	private Commands() { }
	static Commands instance = new Commands();
	public static Commands getInstance() {
		return instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "No puedes hacer eso!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("SkyWars")) {
			if (args.length == 0) {
				Help.getInstance().help(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("2")) {
				Help.getInstance().help2(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("help")) {
				if (args.length == 1) {
					Help.getInstance().help(p);
					return true;	
				}
				if (args[1].equalsIgnoreCase("2")) {
					Help.getInstance().help2(p);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("info")) {
				Info.getInstance().info(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("admin")) {
				if (args.length == 1) {
					AdminHelp.getInstance().adminhelp(p);
					return true;	
				}
				if (args[1].equalsIgnoreCase("2")) {
					AdminHelp.getInstance().adminhelp2(p);
					return true;
				}
				if (args[1].equalsIgnoreCase("3")) {
					AdminHelp.getInstance().adminhelp3(p);
					return true;
				}
				if (args[1].equalsIgnoreCase("balance")) {
					AdminBalance.getInstance().balance(p, args);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("create")) {
				Create.getInstance().create(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("delete")) {
				Delete.getInstance().delete(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("size")) {
				Size.getInstance().size(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("spawn")) {
				Spawn.getInstance().spawn(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("lobby")) {
				Lobby.getInstance().lobby(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("prespawn")) {
				Prespawn.getInstance().prespawn(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("waitroom")) {
				Waitroom.getInstance().waitroom(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("setspecspawn")) {
				Setspecspawn.getInstance().setspecspawn(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("loselevel")) {
				Loselevel.getInstance().loselevel(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("kits")) {
				CommandKits.getInstance().kits(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("falldamage")) {
				Falldamage.getInstance().falldamage(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("balance")) {
				Balance.getInstance().balance(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("selector")) {
				Selector.getInstance().selector(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("join")) {
				ArenaPlayerJoin.getInstance().pjc(p, args);
				return true;
			}
			if (args[0].equalsIgnoreCase("leave")) {
				ArenaPlayerLeave.getInstance().leavec(p);
				return true;
			}
			p.sendMessage("Comando Desconocido!");
		}
		return true;
	}
}
