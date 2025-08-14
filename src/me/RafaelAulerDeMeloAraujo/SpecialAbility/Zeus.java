package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
      e.getAction().name().contains("RIGHT") && 
      Habilidade.getAbility(p) == "Zeus")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
        Location location = p.getEyeLocation();
        final BlockIterator blocksToAdd = new BlockIterator(
            location, 0.0D, 20);
        (new BukkitRunnable() {
            
        	Location blockToAdd;
            public void run() {
              if (blocksToAdd.hasNext()) {
                this.blockToAdd = blocksToAdd.next().getLocation();
                for (Player plays : Bukkit.getOnlinePlayers()) {
                  for (String games : Join.game) {
                  Player games2 = Bukkit.getPlayer(games); 
                  if (games2 != null) {
                  if (games2.getLocation().distance(this.blockToAdd) < 2.0D && 
                    plays != p) {
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    cd.put(blockToAdd, blockToAdd.getBlock().getData());
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    p.getWorld().strikeLightning(this.blockToAdd);
                    this.blockToAdd.getBlock().setData((byte)cd.get(this.blockToAdd));
                    cancel();
                  } 
                  }
                  }} 
                Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 
                    0.0F, 0.0F, 10, this.blockToAdd, 20.0D);
                p.getWorld().playEffect(this.blockToAdd, 
                    Effect.STEP_SOUND, 
                    Material.DIAMOND_BLOCK, 20);
                p.getWorld().playEffect(this.blockToAdd, 
                    Effect.STEP_SOUND, 
                    Material.DIAMOND_BLOCK, 20);
              } else {
                cancel();
                p.getWorld().strikeLightning(this.blockToAdd);

                cd.put(blockToAdd, blockToAdd.getBlock().getData());
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
  p.getWorld().strikeLightning(this.blockToAdd);
                p.getWorld().strikeLightning(this.blockToAdd);
                this.blockToAdd.getBlock().setData((byte)cd.get(this.blockToAdd));
              }            
            }
          }).runTaskTimer((Plugin)Main.getInstance(), 0L, 1L);
       
        e.setCancelled(true);
        p.updateInventory();
        Cooldown.add(p, Main.kits.getInt("ZeusCooldown")); 
  }
  }
}
