package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Mario implements Listener {
	  @EventHandler
	  public void dolphin(PlayerToggleSneakEvent e) {
	    final Player p = e.getPlayer();
	    if (p.isSneaking() && !p.isOnGround()) {
	      if (Habilidade.getAbility(p) == "Mario") {
	        for (int i = -3; i < 0; i++) {
	          Block b = p.getLocation().add(0.0D, i, 0.0D).getBlock();
	          if (b.getType() != Material.AIR)
	            return; 
	        } 
	        p.setVelocity(p.getLocation().getDirection().setY(-2.5D));
	        p.setFallDistance(-100.0F);
	        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 50));
	        (new BukkitRunnable() {
	            public void run() {
	              if (p.isOnGround()) {
	                cancel();
	                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 50));
	                p.getWorld().createExplosion(p.getLocation(), 2.3F);
	                p.setVelocity(new Vector());
	                p.setFallDistance(0.0F);
	              } 
	            }
	          }).runTaskTimer(Main.getInstance(), 0L, 1L);
	      } 
	    } 
	  }
}