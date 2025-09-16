package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class Doctor implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @SuppressWarnings("deprecation")
@EventHandler
  public void doctor(PlayerInteractEntityEvent e) {
    if (e.getRightClicked() instanceof Player) {
      final Player p1 = e.getPlayer();
      if (e.getRightClicked() instanceof Player && 
        Habilidade.getAbility(p1) == "Doctor" && 
        p1.getInventory().getItemInHand().getType() == Material.SHEARS)
        if (Cooldown.add(p1)) {
          e.setCancelled(true);
          
       API.sendMessageCooldown(p1);
        } else {
          Sound sound;
          
          Player clicked = (Player)e.getRightClicked();
          if (API.isInRegion(p1)) {
    		  p1.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
    		  return;
    	  }
          try {
            sound = Sound.valueOf("ANVIL_USE");
          } catch (IllegalArgumentException ey) {
            sound = Sound.valueOf("BLOCK_ANVIL_USE");
          } 
          clicked.getWorld().playSound(clicked.getLocation(), sound, 1.0F, 2.0F);
          clicked.sendMessage(ChatColor.RED + "A player with kit Doctor cured you from your injuries!");
          p1.sendMessage(ChatColor.RED + "You healed the player " + clicked.getName());
          Cooldown.add(p1, 30);
         API.tirarEfeitos(p1);
         p1.setHealth(p1.getMaxHealth());
         API.darEfeito(p1, PotionEffectType.REGENERATION, 10, 1);
        }  
    } 
  }}

