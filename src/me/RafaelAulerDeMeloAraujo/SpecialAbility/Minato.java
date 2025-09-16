package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Minato implements Listener {
  
  @EventHandler
  public void onNaruto(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.ARMOR_STAND && Habilidade.getAbility(p) == "Minato")
      if (Cooldown.add(p)) {
        e.setCancelled(true);
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (API.isInRegion(p)) {
    		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
    		  return;
    	  }
        Cooldown.add(p, Main.kits.getInt("MinatoCooldown"));
        Bot clone = new Bot();
        clone.setName(p.getName());
        clone.setInvincible(false);
        clone.summon(p.getLocation(), true);
        Bot.ownedBot.put(p.getName(), clone);
        clone.setGuardienFor(p);
        e.setCancelled(true);
      }  
  }
}

