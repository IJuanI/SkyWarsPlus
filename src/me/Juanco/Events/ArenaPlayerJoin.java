package me.Juanco.Events;

import java.io.File;
import java.util.Random;
import java.util.Set;

import me.Juanco.Scoreboards.WaitingScoreboard;
import me.Juanco.others.GiveKit;
import me.Juanco.others.ItemsGiver;
import me.Juanco.skywars.ConfigArenas;
import me.Juanco.skywars.ConfigPlayer;
import me.Juanco.skywars.SkyWars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaPlayerJoin {
	
	ConfigPlayer cp = ConfigPlayer.getInstance();
	ConfigArenas ca = ConfigArenas.getInstance();
	private ArenaPlayerJoin() { }
	static ArenaPlayerJoin instance = new ArenaPlayerJoin();
	public static ArenaPlayerJoin getInstance() {
		return instance;
	}
	
	public void pjc(Player p, String[] args) {
		if (args.length == 1) {
			p.sendMessage(ChatColor.RED + "Elije una arena!");
			return;
		}
		try {
			boolean b = true;
			for (File f : ca.getfolder().listFiles()) {
				if (f.getName().substring(0, f.getName().length()-4).equalsIgnoreCase(args[1])) {
					b = false;
				}
			}
			if (b == true) {
				p.sendMessage(ChatColor.RED + "Esa arena no existe!");
				return;
			}
		} catch(NullPointerException e) {
			p.sendMessage(ChatColor.RED + "No hay arenas!");
			return;
		}
		if (ca.getA(args[1]).getString("waitroom.world") == null) {
			p.sendMessage(ChatColor.RED + "Esa arena no tiene waitroom!");
			return;
		}
		if (ca.getA(args[1]).getString("specspawn.world") == null) {
			p.sendMessage(ChatColor.RED + "Esa arena no tiene specspawn!");
			return;
		}
		if (ca.getA(args[1]).getString("loselevel") == null) {
			p.sendMessage(ChatColor.RED + "Esa arena no tiene loselevel!");
			return;
		}
		if (ca.getA(args[1]).getString("Size") == null) {
			p.sendMessage(ChatColor.RED + "Esa arena no tiene Size!");
			return;
		}
		int size = Integer.parseInt(ca.getAr().getString("Size"));
		for (int n = 1;n <= size;n++) {
			if (ca.getA(args[1]).getString("spawns." + n + ".pre") == null) {
				p.sendMessage(ChatColor.RED + "Esa arena no tiene configurados los prespawns!");
				return;
			}
			if (ca.getA(args[1]).getString("spawns." + n + ".1") == null) {
				p.sendMessage(ChatColor.RED + "Esa arena no tiene bien configurados los spawns!");
				return;
			}
		}
		try {
			if (ca.getAr().getString("playing." + p.getName()).equalsIgnoreCase("true")) {
				p.sendMessage(ChatColor.RED + "Ya estas en la lista de espera!");
				return;
			}
		} catch(NullPointerException e) {
			
		}
		try {
			if (ca.getAr().getConfigurationSection("playing").getKeys(false).size() >= size) {
				p.sendMessage(ChatColor.RED + "Esa arena esta llena!");
				return;
			}
		} catch(NullPointerException e) {
			
		}
		World w = Bukkit.getWorld(ca.getA(args[1]).getString("waitroom.world"));
		double x = ca.getAr().getDouble("waitroom.x");
		double y = ca.getAr().getDouble("waitroom.y");
		double z = ca.getAr().getDouble("waitroom.z");
		float yaw = ca.getAr().getInt("waitroom.yaw");
		float pitch = ca.getAr().getInt("waitroom.pitch");
		Location l = new Location(w, x, y, z, yaw, pitch);
		cp.getP(p.getName()).set("Temp.GameMode", p.getGameMode().toString());
		cp.get().set("Temp.Level", p.getLevel());
		int n = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i != null) {
				cp.get().set("Temp.Items." + n, i);
			}
			n++;
		}
		ItemStack i = p.getEquipment().getHelmet();
		if (i != null) {
			cp.get().set("Temp.Armor.Helmet", i);
		}
		ItemStack i1 = p.getEquipment().getChestplate();
		if (i1 != null) {
			cp.get().set("Temp.Armor.Chestplate", i1);
		}
		ItemStack i2 = p.getEquipment().getLeggings();
		if (i2 != null) {
			cp.get().set("Temp.Armor.Leggings", i2);
		}
		ItemStack i3 = p.getEquipment().getBoots();
		if (i3 != null) {
			cp.get().set("Temp.Armor.Boots", i3);
		}
		cp.get().set("Temp.Food-Level", p.getFoodLevel());
		Damageable pd = p;
		cp.get().set("Temp.Health", pd.getHealth());
		cp.get().set("Temp.Location.world", p.getLocation().getWorld().getName());
		cp.get().set("Temp.Location.x", p.getLocation().getX());
		cp.get().set("Temp.Location.y", p.getLocation().getY());
		cp.get().set("Temp.Location.z", p.getLocation().getZ());
		cp.get().set("Temp.Location.yaw", p.getLocation().getYaw());
		cp.get().set("Temp.Location.pitch", p.getLocation().getPitch());
		cp.save();
		WaitingScoreboard.getInstance().display(p);
		p.setScoreboard(SkyWars.plugin.board);
		p.setGameMode(GameMode.SURVIVAL);
		p.setLevel(30);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.updateInventory();
		p.teleport(l);
		pd.setHealth(pd.getMaxHealth());
		p.setFoodLevel(20);
		final String ar = ca.getfile(args[1]).getName().substring(0, ca.getfile(args[1]).getName().length()-4);
		ca.getA(ar).set("playing." + p.getName(), "true");
		ca.saveA();
		p.sendMessage(ChatColor.GREEN + "Añadido a la lista de espera de la arena " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "!");
		p.setMaxHealth(20.0);
		p.setHealth(20.0);
		ItemsGiver.getInstance().selector(p);
		if (ca.getAr().getConfigurationSection("playing").getKeys(false).size() == 1) {
			SkyWars.plugin.ArenaState.put(ar, "Waiting");
		}
		if (ca.getAr().getConfigurationSection("playing").getKeys(false).size() == 2) {
			SkyWars.plugin.countdown.put(ar, 29);
			SkyWars.plugin.ArenaState.put(ar, "Starting");
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.plugin, new Runnable() {
				public void run() {
					if (SkyWars.plugin.ArenaState.get(ar).equalsIgnoreCase("Starting")) {
						int n = 1;
						for (String pls : ca.getAr().getConfigurationSection("playing").getKeys(false)) {
							Player pl = Bukkit.getPlayer(pls);
							World w = Bukkit.getWorld(ca.getAr().getString("spawns." + n + ".pre.world"));
							double x = ca.getAr().getDouble("spawns." + n + ".pre.x");
							double y = ca.getAr().getDouble("spawns." + n + ".pre.y");
							double z = ca.getAr().getDouble("spawns." + n + ".pre.z");
							float yaw = ca.getAr().getInt("spawns." + n + ".pre.yaw");
							float pitch = ca.getAr().getInt("spawns." + n + ".pre.pitch");
							Location l = new Location(w, x, y, z, yaw, pitch);
							l.setY(l.getY()-1); l.getBlock().setType(Material.GLASS);
							l.setY(l.getY()+1);
							try {
								pl.teleport(l);
								SkyWars.plugin.denymove.add(pl);
								pl.sendMessage(ChatColor.YELLOW + "Seras liberado en 5 segundos!");
								if (SkyWars.plugin.usedkit.containsKey(pl)) GiveKit.getInstance().gk(pl, SkyWars.plugin.usedkit.get(pl));
							} catch(NullPointerException e) { }
							n++;
						}
						SkyWars.plugin.ArenaState.put(ar, "playing");
					}
				}
			}, 20*30);
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.plugin, new Runnable() {
				public void run() {
					if (SkyWars.plugin.ArenaState.get(ar).equalsIgnoreCase("playing")) {
						int n = 1;
						for (String pls : ca.getAr().getConfigurationSection("playing").getKeys(false)) {
							Player pl = Bukkit.getPlayer(pls);
							Location block = pl.getLocation();
							block.setY(block.getY()-1);
							block.getBlock().setType(Material.AIR);
							Set<String> b = ca.getAr().getConfigurationSection("spawns." + n).getKeys(false);
							Random rand = new Random();
							int r = rand.nextInt(b.size()-1);
							r += 1;
							String s = "";
							for (int t = 0; t < r; t++) {
								s = b.iterator().next();
								b.remove(s);
							}
							World w = Bukkit.getWorld(ca.getAr().getString("spawns." + n + "." + s + ".world"));
							double x = ca.getAr().getDouble("spawns." + n + "." + s + ".x");
							double y = ca.getAr().getDouble("spawns." + n + "." + s + ".y");
							double z = ca.getAr().getDouble("spawns." + n + "." + s + ".z");
							float yaw = ca.getAr().getInt("spawns." + n + "." + s + ".yaw");
							float pitch = ca.getAr().getInt("spawns." + n + "." + s + ".pitch");
							Location l1 = new Location(w, x, y, z, yaw, pitch);
							try {
								SkyWars.plugin.denymove.remove(pl);
								pl.teleport(l1);
								pl.sendMessage(ChatColor.GREEN + "Has sido liberado! " + ChatColor.BLUE + "Buena suerte!");
								ArenaStart.getInstance().start(pl);
							} catch(NullPointerException e) { }
							n++;
						}
					}
				}
			}, 20*35);
		}
		if (SkyWars.plugin.ArenaState.get(ar).equalsIgnoreCase("Waiting")) {
			p.sendMessage(ChatColor.YELLOW + "Esperando mas jugadores...");
		}
		return;
	}
}