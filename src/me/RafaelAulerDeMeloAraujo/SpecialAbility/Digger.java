package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Digger implements Listener {
  public int delayInTicks = 30;
  
  public int diggerBlock = Material.DIRT.getId();
  
  public int goDownY = 5;
  
  public int goSideways = 5;
  
  public String messageAfterPlaced = ChatColor.RED + "placed the egg. Run!";
  
  public String nameOfItem = "Mining Mole";
  
  public List<Block> blocks = new ArrayList<>();
  
  public HashMap<Block, Material> mat = new HashMap<>();
  
  public HashMap<Block, Byte> dat = new HashMap<>();
  
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @EventHandler
  public void onPlace(final BlockPlaceEvent event) {
    Player p = event.getPlayer();
    if (Habilidade.getAbility(p) == "Digger") {
      if (Cooldown.add(p)) {
        event.setCancelled(true);
        API.sendMessageCooldown(p);
      } else {
        final Block b = event.getBlock();
        if (API.isInRegion(p)) {
  		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
  		  return;
  	  }
        event.getPlayer().sendMessage(this.messageAfterPlaced);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), 
            new Runnable() {
              public void run() {
                for (Location loc : BlockUtils.circle(
                    b.getLocation(), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -1.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -2.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -3.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -4.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -5.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                for (Location loc : BlockUtils.circle(b
                    .getLocation().add(0.0D, -6.0D, 0.0D), 5, false)) {
                  Block block = loc.getBlock();
                  Digger.this.blocks.add(block);
                  Digger.this.mat.put(block, block.getType());
                  Digger.this.dat.put(block, Byte.valueOf(block.getData()));
                  block.setType(Material.AIR);
                  Particles.CLOUD.display(0.0F, 0.0F, 0.0F, 0.0F, 1, loc, 
                      20.0D);
                } 
                (new BukkitRunnable() {
                    @SuppressWarnings("deprecation")
					public void run() {
                      for (Block b : blocks) {
                        b.setType(mat.get(b));
                        b.setData(dat.get(b));
                      } 
                    }
                  }).runTaskLater((Plugin)Main.getInstance(), 200L);
              }
            }, 10l);
        Cooldown.add(p, Main.kits.getInt("DiggerCooldown"));
        event.setCancelled(true);
      }  
  }
}
}