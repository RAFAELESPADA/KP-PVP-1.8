package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Sasuke implements Listener {
  @EventHandler
  public void on(PlayerInteractEvent e) {
    final Player p = e.getPlayer();;
    if (e.getPlayer().getItemInHand().getType() == Material.ARROW && Habilidade.getAbility(p) == "Sasuke")
      if (Cooldown.add(p)) {
        p.updateInventory();
        API.sendMessageCooldown(p);
      } else {
    	  if (API.isInRegion(p)) {
    		  p.sendMessage("DO NOT USE THIS ON NO PVP ZONES!");
    		  return;
    	  }
        p.updateInventory();
       Cooldown.add(p, Integer.valueOf(Main.kits.getInt("SasukeCooldown")));
        Bot b = new Bot();
        int i = 0;
        for (Entity en : p.getNearbyEntities(20.0D, 20.0D, 20.0D)) {
          if (en instanceof Player && en != p) {
            i++;
            if (i > 0) {
              b.setShadow(p, (Player)en);
              b.setShadow(p, (Player)en);
              continue;
            } 
            p.sendMessage("§cThere are no players to attack for your shadow.");
          } 
        }
  }
  }}

