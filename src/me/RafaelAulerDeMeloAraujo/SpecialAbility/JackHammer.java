package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class JackHammer implements Listener {
	  
	  public static List<Block> blocks = new ArrayList<>();
	  
	  public static HashMap<Block, Material> material = new HashMap<>();
	  
	  public static HashMap<Block, Byte> data = new HashMap<>();
	  
	 @EventHandler
	  public void onJackhammer(PlayerInteractEvent e) {
	    final Player p = e.getPlayer();
	   
	    if (e.getAction().toString().contains("BLOCK") && e.getPlayer().getItemInHand().getType() == Material.STONE_AXE && Habilidade.getAbility(p) == "JackHammer")
	      if (Cooldown.add(p)) {
	        p.updateInventory();
	        API.sendMessageCooldown(p);
	      } else {
	    	  if (p.getLocation().getY() > Main.getInstance().getConfig().getInt("Spawn.Y")) {
					return;
				 }
	    	  if (API.isInRegion(p)) {
	    		  p.sendMessage(ChatColor.RED + "Leave the NO PVP Zone to use this kit!");
	    		  return;
	    	  }
	        p.updateInventory();
	       Cooldown.add(p, Main.kits.getInt("JackHammerCooldown"));
	        final Location loc = e.getClickedBlock().getLocation();
	        blocks.add(e.getClickedBlock());
	        material.put(e.getClickedBlock(), e.getClickedBlock().getType());
	        data.put(e.getClickedBlock(), Byte.valueOf(e.getClickedBlock().getData()));
	        e.getClickedBlock().setType(Material.AIR);
	        (new BukkitRunnable() {
	            int i = 256;
	            
	            public void run() {
	              this.i--;
	              Block b = loc.add(0.0D, -1.0D, 0.0D).getBlock();
	              if (this.i == 0 || b.getType() == Material.BEDROCK) {
	                cancel();
	                (new BukkitRunnable() {
	                    public void run() {
	                      for (Block b : blocks) {
	                        b.setType(material.get(b));
	                        b.setData(((Byte)data.get(b)).byteValue());
	                      } 
	                    }
	                  }).runTaskLater(Main.getInstace(), 200L);
	              } else {
	                blocks.add(b);
	               material.put(b, b.getType());
	               data.put(b, Byte.valueOf(b.getData()));
	                b.setType(Material.AIR);
	                b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
	              } 
	            }
	          }).runTaskTimer(Main.getInstance(), 0L, 1L);
	      } 
	  }
	}


