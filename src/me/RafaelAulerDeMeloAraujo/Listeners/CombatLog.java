package me.RafaelAulerDeMeloAraujo.Listeners;





import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.Menu;

public class CombatLog implements Listener
{
    public static HashMap<Player, Player> emcombate;
    
    static {
        CombatLog.emcombate = new HashMap<Player, Player>();
    }
    public static boolean emCombate(final Player p) {
        return CombatLog.emcombate.containsKey(p);
    }
    
    public static String statuscombat(final Player p) {
        String nome = "";
        if (emCombate(p)) {
            nome = "§aYes";
        }
        else if (!emCombate(p)) {
            nome = "§cNo §4";
        }
        return nome;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void aocombatlog(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player hitter = (Player)e.getDamager();
            if (Habilidade.ContainsAbility(p) && !CombatLog.emcombate.containsKey(p) && !CombatLog.emcombate.containsKey(hitter) && Join.game.contains(p.getName())) {
            	if (e.isCancelled()) {
            		return;
            	}
                CombatLog.emcombate.put(p, hitter);
                CombatLog.emcombate.put(hitter, p);
                hitter.sendMessage(API.NomeServer +  Main.messages.getString("GetInCombatWith").replace("&", "§").replace("%player%", p.getName()));
                p.sendMessage(API.NomeServer +  Main.messages.getString("GetInCombatWith").replace("&", "§").replace("%player%", hitter.getName()));
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.instance, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        CombatLog.emcombate.remove(p);
                        CombatLog.emcombate.remove(hitter);
                        hitter.sendMessage(API.NomeServer +  Main.messages.getString("CombatLogExpired").replace("&", "§").replace("%player%", p.getName()));
                        p.sendMessage(API.NomeServer +  Main.messages.getString("CombatLogExpired").replace("&", "§").replace("%player%", hitter.getName()));
                    }
                }, 20L * Main.getInstance().getConfig().getInt("CombatLogTimer"));
            }
        }
    }
    
    
    @EventHandler
    public void aosair(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (CombatLog.emcombate.containsKey(p)) {
            p.setHealth(0.0);
            p.teleport(p.getWorld().getSpawnLocation());
            CombatLog.emcombate.remove(p);
            for (String p3 : Join.game) {
				if (p3 != null) {
					Player p2 = Bukkit.getPlayer(p3);	
					if (p2 != null) {
				p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.Respawn")), 4.0F, 4.0F);
			}
            }
				
		   }
    		Menu.sendToGame(API.NomeServer + Main.messages.getString("PlayerLoggetOutCombatLog").replace("&", "§").replace("%player%", p.getName()));
            
        }
        
    }
    @EventHandler
    public void aomorrer(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (CombatLog.emcombate.containsKey(p)) {
            CombatLog.emcombate.remove(p);
        }
    }
    

    }
