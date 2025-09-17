package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Blink implements Listener {

	@EventHandler
	  public void onMinion(PlayerInteractEvent e) {
	    final Player p = e.getPlayer();
	    if (e.getPlayer().getItemInHand().getType() == Material.NETHER_STAR && 
	      Habilidade.getAbility(p) == "Blink")
	      if (Cooldown.add(p)) {
	        e.setCancelled(true);
	        p.updateInventory();
	      API.sendMessageCooldown(p);
	      } else {
	        e.setCancelled(true);
	        p.updateInventory();

	        Cooldown.add(p, Main.kits.getInt("BlinkCooldown"));
	        float pitch = p.getLocation().getPitch();
	        float yaw = p.getLocation().getYaw();
	        BlockIterator blocksToAdd = new BlockIterator(p.getEyeLocation(), 0.0D, 15);
	        while (blocksToAdd.hasNext()) {
	          Sound sound;
	          Location blockToAdd = blocksToAdd.next().getLocation();
	          Particles.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 8, blockToAdd, 50.0D);
	          try {
	            sound = Sound.valueOf("FIREWORK_LAUNCH");
	          } catch (IllegalArgumentException ey) {
	            sound = Sound.valueOf("ENTITY_FIREWORK_LAUNCH");
	          } 
	          p.getWorld().playSound(blockToAdd, sound, 1.0F, 3.0F);
	          p.teleport(blockToAdd);
	          e.getPlayer().teleport(new Location(p.getWorld(), blockToAdd.getX(), 
	                blockToAdd.getBlockY() + 1.1D, blockToAdd.getZ(), yaw, pitch));
	        } 
	        Particles.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.3F, 16, p.getLocation(), 26.0D);
	            }
	         
	  }
	}
