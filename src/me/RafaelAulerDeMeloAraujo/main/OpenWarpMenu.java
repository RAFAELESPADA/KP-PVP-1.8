package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;



public class OpenWarpMenu
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("kpopenwarps")){
	        if (!Join.game.contains(p.getName())){
	        	 p.sendMessage("§cYou are not in kitpvp to open warp menu");
	          return true;
	        }
	 WarpMenu.openwarp(p);
	        return true;
	      }
		return false;
	}
	}
