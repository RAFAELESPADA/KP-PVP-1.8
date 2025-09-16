package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Neutralizer implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  public static HashMap<String, String> kit = new HashMap<>();
  
  public static List<Player> neutralized = new ArrayList<>();
  
  @EventHandler
  public void onNeutralizer(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.BARRIER && Habilidade.getAbility(p) == "Neutralizer")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
API.sendMessageCooldown(p);
      } else {
    	  if (API.isInRegion(p)) {
    		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
    		  return;
    	  }
    	  
        e.setCancelled(true);
        Cooldown.add(p, Main.kits.getInt("NeutralizerCooldown"));

        p.sendMessage(ChatColor.RED + "You removed the hability of all players nearby you!");
        for (Entity en : p.getNearbyEntities(12, 12, 12)) {
        	
          if (en instanceof Player && en != p) {
            Player pl = (Player)en;
            if (API.isInRegion(pl)) {
      		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
      		  return;
      	  }
            if (Habilidade.getAbility(pl) != "Neutralizer") {
            neutralized.add(pl);
            kit.put(pl.getName(), Habilidade.getAbility(pl));
         
            Habilidade.setAbility(pl, "Neutralized");
            pl.sendMessage(ChatColor.RED + "Your ability got removed for 45 seconds by " + p.getName());
          } 
          }
        } 
        (new BukkitRunnable() {
            public void run() {
              for (Player pl : Neutralizer.neutralized) {
                Habilidade.setAbility(pl, kit.get(pl.getName()));
                pl.sendMessage(ChatColor.GREEN +"Your ability is available again.");
              } 
            }
          }).runTaskLater((Plugin)Main.getInstance(), 900L);
      }  
  }
}
