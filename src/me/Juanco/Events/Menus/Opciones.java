package me.Juanco.Events.Menus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.Juanco.skywars.ConfigPlayer;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Opciones {

	ConfigPlayer cp = ConfigPlayer.getInstance();
	private Opciones() { }
	static Opciones instance = new Opciones();
	public static Opciones getInstance() {
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public void opciones(Player p, FileConfiguration fc, File f) {
		String name = fc.getString("Name");
		name = name.replace("&", "§");
		String na = "§b§oOpciones " + name + "§b§o:";
		Inventory inv = Bukkit.createInventory(null, 18, na);
		
		ItemStack i1 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		ItemMeta im1 = i1.getItemMeta();
		im1.setDisplayName("§5§oVolver");
		i1.setItemMeta(im1);
		inv.setItem(8, i1);
		
		ItemStack i2 = new ItemStack(Material.GOLD_BLOCK, 1);
		ItemMeta im2 = i2.getItemMeta();
		im2.setDisplayName("§bVer contenidos");
		i2.setItemMeta(im2);
		inv.setItem(17, i2);
		
		ItemStack i3;
		if (fc.getString("Options.unarmed-damage") == null) {
			i3 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im3 = i3.getItemMeta();
			im3.setDisplayName("§eDaño sin armas: §cDesactivado");
			i3.setItemMeta(im3);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.unarmed-damage"));
			i3 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im3 = i3.getItemMeta();
			im3.setDisplayName("§eDaño sin armas: §a" + b);
			i3.setItemMeta(im3);
		}
		inv.setItem(9, i3);
		
		ItemStack i4;
		if (fc.getString("Options.extra-damage") == null) {
			i4 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im4 = i4.getItemMeta();
			im4.setDisplayName("§eDaño Extra: §cDesactivado");
			i4.setItemMeta(im4);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.extra-damage"));
			i4 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im4 = i4.getItemMeta();
			im4.setDisplayName("§eDaño Extra: §a" + b);
			i4.setItemMeta(im4);
		}
		inv.setItem(10, i4);
		
		ItemStack i5;
		if (fc.getString("Options.effect.name") == null) {
			i5 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im5 = i5.getItemMeta();
			im5.setDisplayName("§eEfecto: §cDesactivado");
			i5.setItemMeta(im5);
		} else {
			i5 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.LIME.getData());
			ItemMeta im5 = i5.getItemMeta();
			im5.setDisplayName("§eEffecto: §a" + fc.getString("Options.effect.name"));
			List<String> l = new ArrayList<String>();
			l.add("§6§oNivel: §a" + fc.getString("Options.effect.level"));
			im5.setLore(l);
			i5.setItemMeta(im5);
		}
		inv.setItem(11, i5);
		
		ItemStack i6;
		if (fc.getString("Options.jump-boost") == null) {
			i6 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im6 = i6.getItemMeta();
			im6.setDisplayName("§eSalto: §cDesactivado");
			i6.setItemMeta(im6);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.jump-boost"));
			i6 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im6 = i6.getItemMeta();
			im6.setDisplayName("§eSalto: §a" + b);
			i6.setItemMeta(im6);
		}
		inv.setItem(12, i6);
		
		ItemStack i7;
		if (fc.getString("Options.speed") == null) {
			i7 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im7 = i7.getItemMeta();
			im7.setDisplayName("§eVelocidad: §cDesactivado");
			i7.setItemMeta(im7);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.speed"));
			i7 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im7 = i7.getItemMeta();
			im7.setDisplayName("§eVelocidad: §a" + b);
			i7.setItemMeta(im7);
		}
		inv.setItem(13, i7);
		
		ItemStack i8;
		if (fc.getString("Options.no-hunger") == null) {
			i8 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im8 = i8.getItemMeta();
			im8.setDisplayName("§eAnti hambre: §cDesactivado");
			i8.setItemMeta(im8);
		} else {
			i8 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.LIME.getData());
			ItemMeta im8 = i8.getItemMeta();
			im8.setDisplayName("§eAnti hambre: §aActivado");
			i8.setItemMeta(im8);
		}
		inv.setItem(14, i8);
		
		ItemStack i9;
		if (fc.getString("Options.extra-hearts") == null) {
			i9 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im9 = i9.getItemMeta();
			im9.setDisplayName("§eCorazones extra: §cDesactivado");
			i9.setItemMeta(im9);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.extra-hearts"));
			i9 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im9 = i9.getItemMeta();
			im9.setDisplayName("§eCorazones Extra: §a" + b);
			i9.setItemMeta(im9);
		}
		inv.setItem(15, i9);
		
		ItemStack i10;
		if (fc.getString("Options.nlifes") == null) {
			i10 = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.RED.getData());
			ItemMeta im10 = i10.getItemMeta();
			im10.setDisplayName("§eVidas extra: §cDesactivado");
			i10.setItemMeta(im10);
		} else {
			Double b = Double.parseDouble(fc.getString("Options.nlifes"));
			i10 = new ItemStack(Material.STAINED_CLAY, b.intValue(), DyeColor.LIME.getData());
			ItemMeta im10 = i10.getItemMeta();
			im10.setDisplayName("§eVidas Extra: §a" + b);
			i10.setItemMeta(im10);
		}
		inv.setItem(16, i10);
		
		ItemStack i11 = new ItemStack(Material.EMERALD, 1);
		ItemMeta im11 = i11.getItemMeta();
		im11.setDisplayName("§6Dinero: §f" + cp.getP(p.getName()).getString("Money"));
		i11.setItemMeta(im11);
		inv.setItem(0, i11);
		
		ItemStack i12;
		try { Set<String> b = fc.getConfigurationSection("Permissions").getKeys(false);
			Boolean a = true;
			for (String s : b) {
				s = s.replace(",", ".");
				if (!p.hasPermission(s)) {
					a = false;
				}
			}
			if (a == true) {
				if (cp.getP(p.getName()).getString("Kits." + f.getName().substring(0, f.getName().length()-4)) == null) {
					i12 = new ItemStack(Material.DIAMOND, 1);
					ItemMeta im12 = i12.getItemMeta();
					im12.setDisplayName("§6Comprar Kit");
					i12.setItemMeta(im12);
				} else {
					i12 = new ItemStack(Material.DIAMOND_SWORD, 1);
					ItemMeta im12 = i12.getItemMeta();
					im12.setDisplayName("§aElegir Kit");
					i12.setItemMeta(im12);
				}
			} else {
				i12 = new ItemStack(Material.BEDROCK, 1);
				ItemMeta im12 = i12.getItemMeta();
				im12.setDisplayName("§cKit Bloqueado!");
				i12.setItemMeta(im12);
			}
		} catch(NullPointerException e) {
			if (cp.getP(p.getName()).getString("Kits." + f.getName().substring(0, f.getName().length()-4)) == null) {
				i12 = new ItemStack(Material.DIAMOND, 1);
				ItemMeta im12 = i12.getItemMeta();
				im12.setDisplayName("§6Comprar Kit");
				i12.setItemMeta(im12);
			} else {
				i12 = new ItemStack(Material.DIAMOND_SWORD, 1);
				ItemMeta im12 = i12.getItemMeta();
				im12.setDisplayName("§aElegir Kit");
				i12.setItemMeta(im12);
			}
		}
		inv.setItem(6, i12);
		
		ItemStack i13 = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta im13 = i13.getItemMeta();
		if (fc.getString("Cost") == null) {
			im13.setDisplayName("§6Costo: §aGratis");	
		} else {
			im13.setDisplayName("§6Costo: §f" + fc.getInt("Cost"));
		}
		i13.setItemMeta(im13);
		inv.setItem(1, i13);
		
		ItemStack i14 = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta im14 = i14.getItemMeta();
		if (fc.getString("Cost-Per-Use") == null) {
			im14.setDisplayName("§6Costo por uso: §aNinguno");	
		} else {
			im14.setDisplayName("§6Costo por uso: §f" + fc.getInt("Cost-Per-Use"));
		}
		i14.setItemMeta(im14);
		inv.setItem(2, i14);
		
		p.closeInventory();
		p.openInventory(inv);
	}
}
