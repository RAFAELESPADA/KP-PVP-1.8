package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Minato implements Listener {
  
  @EventHandler
  public void onNaruto(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.ARMOR_STAND && Habilidade.getAbility(p) == "Minato")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (API.isInRegion(p)) {
    		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
    		  e.setCancelled(true);
    		  return;
    	  }
        Cooldown.add(p, Main.kits.getInt("MinatoCooldown"));
        Bot clone = new Bot();
        if (clone.isSpawned()) {

  		  p.sendMessage("You already have a clone!");
  		  e.setCancelled(true);
  		  return;
        }
        clone.setName(p.getName());
        clone.setInvincible(false);
        clone.summon(p.getLocation(), true);
        Bot.ownedBot.put(p.getName(), clone);
        clone.setGuardienFor(p);
        e.setCancelled(true);
      }  
  }
  @EventHandler
  public void onNarutoa(EntityDamageByEntityEvent e) {
	  if (e.getEntity() == null) {
		  return;
	  }
	  if (e.getDamager() == null) {
		  return;
	  }
	  if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
    final Player p = (Player) e.getEntity();
    final Player d = (Player) e.getDamager();
    if (!Bot.attackPlayer.containsKey(d.getName()) && Bot.ownedBot.containsKey(p.getName())) {
    	if (Habilidade.getAbility(p) == "Minato") {	
    	Bot.attackPlayer.put(p.getName(), d);
    	   (new BukkitRunnable() {
    	          public void run() {
    	         Bot.attackPlayer.remove(p.getName());	          
    	          }
    	        }).runTaskLater((Plugin)Main.getInstance(), 150L); 
    	  }
    
       
    }  
    	  
	  }   	  
}
}

