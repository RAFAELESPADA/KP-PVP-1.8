package me.RafaelAulerDeMeloAraujo.Coins;



import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class Coins
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<String, Double> bal = new HashMap();
  public static Economy econ = null;
  public static Permission perms = null;
  public static boolean VAULTON = Main.getInstance().getConfig().getBoolean("UseVault");
  
  public static HashMap<String, Double> getCoinsMap()
  {
    return bal;
  }
  public static boolean setupEconomy() {
      if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
          return false;
      }
      RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
      if (rsp == null) {
          return false;
      }
      econ = rsp.getProvider();
      return econ != null;
  }
  public static boolean setupPermissions() {
	  if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
          return false;
      }
      RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
      perms = rsp.getProvider();
      return perms != null;
  }
  
  public static void addCoins(Player player, int amount)
  {
if (!VAULTON) {
      WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		
	  Sun8oxData.getPvp().addCoins(amount); 
} else {

		  econ.depositPlayer(player, amount); 
	  }
  }
  
  public static int getCoins(Player player)
  {

     WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		
		if (Sun8oxData == null) {
		  return 0;
	  }
		if (VAULTON) {
return (int) econ.getBalance(player);		
		}
		else {
	  return  Sun8oxData.getPvp().getCoins();
  }
  }

  public static void removeCoins(Player player, int amount)
  {
	  if (!VAULTON) {
      WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		if (Sun8oxData.getPvp().getCoins() > amount) {
	  Sun8oxData.getPvp().removeCoins(amount);
  }
  	else {

  	  Sun8oxData.getPvp().setCoins(0);
  	}
  }
	  else {
		  econ.withdrawPlayer(player, amount);
	  }
  }
}
