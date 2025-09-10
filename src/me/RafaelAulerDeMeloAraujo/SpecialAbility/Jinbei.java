package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
	        	
	        	if (ent.hasMetadata("FALLBLAST")) {
	        		for (Player p4: Bukkit.getOnlinePlayers()) {
        		        Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(0.0D, 2.0D, 1.0D), p4);
        	            Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(1.0D, 2.0D, 0.0D), p4);
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
            fallingBlock.getWorld().strikeLightning(loc.get(ent).add(0.0D, 2.0D, 0.0D));
            fallingBlock.getWorld().strikeLightning(loc.get(ent).add(1.0D, 2.0D, 3.0D));
            fallingBlock.getWorld().strikeLightning(loc.get(ent).add(3.0D, 4.0D, 2.0D));
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
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
                 
                }}).runTaskLater(Main.getInstance(), 20L);
            (new BukkitRunnable() {
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
             
                }}).runTaskLater(Main.getInstance(), 40L);
            (new BukkitRunnable() {
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
                
                }}).runTaskLater(Main.getInstance(), 60L);
            (new BukkitRunnable() {
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
              
                }}).runTaskLater(Main.getInstance(), 80L);
            (new BukkitRunnable() {
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
             
                }}).runTaskLater(Main.getInstance(), 120L);
            (new BukkitRunnable() {
	            public void run() {
          int tempo = 20;
          tempo --;
          if (tempo < 0) {
        	  cancel();
        	  return;
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
          	      fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
             
                }}).runTaskLater(Main.getInstance(), 140L);
	        (new BukkitRunnable() {
	            public void run() {
	              if (ent.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType() == Material.AIR) {
	                Sound sound;
	                Jinbei.loc.put(ent, ent.getLocation());
	                Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
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
	                Vector v = Vector.getRandom();
	                  v.setX(v.getX() - 0.5D);
	                  v.setY(v.getY() - 0.20000000298023224D);
	                  v.setZ(v.getZ() - 0.5D);
	                  fallingBlock.setVelocity(v);
	                  fallingBlock.setHurtEntities(true);

	                  
	                Particles.REDSTONE.display(0.0F, 0.0F, 0.0F, 1.0F, 0, ent.getLocation(), 20.0D);
	                p.getWorld().playEffect(Jinbei.loc.get(ent), Effect.STEP_SOUND, Material.LAPIS_BLOCK, 20);
	                Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.1F, 5, Jinbei.loc.get(ent), 50.0D);
	                try {
	                  sound = Sound.valueOf("WATER");
	                } catch (IllegalArgumentException ey) {
	                  sound = Sound.valueOf("BLOCK_WATER_AMBIENT");
	                } 
	                p.getWorld().playSound(Jinbei.loc.get(ent), sound, 1.0F, 1.0F);
	                p.getWorld().playSound(Jinbei.loc.get(ent), sound, 3.0F, 1.0F);
	                fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
	                (new BukkitRunnable() {
	                    public void run() {
	                    	 Particles.FLAME.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                    	 Particles.NOTE.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	      	              
	                    	fallingBlock.remove();	
	                        fallingBlock.getWorld().getBlockAt(loc.get(ent)).setType(Material.AIR);
	    	                
	                    }
	                 }).runTaskLater(Main.getInstance(), 100L);
	              } else {
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
	                  Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                  Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                  Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                  Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                  Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                for (int i = 0; i < 15; i++) {
	                  FallingBlock fallingBlock = g.getWorld().spawnFallingBlock(Jinbei.loc.get(ent), Material.STAINED_GLASS, (byte)
	                      11);
	                  f = fallingBlock;
	                  Vector v = Vector.getRandom();
	                  v.setX(v.getX() - 0.5D);
	                  v.setY(v.getY() - 0.20000000298023224D);
	                  v.setZ(v.getZ() - 0.5D);
	                  fallingBlock.setVelocity(v);
	                  (new BukkitRunnable() {
		                    public void run() {
		                    	fallingBlock.remove();
		                        fallingBlock.getWorld().getBlockAt(loc.get(ent)).setType(Material.AIR);
		                    }
	                  }).runTaskLater(Main.getInstance(), 100L);
	                } 
	                (new BukkitRunnable() {
	                    public void run() {
	                      for (Entity entity : ent.getNearbyEntities(10.0D, 5.0D, 10.0D)) {
	                        if (entity instanceof Player && entity != p) {
	                          Sound sound;
	                          Player p2 = (Player)entity;
	                   		if (!Habilidade.ContainsAbility(p2)) {
	                        	return;
	                        }
	                          Vector vector = entity.getLocation().toVector()
	                            .subtract(p.getLocation().toVector()).normalize();
	                          vector.setY(0.5D);
	                          vector.multiply(3.3F);
	                          vector.setY(1.5D);
	                          entity.setVelocity(vector);
	                          entity.getWorld().strikeLightning(entity.getLocation());

	                          entity.getWorld().strikeLightning(entity.getLocation());
	                          entity.getWorld().strikeLightning(entity.getLocation());
	                          Player ey = (Player)entity;
	                          ey.damage(5, p);
	                          try {
	                            sound = Sound.valueOf("WATER");
	                          } catch (IllegalArgumentException eyf) {
	                            sound = Sound.valueOf("BLOCK_WATER_AMBIENT");
	                          } 
	                          p.getWorld().playSound(Jinbei.loc.get(ent), sound, 1.0F, 1.0F);
	                          p.getWorld().playSound(Jinbei.loc.get(ent), sound, 3.0F, 1.0F);
	                          fallingBlock.getWorld().strikeLightning(Jinbei.loc.get(ent));
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
	      	             
	                        } 
	                      } 
	                    }
	                  }).runTaskLater(Main.getInstance(), 1L);
	                (new BukkitRunnable() {
	                    public void run() {
	                    	fallingBlock.remove();	
	                    	 Particles.FLAME.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                    	 Particles.NOTE.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                    	 Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                         Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                         Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                         Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                         Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                         Particles.WATER_SPLASH.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Jinbei.loc.get(ent), 50.0D);
	                        
	                        fallingBlock.getWorld().getBlockAt(loc.get(ent)).setType(Material.AIR);
	                    }
	                  
	                  }).runTaskLater(Main.getInstance(), 100L);
	                    }
	                cancel();
	              } 
	            }
	          ).runTaskTimer(Main.getInstance(), 0L, 1L);

		       Cooldown.add(p, Main.kits.getInt("JinbeiCooldown"));
		      
		       } else {
	        API.sendMessageCooldown(p); 
	  }
	}
}
