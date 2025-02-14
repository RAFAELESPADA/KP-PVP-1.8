package me.RafaelAulerDeMeloAraujo.Coins;


import java.util.HashMap;

import org.bukkit.entity.Player;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;

public class XP
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<Player, Integer> bal = new HashMap();
  public static HashMap<Player, Integer> getCoinsMap()
  {
    return bal;
  }
  
  public static void addXP(Player player, int amount)
  {
	  HelixPlayer Sun8oxData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  Sun8oxData.getPvp().addXP(amount);
  }
  
  public static void setXP(Player player, int amount)
  {
	  HelixPlayer Sun8oxData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  Sun8oxData.getPvp().setXp(amount);
  }
  
  public static int getXP(Player player)
  {
	  HelixPlayer Sun8oxData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  
	  if (Sun8oxData == null) {
		  return 0;
	  }
	  return  Sun8oxData.getPvp().getXp();
  }

  public static void removeXP(Player player, int amount)
  {
	  HelixPlayer Sun8oxData = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
	  if (amount < Sun8oxData.getPvp().getCoins()) {
	  setXP(player, Sun8oxData.getPvp().getXp() - amount);
	  } else {
		Sun8oxData.getPvp().setXp(Main.customization.getInt("MinimiumAmountOfXP"));  
	  }
  }
}