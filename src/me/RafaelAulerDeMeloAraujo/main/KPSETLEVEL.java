package me.RafaelAulerDeMeloAraujo.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.Coins.Commands;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent;

public class KPSETLEVEL implements CommandExecutor {


public static void SetLevel(final Player p , int level) {
   XP.setXP(p, Main.customization.getInt("XP-Required-To-LevelUP") * level);
}


/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 18 */     Player p = (Player)sender;
/* 19 */     if (cmd.getName().equalsIgnoreCase("kpsetlevel"))
/*    */     {
/* 22 */       if (args.length != 2) {
/* 23 */         p.sendMessage(ChatColor.RED+ "Use /kpsetlevel <Nick> <Level>");
/* 34 */         return true;
/*    */       }       
/* 37 */       if ((args.length > 0) && (args.length == 2)) {
/* 38 */         Player t = org.bukkit.Bukkit.getServer().getPlayer(args[0]);
		if (!Commands.isNumeric(args[1])) {
			p.sendMessage("Use only numbers and a player name!");
			return true;
		}
		if (!p.hasPermission("kitpvp.setlevel")) {
			p.sendMessage("You dont have permission to do that!");
			return true;
		}
		Integer i = Integer.parseInt(args[1]);
		if (t == null) {
			p.sendMessage("This player is offline!");
			return true;
		}


SetLevel(p, i);	
PlayerLevelUPEvent helixPlayerDeathEvent = new me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent(
		p
);
Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent);
p.sendMessage(ChatColor.GREEN + "You seted the level: " + i + " to the player: " + t.getName());
/*    */ 	
	        
	
/*    */       
/*    */     }     
/* 54 */     
/*    */   }
/*    */


return false;
}
}
