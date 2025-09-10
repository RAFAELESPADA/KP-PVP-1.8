package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Asteroid implements Listener {
public static HashMap<Entity, Location> loc = new HashMap<>();
int total = 0;	

@EventHandler
public void onAsteriod(PlayerInteractEvent e) {
  final Player p = e.getPlayer();
  if (e.getPlayer().getItemInHand().getType() == Material.OBSIDIAN && Habilidade.getAbility(p) == "Asteroid")
    if (Cooldown.add(p)) {
      e.setCancelled(true);
      p.updateInventory();
      API.sendMessageCooldown(p);
    } else {
    	HashSet<Material> transparent = new HashSet<Material>();
    	transparent.add(Material.AIR);
    	Block block = p.getTargetBlock(transparent, 50);
     Cooldown.add(p, Main.kits.getInt("AsteroidCooldown"));
      e.setCancelled(true);
      p.updateInventory();
      p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 100));
      (new BukkitRunnable() {
          public void run() {
        if (Habilidade.getAbility(p) != "Asteroid") {
        	cancel();
        	return;
        }
        if(total > 10) {
        	p.sendMessage(ChatColor.RED + "Your asteroid Rain ended!");
            cancel();
            total = 0;
        }   	
            	final FallingBlock ent = p.getWorld().spawnFallingBlock(block.getLocation().add(0.0D, 20.0D, 0.0D), 
            	          Material.OBSIDIAN, (byte)0);
            	      ent.setMetadata("FALLBLAST2", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
                      
            	        for (Location loc2 : BlockUtils.sphere(ent.getLocation(), 3, false)) {
            	          (new BukkitRunnable() {
            	              public void run() {
            	                Particles.FLAME.display(0.3F, 0.3F, 0.3F, 0.25F, 10, loc2, 50.0D);
            	              }
            	            }).runTaskLater((Plugin)Main.getInstance(), 5L); 
            	      FallingBlock fallingBlock = ent;
            	      fallingBlock.setDropItem(false);
     
              
            }
            	        total = total + 1;
            	        p.sendMessage(ChatColor.GREEN + API.NomeServer + total + " Asteroids summoned");
            	        }
          
        }).runTaskTimer(Main.getInstance(), 0L, 10L);
    }  
}
}
