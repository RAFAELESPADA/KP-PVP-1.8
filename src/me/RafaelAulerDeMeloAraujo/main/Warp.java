package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.Warps.SettingsManager;
import me.RafaelAulerDeMeloAraujo.X1.Sumo;

public class Warp implements CommandExecutor {
	SettingsManager settings = SettingsManager.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kpsetwarp")) {
			if (!sender.hasPermission("kitpvp.setwarp")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
                return true;
        }
            if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Use /kpsetwarp <FPS , Challenge");
                    return true;
            }
            Player p = (Player)sender;
            settings.getData().set("warps." + args[0].toLowerCase() + ".world", p.getLocation().getWorld().getName());
            settings.getData().set("warps." + args[0].toLowerCase() + ".x", p.getLocation().getX());
            settings.getData().set("warps." + args[0].toLowerCase() + ".y", p.getLocation().getY());
            settings.getData().set("warps." + args[0].toLowerCase() + ".z", p.getLocation().getZ());
            settings.saveData();
            p.sendMessage(ChatColor.GREEN + "Seted warp " + args[0].toLowerCase() + " with sucess!");
    }
   
    if (cmd.getName().equalsIgnoreCase("kpwarp")) {
            if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "/kpwarp <FPS , Challenge, Sumo>");
                    return true;
            }
            if (!Join.game.contains(sender.getName())) {
                sender.sendMessage(ChatColor.RED + "[KITPVP] You are not in game!");
                return true;
        }
            Player p = (Player)sender;
            if (Habilidade.getAbility(p) != Main.getInstance().getConfig().getString("NoKit-DefaultName")) {
                sender.sendMessage(ChatColor.RED + "[KITPVP] You cannot go to a warp with a kit selected!");
                return true;
        }
            
            if (settings.getData().getConfigurationSection("warps." + args[0].toLowerCase()) == null) {
                    p.sendMessage(ChatColor.YELLOW + "Warp " + args[0].toLowerCase() + " is not seted yet!");
                    return true;
            }
            if (args[0].equalsIgnoreCase("FPS")) {
            	 World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + args[0].toLowerCase() + ".world"));
            	double x = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".x");
                double y = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".y");
                double z = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".z");
                p.teleport(new Location(w, x, y, z));
                warp(p);	  	
                ItemStack sopas = new ItemStack(Material.BOWL, 64);
                ItemMeta ksopas = sopas.getItemMeta();
                ksopas.setDisplayName("§eBowl");
                sopas.setItemMeta(ksopas);

              	Habilidade.setAbility(p, "FPS");
                ItemStack cogur = new ItemStack(Material.RED_MUSHROOM, 64);
                ItemMeta kcogur = cogur.getItemMeta();
                kcogur.setDisplayName("§3--> §cRed §3<--");
                cogur.setItemMeta(kcogur);
                ItemStack cogum = new ItemStack(Material.BROWN_MUSHROOM, 64);
                ItemMeta kcogum = cogum.getItemMeta();
                kcogum.setDisplayName("§3--> §8Brown §3<--");
                cogum.setItemMeta(kcogum);
               p.getInventory().setItem(14, cogum);
               p.getInventory().setItem(15, cogur);
               p.getInventory().setItem(13, sopas);
                API.sopa(p);
                ItemStack espada = new ItemStack(Material.STONE_SWORD);
                ItemMeta kespada = espada.getItemMeta();
                kespada.setDisplayName("§eSword");
                espada.setItemMeta(kespada);
                p.getInventory().setItem(0, espada);
                 
                p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0].toLowerCase() + "!");
            }
            else if (args[0].equalsIgnoreCase("Challenge")) {
           	 World w = Bukkit.getServer().getWorld(settings.getData().getString("warps." + args[0].toLowerCase() + ".world"));
           	double x = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".x");
               double y = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".y");
               double z = settings.getData().getDouble("warps." + args[0].toLowerCase() + ".z");
               p.teleport(new Location(w, x, y, z));
               warp(p);
               API.sopa(p);

             	Habilidade.setAbility(p, "LAVA");
               ItemStack sopas = new ItemStack(Material.BOWL, 64);
               ItemMeta ksopas = sopas.getItemMeta();
               ksopas.setDisplayName("§eBowl");
               sopas.setItemMeta(ksopas);
               
               ItemStack cogur = new ItemStack(Material.RED_MUSHROOM, 64);
               ItemMeta kcogur = cogur.getItemMeta();
               kcogur.setDisplayName("§3--> §cRed §3<--");
               cogur.setItemMeta(kcogur);
               ItemStack cogum = new ItemStack(Material.BROWN_MUSHROOM, 64);
               ItemMeta kcogum = cogum.getItemMeta();
               kcogum.setDisplayName("§3--> §8Brown §3<--");
               cogum.setItemMeta(kcogum);
              p.getInventory().setItem(14, cogum);
              p.getInventory().setItem(15, cogur);
              p.getInventory().setItem(13, sopas);
               p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0].toLowerCase() + "!");
           }
            else if (args[0].equalsIgnoreCase("Sumo")) {
            Sumo.entrar1v1(p);
    }
    }
		return false;
	}


private void warp(Player player) {
	player.setGameMode(GameMode.ADVENTURE);
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	player.setAllowFlight(false);
	Cooldown.remove(player);
	  /* 280 */       Deshfire.Armadura.remove(player);
	  /* 281 */       Deshfire.Armadura2.remove(player);
	  /* 282 */       Deshfire.cooldownm.remove(player);
	player.setFlying(false);

API.BuildScore(player);
	player.getInventory().setHeldItemSlot(0);
	player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
}

}

