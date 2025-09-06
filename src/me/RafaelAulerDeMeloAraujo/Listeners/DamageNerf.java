package me.RafaelAulerDeMeloAraujo.Listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.Main;

public class DamageNerf implements Listener {


/*     */   @EventHandler
/*     */   public void viper(EntityDamageByEntityEvent e) {
/*  39 */     if (((e.getDamager() instanceof Player)) && 
/*  40 */       ((e.getEntity() instanceof LivingEntity)))
/*     */     {
	
/*  43 */       Player p = (Player)e.getDamager();


/*  44 */       if (Main.getInstance().getConfig().getBoolean("EnableDamageNerf")) {
/*     */       {
    if (!Join.game.contains(p.getName())) {
    	return;
    }
    e.setDamage(e.getDamage() / Main.getInstance().getConfig().getDouble("DamageNerfAmount"));
}
}
}
}
}