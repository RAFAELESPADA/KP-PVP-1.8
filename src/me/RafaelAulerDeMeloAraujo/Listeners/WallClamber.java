/*    */ package me.RafaelAulerDeMeloAraujo.Listeners;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ 
/*    */ import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
/*    */ 
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.BlockUtils;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Particles;
/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class WallClamber implements org.bukkit.event.Listener
/*    */ {
/*    */   private Main main;
/*    */   Main plugin;
/* 23 */   List<Player> FlyCooldown = new ArrayList();
/* 24 */   List<Player> TogglePlayersCooldown = new ArrayList();
/*    */   
/*    */   public WallClamber(Main main) {
/* 27 */     this.main = main;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onWall(PlayerInteractEvent e)
/*    */   {
/* 33 */     Player p = e.getPlayer();
/* 34 */     Action a = e.getAction();
if (!Habilidade.getAbility(p).equalsIgnoreCase("Spiderman")) {
	return;
}

/* 35 */     if ((a.equals(Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getType() == Material.DIAMOND_SWORD) || (p.getItemInHand().getType() == Material.STONE_SWORD)) {
	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  e.setCancelled(true);
		  return;
	}
/* 36 */       Vector v2 = p.getLocation().getDirection().multiply(0.0D).setY(1.0D);
/* 37 */       API.darEfeito(p, PotionEffectType.REGENERATION, 3, 1);
/* 38 */       
/* 39 */       p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Spiderman")), 500.0F, 500.0F);
/* 40 */       p.setVelocity(v2);
/*    */     }
/* 35 */     if ((a.equals(Action.LEFT_CLICK_AIR)) && (p.getItemInHand().getType() == Material.DIAMOND_SWORD) || (p.getItemInHand().getType() == Material.STONE_SWORD)) {
	  if (API.isInRegion(p)) {
		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
		  e.setCancelled(true);
		  return;
	}
	  if (Cooldown.add(p)) {
		  return;
	  }
	  Cooldown.add(p, 40);
	  Snowball h = (Snowball)p.launchProjectile(Snowball.class);
      Vector velo1 = p.getLocation().getDirection().normalize().multiply(1.88D);
      h.setVelocity(velo1);
      h.setMetadata("spideryokai", (MetadataValue)new FixedMetadataValue((Plugin)Main.getInstace(), Boolean.valueOf(true)));
    
/*    */   }

/*    */ }

@EventHandler
public void dano(final EntityDamageByEntityEvent e) {
  if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
    Snowball s = (Snowball)e.getDamager();
    if (s.hasMetadata("spideryokai") && 
      s.getShooter() != e.getEntity()) {
  	  if (API.isInRegion((Player) e.getEntity())) {
  		  e.setCancelled(true);
  		 Player si = (Player) s.getShooter();
  		 si.sendMessage("DONT USE THIS ON PLAYERS ON SPAWN!");
  		  return;
  	}
  	  for (Location b : BlockUtils.sphere(e.getEntity().getLocation(), 2, true)) {
  		final Material m = b.getBlock().getType();
        final byte data = b.getBlock().getData();
        b.getBlock().setType(Material.WEB);
        (new BukkitRunnable() {
            public void run() {
              b.getBlock().setType(m);
              b.getBlock().setData(data);
              Particles.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.25F, 10, b, 50.0D);
            }
          }).runTaskLater((Plugin)Main.getInstance(), 120L);
      } 
    }}}}


