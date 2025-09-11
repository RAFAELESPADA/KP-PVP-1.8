package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
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
	        		for (Entity bi : ent.getNearbyEntities(4, 3, 4)) {
	        			if (bi instanceof Player) {
	        				if (Habilidade.getAbility(((Player) bi)) != "Jinbei") {
	        				((Player) bi).damage(2);
	        				API.darEfeito(((Player) bi), PotionEffectType.POISON, 5, 0);
	        				Vector v = new Vector();
	  	                  v.setX(1.5D);
	  	                  v.setY(0.8D);
	  	                  v.setZ(1.9D);
	  	                ((Player) bi).setVelocity(v);
	        			}
	        			}
	        		 event.setCancelled(true);
	        	}}
	        		else if (ent.hasMetadata("FALLBLAST2")) {
	        			throwRandomFirework(ent);
	        			(new BukkitRunnable() {
          	              public void run() {
          	            	  
          		        		for (Player p4: Bukkit.getOnlinePlayers()) {
          	        		        Particles.FLAME.display(0.0F, 0.0F, 0.0F, 0.25F, 100, ent.getLocation().add(0.0D, 2.0D, 1.0D), p4);
          	        	  }
          	              }
          	            }).runTaskLater((Plugin)Main.getInstance(), 5L);
	        		for (Entity bi : ent.getNearbyEntities(4, 4, 4)) {
	        			if (bi instanceof Player) {
if (!Habilidade.ContainsAbility(((Player) bi))) {
	return;
}
Player caster = Asteroid.caster.get(ent);
	            	          
	        				if (((Player) bi) != caster) {
	        				 ((Player) bi).setFireTicks(40);
	        				 ((Player) bi).damage(4, caster);
	        				}
	        				 ((Player) bi).playSound(((Player) bi).getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.RyuAbility")), 3.0F, 3.0F);
	        				 Asteroid.caster.remove(((Player) bi));
	        			}
	        		}
	        		 event.setCancelled(true);
	        	}
	        }
	        }
		public static void throwRandomFirework(Entity p) {
		    Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		    FireworkMeta fwm = fw.getFireworkMeta();
		    Random r = new Random();
		    FireworkEffect.Type type = FireworkEffect.Type.BALL_LARGE;
		    Color c1 = Color.RED;
		    Color c2 = Color.WHITE;
		    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
		    fwm.addEffect(effect);
		    fwm.setPower(0);
		    new BukkitRunnable() {
		        @Override
		        public void run() {
		          fw.detonate();
		        }
		    }.runTaskLater(Main.getInstance(), 2L);
		    //Then apply this to our rocket
		    fw.setFireworkMeta(fwm);
		    
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

	      	fallingBlock.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));

	      	fallingBlock2.setMetadata("FALLBLAST", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
	        FallingBlock ent =  fallingBlock;
	        fallingBlock.setVelocity(p.getLocation().getDirection().multiply(1.7F));
	        fallingBlock.setDropItem(false);
	        fallingBlock2.setVelocity(p.getLocation().getDirection().multiply(1.4F));
	        fallingBlock2.setDropItem(false);
            (new BukkitRunnable() {

				@Override
				public void run() {
					loc.put(fallingBlock, fallingBlock.getLocation());
				}


            }).runTaskLater(Main.getInstance(), 40L);
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
