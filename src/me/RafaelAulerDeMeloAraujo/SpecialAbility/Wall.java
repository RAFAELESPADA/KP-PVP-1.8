package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Wall implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  public static List<Block> blocks = new ArrayList<>();
  
  public static HashMap<Block, Material> material = new HashMap<>();
  
  public static HashMap<Block, Byte> data = new HashMap<>();
  
  @EventHandler
  public void onMinion(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.COBBLE_WALL && Habilidade.getAbility(p) == "Wall")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (p.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y") - 2) {
    		  p.sendMessage("DO NOT USE THIS ON SPAWN!");
				return;
			 }
        e.setCancelled(true);
        p.updateInventory();
        Cooldown.add(p, Main.kits.getInt("WallCooldown"));
        Location loca = p.getEyeLocation().add(0.0D, 4.0D, 0.0D);
        p.teleport(loca);
        blocks.add(loca.getBlock());
        material.put(loca.getBlock(), loca.getBlock().getType());
        data.put(loca.getBlock(), Byte.valueOf(loca.getBlock().getData()));
        loca.getBlock().setType(Material.COBBLESTONE);
        BlockIterator blocksToAdd = new BlockIterator(loca, 0.0D, 100);
        while (blocksToAdd.hasNext()) {
          final Location loc = blocksToAdd.next().getLocation();
          (new BukkitRunnable() {
              int i = 256;
              
              public void run() {
                this.i--;
                Block b = loc.add(0.0D, -1.0D, 0.0D).getBlock();
                if (this.i == 0 || b.getType() == Material.BEDROCK) {
                  cancel();
                  (new BukkitRunnable() {
                      public void run() {
                        for (Block b : Wall.blocks) {
                          b.setType(Wall.material.get(b));
                          b.setData(((Byte)Wall.data.get(b)).byteValue());
                        } 
                      }
                    }).runTaskLater(Main.getInstace(), 600L);
                } else if (b.getType() == Material.AIR) {
                  Wall.blocks.add(b);
                  Wall.material.put(b, b.getType());
                  Wall.data.put(b, Byte.valueOf(b.getData()));
                  b.setType(Material.COBBLESTONE);
                } 
              }
            }).runTaskTimer(Main.getInstace(), 0L, 1L);
        } 
      }
  }
}
