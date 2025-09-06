package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Zeus implements Listener {
  public static HashMap<Location, Byte> cd = new HashMap<>();
  
  
  @EventHandler
  public void onZeus(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    Player g = p;
    if (e.getPlayer().getItemInHand().getType() == Material.INK_SACK && 
      e.getAction().equals(Action.RIGHT_CLICK_AIR) && 
      Habilidade.getAbility(p) == "Zeus")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (p.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y") - 2) {
    		  p.sendMessage("DO NOT USE THIS ON SPAWN!");
				return;
			 }
        Location location = p.getEyeLocation();
       BlockIterator blocksToAdd = new BlockIterator(
            location, 0.0D, 30);
       
       (new BukkitRunnable() {
            
        	Location blockToAdd;
            public void run() {
              while (blocksToAdd.hasNext() && blocksToAdd.next() != p.getLocation().getBlock() && blocksToAdd != null) {
            	 
               blockToAdd = blocksToAdd.next().getLocation();
               if (blockToAdd == null) {
            	   cancel();
            	   return;
               }
                p.getWorld().strikeLightning(blockToAdd);
                p.getWorld().strikeLightning(blockToAdd);

                Particles.FLAME.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.NOTE.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.NOTE.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.NOTE.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                for (Player p4: Bukkit.getOnlinePlayers()) {
        	        Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, blockToAdd.add(0.0D, 2.0D, 0.0D), p4);
                    Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, blockToAdd.add(0.0D, 2.0D, 0.0D), p4);
        	        
                Particles.HEART.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);

                Particles.HEART.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);Particles.EXPLOSION_HUGE.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);

                Particles.HEART.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.HEART.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                } Particles.FLAME.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.FLAME.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                Particles.FLAME.display(1.3F, 1.3F, 1.3F, 0.25F, 10, blockToAdd, 50.0D);
                for (Entity et : p.getWorld().getNearbyEntities(blockToAdd, 15, 15, 15)) {
                if (et instanceof Player) {
                	Player p2 = (Player)et;
                	if (p != p2) { 
                		if (!Habilidade.ContainsAbility(p2)) {
        	return;
        }
                	p2.damage(6, p);
                	
                et.getWorld().strikeLightning(et.getLocation());

                et.getWorld().strikeLightning(et.getLocation());
              }   
                }
            }
                 
          }}}).runTaskTimer((Plugin)Main.getInstance(), 0L, 10L);
       
        e.setCancelled(true);
        p.updateInventory();
        Cooldown.add(p, Main.kits.getInt("ZeusCooldown")); 
  }
  }
}
