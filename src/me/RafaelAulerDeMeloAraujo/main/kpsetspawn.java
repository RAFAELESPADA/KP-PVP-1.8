package me.RafaelAulerDeMeloAraujo.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;



public class kpsetspawn
implements  CommandExecutor{
	

	 private Main main = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
		
	      if (cmd.getName().equalsIgnoreCase("kpsetspawn")){
	        if (!p.hasPermission("kitpvp.setspawn")){
	          return true;
	        }
	        Main.plugin.getConfig().set("Spawn.World", p.getLocation().getWorld().getName());
	        Main.plugin.getConfig().set("Spawn.X", Double.valueOf(p.getLocation().getX()));
	        Main.plugin.getConfig().set("Spawn.Y", Double.valueOf(p.getLocation().getY()));
	        Main.plugin.getConfig().set("Spawn.Z", Double.valueOf(p.getLocation().getZ()));
	        Main.plugin.getConfig().set("Spawn.Pitch", Float.valueOf(p.getLocation().getPitch()));
	        Main.plugin.getConfig().set("Spawn.Yaw", Float.valueOf(p.getLocation().getYaw()));
	        Main.plugin.getConfig().set("SpawnD.World", p.getLocation().getWorld().getName());
	        Main.plugin.getConfig().set("SpawnD.X", Double.valueOf(p.getLocation().getX()));
	        Main.plugin.getConfig().set("SpawnD.Y", Double.valueOf(p.getLocation().getY()));
	        Main.plugin.getConfig().set("SpawnD.Z", Double.valueOf(p.getLocation().getZ()));
	        Main.plugin.getConfig().set("SpawnD.Pitch", Float.valueOf(p.getLocation().getPitch()));
	        Main.plugin.getConfig().set("SpawnD.Yaw", Float.valueOf(p.getLocation().getYaw()));
	        try {
	        	Main.plugin.getConfig().options().copyDefaults(true);
	        	Main.plugin.getConfig().options().copyHeader(true);
				Main.plugin.getConfig().save(new File(Bukkit.getWorldContainer().getAbsolutePath() + "/plugins/KP-PVP/config.yml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        p.sendMessage(API.NomeServer + Main.messages.getString("SpawnSeted").replace("&", "§"));
	        p.sendMessage("§cRESTART THE SERVER TO APPLY THE NEW SPAWN CHANGES!");
	        return true;
	      }
		return false;
	}
	}
