package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Asteroid implements Listener {
public static HashMap<Entity, Location> loc = new HashMap<>();

@EventHandler
public void onAsteriod(PlayerInteractEntityEvent e) {
  final Player p = e.getPlayer();
  if (e.getPlayer().getItemInHand().getType() == Material.OBSIDIAN && Habilidade.getAbility(p) == "Asteroid")
    if (Cooldown.add(p)) {
      e.setCancelled(true);
      p.updateInventory();
      API.sendMessageCooldown(p);
    } else {
     Cooldown.add(p, Main.kits.getInt("AsteroidCooldown"));
      e.setCancelled(true);
      p.updateInventory();
      p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 100));
      @SuppressWarnings("deprecation")
	final FallingBlock ent = p.getWorld().spawnFallingBlock(e.getRightClicked().getLocation().add(0.0D, 10.0D, 0.0D), 
          Material.OBSIDIAN, (byte)0);
      FallingBlock fallingBlock = ent;
      fallingBlock.setDropItem(false);
      loc.put(fallingBlock, fallingBlock.getLocation());
      (new BukkitRunnable() {
          public void run() {
            if (ent.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType() == Material.AIR) {
              Asteroid.loc.put(ent, ent.getLocation());
              Particles.FLAME.display(0.1F, 0.1F, 0.1F, 0.0F, 40, ent.getLocation(), 50.0D);
              Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.0F, 100, ent.getLocation(), 50.0D);
            } else {
              Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.25F, 100, 
                  Asteroid.loc.get(ent), 50.0D);
              Particles.DRIP_LAVA.display(0.0F, 0.0F, 0.0F, 0.25F, 100, Asteroid.loc.get(ent), 
                  50.0D);
              Particles.LAVA.display(0.0F, 0.0F, 0.0F, 0.25F, 50, Asteroid.loc.get(ent), 
                  50.0D);
              (new BukkitRunnable() {
                  public void run() {
                    p.getWorld().createExplosion(Asteroid.loc.get(ent), 2.6F);
                  }
                }).runTaskLater(Main.getInstance(), 1L);
              for (Entity en : ent.getNearbyEntities(15.0D, 15.0D, 15.0D)) {
            	  if (!(en instanceof Player)) {
            		  return;
            	  }
                if (en != p && en instanceof Player)
                  en.setFireTicks(80); 
                	Player peni = (Player)en;
                	peni.damage(8, p);
              } 
              ent.remove();
              cancel();
            } 
          }
        }).runTaskTimer(Main.getInstance(), 0L, 1L);
    }  
}
}