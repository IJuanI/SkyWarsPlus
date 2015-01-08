package me.Juanco.Events.Menus;

import java.io.File;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Comprar {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private Comprar() { }
	static Comprar instance = new Comprar();
	public static Comprar getInstance() {
		return instance;
	}
	
	public void comprar(Player p, FileConfiguration fc, File f) {
		Boolean cost = true;
		if (fc.getString("Cost") == null) {
			cost = false;
		}
		if (cost == false) {
			cp.getP(p.getName()).set("Kits." + f.getName().substring(0, f.getName().length()-4), "Adquirido");
			cp.save();
			Opciones.getInstance().opciones(p, fc, f);
			p.sendMessage(ChatColor.GREEN + "Item Adquirido!");
		} else {
			int m = Integer.parseInt(cp.getP(p.getName()).getString("Money"));
			if (m < fc.getInt("Cost")) {
				p.sendMessage(ChatColor.RED + "No tienes suficiente dinero!");
				return;
			} else {
				m -= fc.getInt("Cost");
				cp.getP(p.getName()).set("Kits." + f.getName().substring(0, f.getName().length()-4), "Adquirido");
				cp.get().set("Money", m);
				cp.save();
				Opciones.getInstance().opciones(p, fc, f);
				p.sendMessage(ChatColor.GREEN + "Item Adquirido!");
			}
		}
	}
}
