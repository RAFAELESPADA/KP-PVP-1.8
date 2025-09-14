package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class HedgeHog implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  @EventHandler
  public void dano(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Arrow)))
    {
     Arrow s = (Arrow)e.getDamager();
      if (s.hasMetadata("BUY")) {
        e.setDamage(e.getDamage() + 2);
      }
    }
    }
  @EventHandler
  public void onHedgehog(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    
    if (p.getItemInHand().getType() == Material.ARROW && 
      Habilidade.getAbility(p) == "HedgeHog")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (API.isInRegion(p)) {
    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
    		  return;
    	  }
          Cooldown.add(p, Main.kits.getInt("HedgeHogCooldown"));
        e.setCancelled(true);
        p.updateInventory();
        Location loc = p.getEyeLocation();
        for (int pitch = 0; pitch >= -90; pitch -= 15) {
          for (int yaw = 180; yaw >= -180; yaw -= 15) {
            loc.setYaw(yaw);
            loc.setPitch(pitch);
            final Arrow arrow = (Arrow)p.launchProjectile(Arrow.class);
            arrow.setVelocity(loc.getDirection().multiply(1.2D));
            arrow.setKnockbackStrength(5);
            arrow.setMetadata("BUY", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
            
            (new BukkitRunnable() {
                public void run() {
                  arrow.remove();
                }
              }).runTaskLater((Plugin)Main.getInstance(), 100L);
          } 
        }
      }
  }
}
