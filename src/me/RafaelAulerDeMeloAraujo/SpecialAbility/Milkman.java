package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.helix.core.bukkit.item.ItemBuilder;

public class Milkman extends API implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
    	if (Habilidade.getAbility(event.getPlayer()) != "Milkman") {
    		return;
    	}
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "milkman")) {
        	return;
        }
        event.setCancelled(true);
        final Player p = event.getPlayer();
        if (inCooldown(p) && Habilidade.getAbility(event.getPlayer()) == "Milkman") {
            API.MensagemCooldown(p);
            return;
        }
       Cooldown.add(event.getPlayer(), Main.kits.getInt("MilkManCooldown"));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3 * 20, 0));
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3 * 20, 0));
        event.getPlayer().sendMessage("§aMilkman applied!");
    }
}