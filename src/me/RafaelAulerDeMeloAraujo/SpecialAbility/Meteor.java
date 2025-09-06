package me.RafaelAulerDeMeloAraujo.SpecialAbility;




import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.wavemc.core.util.WaveCooldown;

public class Meteor implements Listener {

	ArrayList<String> danometeor = new ArrayList();
	ArrayList<Player> subiu = new ArrayList();
	@EventHandler
	/*     */   public void BotaStomper2(EntityDamageEvent e)
	/*     */   {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (Main.kits.getBoolean("MeteorDisabled")) {
			return;
		}
		Player p = (Player)e.getEntity();
		if (Habilidade.getAbility(p) != "Meteor") {
			return;
		}
		if (!danometeor.contains(e.getEntity().getName())) {
			return;
		}
		if (Cooldown.add(p)) {
			return;
		}
		if (!(e.getCause() == DamageCause.FALL)) {
			return;
		}
		e.setCancelled(true);
		for (Entity ent : p.getNearbyEntities(5, 5, 5)) {
			if (!(ent instanceof Player)) {
				return;
			}
				Player p2 = (Player)ent;
				if (Habilidade.getAbility(p) != "Meteor") {
					return;
				}
				  else if (p2.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y")) {
						return;
					 }
				p2.damage(10, p);
				Location location = p2.getLocation();
				 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.RyuAbility")), 1.0F, 1.0F);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				 location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
				p2.getWorld().strikeLightning(p2.getLocation());
				p2.setFireTicks(140);
				Cooldown.addCooldown(p , 40);
				p2.sendMessage(ChatColor.RED + "You got harmed by a Meteor conjured by " + p.getName() + " !");
				danometeor.remove(e.getEntity().getName());
			}
		}
	@EventHandler
	/*     */   public void BotaStomper2(PlayerToggleSneakEvent e)
	/*     */   {
		Player p = e.getPlayer();
		if (!subiu.contains(p)) {
			return;
		}
		if (Main.kits.getBoolean("MeteorDisabled")) {
			return;
		}
		if (Habilidade.getAbility(p) != "Meteor") {
			return;
		}
		if (p.isOnGround()) {
			return;
		}
		if ((e.getPlayer().getItemInHand().getType() != Material.FIREBALL)) {
			return;
		}
		int l = (int)p.getEyeLocation().getDirection().multiply(3.0).add(new Vector(0, 0, 0)).getY();
		if(p.getLocation().getPitch() >= -90 && p.getLocation().getPitch() <= -10) {
	        return;
	    }
		if (Cooldown.add(p))
		/*     */       {
            API.MensagemCooldown(p);
		/*  92 */         return;
		/*     */       } p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.RyuAbility")), 1.0F, 1.0F);
		
		/*  76 */       p.setVelocity(p.getEyeLocation().getDirection().multiply(2.0).add(new Vector(0, 0, 0)));
		danometeor.add(p.getName());
		subiu.remove(p);

	/* 102 */       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	/* 106 */           if (Habilidade.getAbility(p) != "Meteor") {
		return;
		}
	 p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.Respawn")), 1.0F, 1.0F);
	/*     */         }
	/*     */         

	}

	/* 109 */       , 800L);
	/*     */     }
	private static double calcMotY(double targetY) {
	    double y = 0;
	    double motY = 0;
	    while(y <= targetY) {
	        motY /= 0.98;
	        motY += 0.08;
	        y += motY;
	    }
	    return motY;
	}
	@EventHandler
	/*     */   public void BotaStomper(PlayerInteractEvent e)
	/*     */   {
	/*  84 */     final Player p = e.getPlayer();
	/* 106 */           if (Habilidade.getAbility(p) != "Meteor") {
		return;
		}
	if (Main.kits.getBoolean("MeteorDisabled")) {
		return;
	}
	/*     */     
		if ((e.getPlayer().getItemInHand().getType() != Material.FIREBALL)) {
			return;
		}
	/*  87 */       e.setCancelled(true);
	/*  88 */       p.updateInventory();
	/*  89 */       if (Cooldown.add(p))
	/*     */       {
	/*  91 */         API.MensagemCooldown(p);
	/*  92 */         return;
	/*     */       }
	 else if (p.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y")) {
		 p.sendMessage(ChatColor.RED + "You cannot use the meteor on Spawn!");
			return;
		 }
		  if (WaveCooldown.has(p.getName(), "meteor")) {
			  p.sendMessage(ChatColor.RED + "Wait " + WaveCooldown.getTime(p.getName(), "meteor") +  " to use this again!");
			  return;
		  }
	WaveCooldown.create(p.getName(), "meteor", TimeUnit.SECONDS, 15);
	Bukkit.getScheduler().runTaskLater(Main.getInstance() , new Runnable() {
	    @Override
	    public void run() {
	   	WaveCooldown.delete(p.getName(), "meteor");
	        }
	    }
	, 15 * 20);
	/*  94 */      	 p.setVelocity(new Vector(0, calcMotY(16), 0));

	/* 107 */           p.sendMessage(ChatColor.RED + "PRESS SHIFT WHEN YOU ARE IN THE SKY!");
	/*  98 */       Location loc = p.getLocation();
    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);
	Location location = p.getLocation();
	for (final Entity pertos : p.getNearbyEntities(20, 20 , 20)) {
		  if (pertos instanceof Player) {
			    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);
	}

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);

	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);
	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);

	/*     */         }
	/* 109 */       }, 20L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);
	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);

	/*     */         }
	/* 109 */       }, 40L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 60L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.BLAZE_SHOOT, 15);
	/*     */         }
	/* 109 */       }, 80L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 100L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 120L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {

	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);
	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 130L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 140L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 155L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 15);
	/*     */         }
	/* 109 */       }, 160L);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	    p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(Main.getInstance().getConfig().getString("Sound.AnchorHit")), 4.0F, 4.0F);

	/*     */         }
	/* 109 */       }, 200L);
	}

	subiu.add(p);
	} 

	/*     */   }
	
