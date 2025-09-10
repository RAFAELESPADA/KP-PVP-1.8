package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Asteroid implements Listener {
public static HashMap<Entity, Location> loc = new HashMap<>();
int total = 0;	

@EventHandler
public void onAsteriod(PlayerInteractEntityEvent e) {
  final Player p = e.getPlayer();
  if (e.getPlayer().getItemInHand().getType() == Material.OBSIDIAN && Habilidade.getAbility(p) == "Asteroid")
    if (Cooldown.add(p)) {
      e.setCancelled(true);
      p.updateInventory();
      API.sendMessageCooldown(p);
    } else {
    	Player eL = (Player)e.getRightClicked();
    	if (!Habilidade.ContainsAbility((eL))) {
    		p.sendMessage("DO NOT USE THIS ON PLAYERS WITHOUT ANY KIT");
    	}
     Cooldown.add(p, Main.kits.getInt("AsteroidCooldown"));
      e.setCancelled(true);
      p.updateInventory();
      p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 100));
      @SuppressWarnings("deprecation")
	final FallingBlock ent = p.getWorld().spawnFallingBlock(e.getRightClicked().getLocation().add(0.0D, 10.0D, 0.0D), 
          Material.OBSIDIAN, (byte)0);
      ent.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
      
      FallingBlock fallingBlock = ent;
      fallingBlock.setDropItem(false);
      loc.put(fallingBlock, fallingBlock.getLocation());
      (new BukkitRunnable() {
          public void run() {
        if (Habilidade.getAbility(p) != "Asteroid") {
        	cancel();
        	return;
        }
        if (eL.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y") - 3) {
			return;
		 }
        if (eL.getLocation().distance(p.getLocation()) > 20) {
        	cancel();
        	return;
        }
        if(total > 5) {
        	p.sendMessage(ChatColor.RED + "Your asteroid Rain ended!");
            cancel();
        }
        (new BukkitRunnable() {
            public void run() {
            	  for (Entity en2 : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
            		  
            		  if (!(en2 instanceof Player)) {
                  		  return;
                  	  }
            		  Player eL2 = (Player)en2;
            		  if (!Habilidade.ContainsAbility((eL2))) {
            			 return; 
            	    	}
            		  if (en2 != null) {
            	
            	Player peni = (Player)en2;
            	final FallingBlock ent = p.getWorld().spawnFallingBlock(e.getRightClicked().getLocation().add(0.0D, 10.0D, 0.0D), 
            	          Material.OBSIDIAN, (byte)0);
            	      ent.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
            	      
            	      FallingBlock fallingBlock = ent;
            	      fallingBlock.setDropItem(false);
            	Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, en2.getLocation(), 50.0D);
                Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en2.getLocation(), 50.0D);          	
Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, en2.getLocation(), 50.0D);
                Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en2.getLocation(), 50.0D);
                Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, en2.getLocation(), 50.0D);
                Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en2.getLocation(), 50.0D);
                en2.setFireTicks(20); 
              Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, en2.getLocation(), 50.0D);
              Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en2.getLocation(), 50.0D);
              p.getWorld().strikeLightning(en2.getLocation());
          	peni.damage(2, p);
              en2.sendMessage(ChatColor.BLUE + " You got hit by a Asteroid! Ouch...");
              total++;
            }}
            }}).runTaskLater(Main.getInstance(), 5L);
        for (Entity en : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
      	  if (!(en instanceof Player)) {
      		  return;
      	  }
          if (en != p && en instanceof Player)
        	  if (en != null) {
        		  Player eL2 = (Player)en;
        		  if (!Habilidade.ContainsAbility((eL2))) {
         			 return; 
         	    	}
            en.setFireTicks(20); 
          	Player peni = (Player)en;
            Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, en.getLocation(), 50.0D);
            Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en.getLocation(), 50.0D);
            Particles.HEART.display(0.0F, 0.0F, 0.0F, 0.0F, 100, en.getLocation(), 50.0D);
            
          	peni.damage(1, p);
        	  }} 
            if (ent.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType() == Material.AIR) {
              Asteroid.loc.put(ent, ent.getLocation());
            	ent.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                
          	fallingBlock.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
              Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, ent.getLocation(), 50.0D);
              Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, ent.getLocation(), 50.0D);
              (new BukkitRunnable() {
                  public void run() {
                    p.getWorld().createExplosion(Asteroid.loc.get(ent), 2.6F);
                  }
                }).runTaskLater(Main.getInstance(), 5L);
              for (Entity en : ent.getNearbyEntities(15.0D, 15.0D, 15.0D)) {
            	  if (!(en instanceof Player)) {
            		  return;
            	  }
            	  Player eL2 = (Player)en;
        		  if (!Habilidade.ContainsAbility((eL2))) {
        			 return; 
        	    	}
            		int total = 0;
            		if(total > 5) {
                        cancel();
                    }
                    
                if (en != p && en instanceof Player)
                
                  en.setFireTicks(20); 
                	Player peni = (Player)en;
                	peni.damage(2, p);
                	total++;
              } 
              
            } else {
            	final FallingBlock ent = p.getWorld().spawnFallingBlock(e.getRightClicked().getLocation().add(0.0D, 10.0D, 0.0D), 
          	          Material.OBSIDIAN, (byte)0);
            	Asteroid.loc.put(ent, ent.getLocation());
            	ent.setDropItem(false);
            	ent.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                
              Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, 
                  Asteroid.loc.get(ent), 50.0D);
              Particles.DRIP_LAVA.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Asteroid.loc.get(ent), 
                  50.0D);
              Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.25F, 50, Asteroid.loc.get(ent), 
                  50.0D);
              (new BukkitRunnable() {
                  public void run() {
                    p.getWorld().createExplosion(Asteroid.loc.get(ent), 2.6F);
                  }
                }).runTaskLater(Main.getInstance(), 5L);
              for (Entity en : ent.getNearbyEntities(15.0D, 15.0D, 15.0D)) {
            	  if (!(en instanceof Player)) {
            		  return;
            	  }
            	  Player eL2 = (Player)en;
        		  if (!Habilidade.ContainsAbility((eL2))) {
        			 return; 
        	    	}
                if (en != p && en instanceof Player)
                  en.setFireTicks(20); 
                	Player peni = (Player)en;
                	
                	  total++;
              } 
            
              ent.remove();
              cancel();
            } 
          }
        }).runTaskTimer(Main.getInstance(), 0L, 40L);
    }  
}
}