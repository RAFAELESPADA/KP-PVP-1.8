package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Jinbei implements Listener {

	public static HashMap<String, Long> cooldown = new HashMap<>();
	  FallingBlock f;
	  public static HashMap<Entity, Location> loc = new HashMap<>();
	  @EventHandler(priority=EventPriority.LOWEST)
	    public void onFallingBlockLand(EntityChangeBlockEvent event) {
	        Entity ent = event.getEntity();
	        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
	        	 World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	       	  if (event.getBlock().getWorld().equals(w)) {
	       		  event.setCancelled(true);
	       	  }
	        	if (ent.hasMetadata("FALLBLAST")) {
	        		for (Player p4: Bukkit.getOnlinePlayers()) {
        		        Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(0.0D, 2.0D, 1.0D), p4);
        	            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(1.0D, 2.0D, 0.0D), p4);
        		        }
	        		for (Entity bi : ent.getNearbyEntities(3, 3, 3)) {
	        			if (bi instanceof Player) {
	        				((Player) bi).damage(2);
	        				API.darEfeito(((Player) bi), PotionEffectType.POISON, 5, 0);
	        				Vector v = new Vector();
	  	                  v.setX(1.5D);
	  	                  v.setY(0.8D);
	  	                  v.setZ(1.9D);
	  	                ((Player) bi).setVelocity(v);
	        			}
	        		 event.setCancelled(true);
	        	}
	        	if (ent.hasMetadata("FALLBLAST2")) {
	        		for (Player p4: Bukkit.getOnlinePlayers()) {
        		        Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(0.0D, 2.0D, 1.0D), p4);
        	            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(1.0D, 2.0D, 0.0D), p4);
        		        }
	        		for (Entity bi : ent.getNearbyEntities(3, 3, 3)) {
	        			if (bi instanceof Player) {
	        				((Player) bi).damage(4);
	        				 Particles.FLAME.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(1.0D, 2.0D, 0.0D), ((Player)bi));

	        				 ((Player) bi).getWorld().strikeLightning(ent.getLocation());
	        			}
	        		}
	        		 event.setCancelled(true);
	        	}
	        }
	        }
	    }
	  
	  
	  
	  @EventHandler
	  public void hadouken(PlayerInteractEvent e) {
	    final Player p = e.getPlayer();
	    Player g = p;
	    if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && 
	      Habilidade.getAbility(p) == "Jinbei" && p.getItemInHand().getType() == Material.BUCKET)
	      if (!Cooldown.add(p)) {
	    	  if (p.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y") - 2) {
	    		  p.sendMessage("DO NOT USE THIS ON SPAWN!");
					return;
				 }
	        final FallingBlock  fallingBlock = g.getWorld().spawnFallingBlock(g.getLocation().add(0.0D, 2.0D, 0.0D), Material.STAINED_GLASS, (byte)0);
	        final FallingBlock  fallingBlock2 = g.getWorld().spawnFallingBlock(g.getLocation().add(1.0D, 3.0D, 1.0D), Material.STAINED_GLASS, (byte)0);
	        loc.put(fallingBlock, fallingBlock.getLocation());

	      	fallingBlock.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));

	      	fallingBlock2.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
	        FallingBlock ent =  fallingBlock;
	        fallingBlock.setVelocity(p.getLocation().getDirection().multiply(1.7F));
	        fallingBlock.setDropItem(false);
	        fallingBlock2.setVelocity(p.getLocation().getDirection().multiply(1.4F));
	        fallingBlock2.setDropItem(false);
	        for (Player p4: Bukkit.getOnlinePlayers()) {
	        Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent).add(0.0D, 2.0D, 0.0D), p4);
            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent).add(0.0D, 2.0D, 0.0D), p4);
	        }
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
           
            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	        Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
            (new BukkitRunnable() {

	            int tempo = 0;
	            int total = 5; 
	            public void run() {
		            tempo = tempo + 1;
		            p.sendMessage(ChatColor.RED + "You shoot " + tempo + " Times.");
		            if (tempo >= total) {
		            	p.sendMessage(ChatColor.RED + "Your jinbei power stopped");
	                    cancel();
	                }
		            if (Habilidade.getAbility(p) != "Jinbei") {
		            	cancel();
		            }
                      final FallingBlock  fallingBlock = g.getWorld().spawnFallingBlock(g.getLocation().add(0.0D, 2.0D, 0.0D), Material.STAINED_GLASS, (byte)0);
          	        final FallingBlock  fallingBlock2 = g.getWorld().spawnFallingBlock(g.getLocation().add(1.0D, 3.0D, 1.0D), Material.STAINED_GLASS, (byte)0);
          	        loc.put(fallingBlock, fallingBlock.getLocation());
Random r = new Random();
float r2 = r.nextFloat();
          	      	fallingBlock.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));

          	      	fallingBlock2.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
          	       
          	        fallingBlock.setVelocity(p.getLocation().getDirection().multiply(r2 + 1.7F));
          	        fallingBlock.setDropItem(false);
          	        fallingBlock2.setVelocity(p.getLocation().getDirection().multiply(r2 + 1.4F));
          	        fallingBlock2.setDropItem(false);
          	    Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
                
                }}).runTaskTimer(Main.getInstance(), 20L, 40L);
            

		       Cooldown.add(p, Main.kits.getInt("JinbeiCooldown"));
		      
		       } else {
	        API.sendMessageCooldown(p); 
	  }
	}
}
