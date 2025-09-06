/*     */ package me.RafaelAulerDeMeloAraujo.Coins;
/*     */ 
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.main.Main;



@SuppressWarnings("unused")
public class Commands implements CommandExecutor {
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("givecoins")) {
			if (!sender.hasPermission("kitpvp.coins")) {
				sender.sendMessage("You dont have permission");
			} else {
				if (args.length == 0) {
					sender.sendMessage( "§c§l/givecoins [player] [amount]");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage( "§c§lThe Player is offline");
					return true;
				}
				if (isNumeric(args[1])) {
					Integer coins = Integer.parseInt(args[1]);
					Coins.addCoins(target, coins);
					sender.sendMessage( "§eYou give the player " + target.getName() + "" + coins
							+ "§bCoins");  
				target.sendMessage(API.NomeServer +  Main.messages.getString("NewBalanceMessage").replace("&", "§").replace("%coins%", String.valueOf(coins)));			
                   target.sendMessage(API.NomeServer +  Main.messages.getString("BalanceUpdated").replace("&", "§"));
					
				}
			}
		}
		return false;
	}
}

		


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Coins\Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
