package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.Listeners.ServerTimerEvent;

public class Leo implements Listener {
	  private static HashMap<String, Integer> walktime = new HashMap<>();
	  

@EventHandler
public void onSprint(ServerTimerEvent e) {
  for (Player p : Bukkit.getOnlinePlayers()) {
    if (Habilidade.getAbility(p) == "Leopard") {
      if (p.isSprinting()) {
        if (!walktime.containsKey(p.getName())) {
          walktime.put(p.getName(), Integer.valueOf(3));
        } else {
          walktime.put(p.getName(), Integer.valueOf(((Integer)walktime.get(p.getName())).intValue() - 1));
          if (((Integer)walktime.get(p.getName())).intValue() <= 0) {
            API.darEfeito(p, PotionEffectType.SPEED, 5, 1);
            walktime.put(p.getName(), Integer.valueOf(5));
          } 
        } 
        continue;
      } 
		Location l = p.getLocation();

		l.getWorld().playEffect(l, Effect.SPELL, 20);
      API.tirarEfeitos(p);
      walktime.put(p.getName(), Integer.valueOf(5));
    } 
  } 
}
@EventHandler
public void onSprinat(ServerTimerEvent e) {

	  for (Player p : Bukkit.getOnlinePlayers()) {
    if (walktime.containsKey(p.getName())) {
if (Habilidade.getAbility(p) != "Leopard") {
	 walktime.remove(p.getName());
}
    }
	  }
    }
@EventHandler
public void onSprinaft(PlayerDeathEvent e) {

	  if (Join.game.contains(e.getEntity().getName()))
	 walktime.remove(e.getEntity().getName());
}


}