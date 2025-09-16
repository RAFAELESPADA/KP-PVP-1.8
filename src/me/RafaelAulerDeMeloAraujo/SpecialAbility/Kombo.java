package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Kombo implements Listener {
  private static HashMap<String, Integer> inCombo = new HashMap<>();
  
  private static HashMap<String, Integer> increase = new HashMap<>();
  
  @EventHandler
  public void dombo(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
      final Player d = (Player)e.getDamager();
      if (Habilidade.getAbility(d) == "Kombo")
        if (inCombo.containsKey(d.getName())) {
          int hits = ((Integer)inCombo.get(d.getName())).intValue();
          hits++;
          inCombo.put(d.getName(), Integer.valueOf(hits));
          if (hits == 3) {
            if (!increase.containsKey(d.getName()))
              increase.put(d.getName(), Integer.valueOf(0)); 
            int in = ((Integer)increase.get(d.getName())).intValue();
            in++;
            increase.put(d.getName(), Integer.valueOf(in));
            inCombo.remove(d.getName());
            d.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, ((Integer)increase.get(d.getName())).intValue()));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                  public void run() {
                    if (!inCombo.containsKey(d.getName()))
                      increase.remove(d.getName()); 
                  }
                },  100L);
          } 
        } else {
          inCombo.put(d.getName(), Integer.valueOf(1));
        }  
      if (inCombo.containsKey(((HumanEntity)e.getEntity()).getName())) {
        API.tirarEfeitos(d);
        increase.remove(d.getName());
        inCombo.remove(d.getName());
      } 
    } 
  }
}

