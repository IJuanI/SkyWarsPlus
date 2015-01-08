package me.Juanco.Commands;

import me.Juanco.Commands.Kits.KitAddcost;
import me.Juanco.Commands.Kits.KitAddperm;
import me.Juanco.Commands.Kits.KitCostpu;
import me.Juanco.Commands.Kits.KitCreate;
import me.Juanco.Commands.Kits.KitDelete;
import me.Juanco.Commands.Kits.KitDelperms;
import me.Juanco.Commands.Kits.KitInvsee;
import me.Juanco.Commands.Kits.KitList;
import me.Juanco.Commands.Kits.KitLockable;
import me.Juanco.Commands.Kits.KitPayeco;
import me.Juanco.Commands.Kits.KitPayxp;
import me.Juanco.Commands.Kits.KitSetinv;
import me.Juanco.Commands.Kits.KitSetoptions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandKits {

	private CommandKits() { }
	static CommandKits instance = new CommandKits();
	public static CommandKits getInstance() {
		return instance;
	}
	
	public void kits(Player p, String[] args) {
		if (!p.hasPermission("sw.admin.kits")) {
			p.sendMessage("Comando Desconocido!");
			return;
		}
		if (args.length == 1) {
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " KITS" + ChatColor.BLUE + " ===============");
			if (p.hasPermission("sw.admin.kits.create")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits create <id> <slot> <ItemID> <name>: " + ChatColor.GRAY + "Crea un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.create")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.delete")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits delete <id>: " + ChatColor.GRAY + "Borra un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.delete")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.setinv")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits setinv <id> (null): " + ChatColor.GRAY + "Establece el inventario de un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.setinv")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.addperm")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits addperm <id> <perm>: " + ChatColor.GRAY + "Agrega un permiso a un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.addperm")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.delperms")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits delperms <id>: " + ChatColor.GRAY + "Borra los permisos de un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.delperms")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.lockable")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits lockable <id> (on/off): " + ChatColor.GRAY + "Has un item desbloqueable!"); //
			}
			if (!p.hasPermission("sw.admin.kits.lockable")) {
				p.sendMessage("");
			}
			p.sendMessage(ChatColor.GOLD + "/sw kits 2: " + ChatColor.WHITE + "Siguiente pagina!");
			p.sendMessage("");
			return;
		}
		
		if (args[1].equalsIgnoreCase("2")) {
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "===============" + ChatColor.AQUA + " KITS 2" + ChatColor.BLUE + " ===============");
			if (p.hasPermission("sw.admin.kits.addcost")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits addcost <id> <amount>: " + ChatColor.GRAY + "Agrega un costo a un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.costpu")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.costpu")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits costpu <id> <amount>: " + ChatColor.GRAY + "Costos por uso!"); //
			}
			if (!p.hasPermission("sw.admin.kits.costpu")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.payxp")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits payxp <id>: " + ChatColor.GRAY + "Has que el kit cobre en xp!"); //
			}
			if (!p.hasPermission("sw.admin.kits.payxp")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.payeco")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits payeco <id>: " + ChatColor.GRAY + "Has que el kit cobre en $!"); //
			}
			if (!p.hasPermission("sw.admin.kits.payeco")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.setoptions")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits setoptions <value>: " + ChatColor.GRAY + "Establece las opciones!"); //
			}
			if (!p.hasPermission("sw.admin.kits.setoptions")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.invsee")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits invsee <id>: " + ChatColor.GRAY + "Mira el inv de un kit!"); //
			}
			if (!p.hasPermission("sw.admin.kits.invsee")) {
				p.sendMessage("");
			}
			if (p.hasPermission("sw.admin.kits.list")) {
				p.sendMessage(ChatColor.GREEN + "/sw kits list: " + ChatColor.GRAY + "Listado de kits!");
			}
			if (!p.hasPermission("sw.admin.kits.list")) {
				p.sendMessage("");
			}
			p.sendMessage("");
			return;
		}
		if (args[1].equalsIgnoreCase("create")) {
			KitCreate.getInstance().create(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("delete")) {
			KitDelete.getInstance().delete(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("setinv")) {
			KitSetinv.getInstance().setinv(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("addperm")) {
			KitAddperm.getInstance().addperm(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("delperms")) {
			KitDelperms.getInstance().delperms(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("lockable")) {
			KitLockable.getInstance().blockeable(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("addcost")) {
			KitAddcost.getInstance().addcost(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("costpu")) {
			KitCostpu.getInstance().costpu(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("payxp")) {
			KitPayxp.getInstance().payxp(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("payeco")) {
			KitPayeco.getInstance().payeco(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("setoptions")) {
			KitSetoptions.getInstance().setoptions(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("invsee")) {
			KitInvsee.getInstance().invsee(p, args);
			return;
		}
		if (args[1].equalsIgnoreCase("list")) {
			KitList.getInstance().list(p, args);
			return;
		}
	}
}
