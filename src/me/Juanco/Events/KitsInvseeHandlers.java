package me.Juanco.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class KitsInvseeHandlers implements Listener {

	private KitsInvseeHandlers() { }
	static KitsInvseeHandlers instance = new KitsInvseeHandlers();
	public static KitsInvseeHandlers getInstance() {
		return instance;
	}
	
	public void register(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("Kit:")) {
			try {
				if (e.getCurrentItem() != null) {
					e.setCancelled(true);
				}	
			} catch(NullPointerException ee) {
				
			}
		}
	}
}
