package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Prisoner implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @EventHandler
  public void onPrisoner(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.IRON_FENCE && 
      Habilidade.getAbility(p) == "Prisoner")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
      API.sendMessageCooldown(p);
      } else {
        
        Cooldown.add(p, Main.kits.getInt("PrisonerCooldown"));
        e.setCancelled(true);
        p.updateInventory();
        Snowball h = (Snowball)p.launchProjectile(Snowball.class);
        Vector velo1 = p.getLocation().getDirection().normalize().multiply(1.88D);
        h.setVelocity(velo1);
        h.setMetadata("prison", (MetadataValue)new FixedMetadataValue((Plugin)Main.getInstace(), Boolean.valueOf(true)));
      }  
  }
  

  @EventHandler
  public void dano(final EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
      Snowball s = (Snowball)e.getDamager();
      if (s.hasMetadata("prison") && 
        s.getShooter() != e.getEntity()) {
        e.getEntity().teleport(e.getEntity().getLocation().add(0.0D, 2.0D, 0.0D));
        for (Location loc : BlockUtils.sphere(e.getEntity().getLocation(), 3, true)) {
          final Material m = loc.getBlock().getType();
          final byte data = loc.getBlock().getData();
          loc.getBlock().setType(Material.GLASS);
          Particles.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.25F, 10, loc, 50.0D);
          (new BukkitRunnable() {
              public void run() {
                loc.getBlock().setType(m);
                loc.getBlock().setData(data);
                Particles.FLAME.display(0.3F, 0.3F, 0.3F, 0.25F, 10, e.getEntity().getLocation(), 50.0D);
              }
            }).runTaskLater((Plugin)Main.getInstance(), 120L);
        } 
              }
         
      if (s.hasMetadata("prison") && 
    	        s.getShooter() != e.getEntity()) {
        for (Location loc2 : BlockUtils.sphere(e.getEntity().getLocation(), 2, false)) {
          final Material m = loc2.getBlock().getType();
          final byte data = loc2.getBlock().getData();
          loc2.getBlock().setType(Material.LAVA);
          (new BukkitRunnable() {
              public void run() {
                  loc2.getBlock().setType(Material.AIR);
                Particles.FLAME.display(0.3F, 0.3F, 0.3F, 0.25F, 10, e.getEntity().getLocation(), 50.0D);
              }
          }).runTaskLater((Plugin)Main.getInstance(), 90L);
       
        (new BukkitRunnable() {
            public void run() {
            	loc2.getBlock().setType(m);
                loc2.getBlock().setData(data); }
          }).runTaskLater((Plugin)Main.getInstance(), 100L);
      } 
       
     
  }}}}


