package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Flash implements Listener {
  public static HashMap<String, Integer> cd = new HashMap<>();
  
  @EventHandler
  public void on(PlayerInteractEvent e) {
    final Player p = e.getPlayer();
    if (e.getPlayer().getItemInHand().getType() == Material.REDSTONE_TORCH_ON && Habilidade.getAbility(p) == "Flash")
      if (Cooldown.add(p)) {
        p.updateInventory();
      API.sendMessageCooldown(p);
      } else {
        p.updateInventory();
        p.sendMessage(ChatColor.GREEN + "ZUUUUMMMM!!");
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 15));
        Cooldown.add(p, Main.kits.getInt("FlashCooldown"));
      } 
  }
}