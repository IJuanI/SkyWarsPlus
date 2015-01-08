package me.Juanco.others;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RestoreItems {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private RestoreItems() { }
	static RestoreItems instance = new RestoreItems();
	public static RestoreItems getInstance() {
		return instance;
	}
	
	public void ri(Player p) {
		String Gamemode = cp.getP(p.getName()).getString("Temp.GameMode");
		int Level = cp.getP(p.getName()).getInt("Temp.Level");
		p.getInventory().clear();
		try {
			for (String s : cp.get().getConfigurationSection("Temp.Items").getKeys(false)) {
				ItemStack i = cp.get().getItemStack("Temp.Items." + s);
				p.getInventory().setItem(Integer.parseInt(s), i);
			}	
		} catch(NullPointerException e) { }
		try { ItemStack i = cp.get().getItemStack("Temp.Armor.Helmet");
		p.getEquipment().setHelmet(i); } catch(NullPointerException e) { }
		try { ItemStack i = cp.get().getItemStack("Temp.Armor.Chestplate");
		p.getEquipment().setChestplate(i); } catch(NullPointerException e) { }
		try { ItemStack i = cp.get().getItemStack("Temp.Armor.Leggings");
		p.getEquipment().setLeggings(i); } catch(NullPointerException e) { }
		try { ItemStack i = cp.get().getItemStack("Temp.Armor.Boots");
		p.getEquipment().setBoots(i); } catch(NullPointerException e) { }
		World w = Bukkit.getServer().getWorld(cp.get().getString("Temp.Location.world"));
		double x = cp.get().getDouble("Temp.Location.x");
		double y = cp.get().getDouble("Temp.Location.y");
		double z = cp.get().getDouble("Temp.Location.z");
		float yaw = cp.get().getInt("Temp.Location.yaw");
		float pitch = cp.get().getInt("Temp.Location.pitch");
		p.setMaxHealth(20.0);
		try {
			p.setHealth(cp.get().getDouble("Temp.Health"));	
		} catch(IllegalArgumentException e) {
			Damageable pd = p;
			p.setHealth(pd.getMaxHealth());
		}
		p.setFoodLevel(cp.get().getInt("Temp.Food-Level"));
		Location l = new Location(w, x, y, z, yaw, pitch);
		p.teleport(l);
		p.setGameMode(GameMode.valueOf(Gamemode));
		p.setLevel(Level);
		cp.get().set("Temp", null);
		cp.save();
	}
}
