package me.Juanco.Events;

import java.io.File;

import me.Juanco.Events.Menus.Comprar;
import me.Juanco.Events.Menus.Contenidos;
import me.Juanco.Events.Menus.Elegir;
import me.Juanco.Events.Menus.Opciones;
import me.Juanco.skywars.ConfigKits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ItemsHandler implements Listener {

	ConfigKits ck = ConfigKits.getInstance();
	private ItemsHandler() { }
	static ItemsHandler instance = new ItemsHandler();
	public static ItemsHandler getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	private void openSelector(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§b§oElije un kit!");
		for (File f : ck.folder().listFiles()) {
			FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
			int slot = fc.getInt("Slot");
			String name = fc.getString("Name");
			Material id = Material.getMaterial(fc.getString("ID"));
			name = name.replaceAll("&", "§");
			ItemStack i = new ItemStack(id, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(name);
			i.setItemMeta(im);
			inv.setItem(slot-1, i);
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			try {
				if (e.getItem().getItemMeta().getDisplayName().contains("Kits")) {
					e.setCancelled(true);
					openSelector(e.getPlayer());
				}	
			} catch(NullPointerException ee) { }
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("Elije un kit!")) {
			try {
				if (!e.getCurrentItem().getType().toString().equalsIgnoreCase("air")) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					for (File f : ck.folder().listFiles()) {
						FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
						String name = fc.getString("Name");
						name = name.replace("&", "§");
						String ID = fc.getString("ID");
						if (e.getCurrentItem().getType().equals(Material.getMaterial(ID)) && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
							if (e.getAction().equals(InventoryAction.PICKUP_ALL)) {
								Elegir.getInstance().elegir(p, f.getName().substring(0, f.getName().length()-4), fc);
							} else {
								Opciones.getInstance().opciones(p, fc, f);
							}
						}
					}
				}
			} catch(NullPointerException ee) { }
		}
		if (e.getInventory().getName().contains("Opciones")) {
			try {
				if (!e.getCurrentItem().getType().toString().equalsIgnoreCase("air")) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					for (File f : ck.folder().listFiles()) {
						FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
						String name = f.getName().substring(0, f.getName().length()-4);
						Boolean b = fc.getString("Name").replaceAll("&", "§").equalsIgnoreCase(e.getInventory().getName().substring(13, e.getInventory().getName().length()-5));
						String str = e.getCurrentItem().getItemMeta().getDisplayName();
						if (str.equalsIgnoreCase("§bVer contenidos") && b) {
							Contenidos.getInstance().contenidos(p, fc);
						}
						if (str.equalsIgnoreCase("§5§oVolver") && b) {
							p.closeInventory();
							openSelector(p);
						}
						if (str.equalsIgnoreCase("§6Comprar Kit") && b) {
							Comprar.getInstance().comprar(p, fc, f);
						}
						if (str.equalsIgnoreCase("§aElegir Kit") && b) {
							Elegir.getInstance().elegir(p, name, fc);
						}
					}
				}
			} catch(NullPointerException ee) { }
		}
		if (e.getInventory().getName().contains("Contenidos ")) {
			try {
				if (!e.getCurrentItem().getType().toString().equalsIgnoreCase("air")) {
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					for (File f : ck.folder().listFiles()) {
						FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
						if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5§oVolver") && fc.getString("Name").replaceAll("&", "§").equalsIgnoreCase(e.getInventory().getName().substring(15, e.getInventory().getName().length()-5))) {
							Opciones.getInstance().opciones(p, fc, f);
						}
					}
				}
			} catch(NullPointerException ee) { }
		}
	}
}
