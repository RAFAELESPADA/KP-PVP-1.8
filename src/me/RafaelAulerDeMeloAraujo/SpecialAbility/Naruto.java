/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.Listeners.ServerTimerEvent;
/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Naruto
/*     */   implements CommandExecutor, Listener
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Naruto(Main main)
/*     */   {
/*  38 */     this.main = main;
/*  39 */     plugin = main; }
/*     */   
/*  41 */   public static HashMap<String, Long> cooldown = new HashMap();
/*  42 */   private static final HashMap<Player, String> Armadura = null;
/*     */   
/*     */   @EventHandler
/*     */   public void interact(PlayerInteractEvent e)
/*     */   {
/*  47 */     Player p = e.getPlayer();

/*  48 */     if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
/*  49 */       (Habilidade.getAbility(p) == "Naruto") && 
/*  50 */       (p.getItemInHand().getType() == Material.NETHER_STAR))
/*     */     {

/*  52 */       if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
/*     */       {
/*  54 */         e.setCancelled(true);
/*  55 */         p.updateInventory();
/*     */          for (final Entity pertos : p.getNearbyEntities(Main.kits.getDouble("TimelordRadius"), Main.kits.getDouble("TimelordRadius"), Main.kits.getDouble("TimelordRadius"))) {
	if (pertos instanceof Player) {
		Player p2 = ((Player)pertos);
		p2.playSound(p2.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.NarutoAbility")), 1.0F, 1.0F);
		  /*  62 */       
	}
}
/*  57 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§") + ChatColor.RED + Main.messages.getString("NarutoUse").replace("&", "§")));
API.darEfeito(p, PotionEffectType.REGENERATION, Main.kits.getInt("NarutoRegenTime"), Main.kits.getInt("NarutoRegenAmplifier"));
API.darEfeito(p, PotionEffectType.SPEED, Main.kits.getInt("NarutoSpeedTime"), Main.kits.getInt("NarutoSpeedAmplifier"));
API.darEfeito(p, PotionEffectType.INCREASE_DAMAGE, Main.kits.getInt("NarutoStrenghtTime"), Main.kits.getInt("NarutoStrenghtAmplifier"));
/*  61 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.NarutoAbility")), 1.0F, 1.0F);
/*  62 */         cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Main.kits.getLong("NarutoCooldown"))));
/*  63 */         return;
/*     */       }
/*  65 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§") + ChatColor.RED + " You need to wait " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " seconds to use your ability again."));
/*     */     }
}
/*     */   
/*     */   
/*     */ 
/*     */ 
/*     */     @EventHandler
public void onUpdate(ServerTimerEvent event) {
	if (event.getCurrentTick() % 2 > 0)
		return;
	for (Player ghosts : Bukkit.getOnlinePlayers())
	if (Habilidade.getAbility(ghosts) == "Naruto") {
	if (cooldown.containsKey(ghosts.getName())) {	
		Location l = ghosts.getLocation();
		Location l2 = new Location(ghosts.getWorld(), ghosts.getLocation().getX(), ghosts.getLocation().getY() + 1, ghosts.getLocation().getZ());
		l.getWorld().playEffect(l, Effect.SMOKE, 20);
		;
		l.getWorld().playEffect(l, Effect.SMOKE, 20);
		l2.getWorld().playEffect(l, Effect.SMOKE, 20);
		;
		l2.getWorld().playEffect(l, Effect.SMOKE, 20);
	}
	}
}

/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
/*     */   {
/*  75 */     Player p = (Player)sender;
/*  76 */     if (command.getName().equalsIgnoreCase("naruto"))
/*     */     {
/*  78 */       if (!Join.game.contains(p.getName()))
/*     */       {
/*  80 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to do choose this kit!");
/*  81 */         return true;
/*     */       }

/*     */    
/*  83 */       if (Habilidade.ContainsAbility(p)) {
/*  84 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/*  85 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/*  86 */         return true;
/*     */       }
/*  88 */       if (!p.hasPermission("kitpvp.kit.naruto")) {
/*  89 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
/*  90 */         return true;
/*     */       }
if (Main.kits.getBoolean("NarutoDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Naruto kit is disabled, sorry");
	return true;
}
/*     */       
/*     */ 
/*     */ 
/*  95 */       p.getInventory().clear();
/*     */       
/*     */ 
/*  98 */ 
/* 102 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
/* 103 */       ItemMeta sopas = sopa.getItemMeta();
/* 104 */       sopas.setDisplayName("§6Soup");
/* 105 */       sopa.setItemMeta(sopas);
/* 106 */       ItemStack especial = new ItemStack(Material.NETHER_STAR);
/* 107 */       ItemMeta especial2 = especial.getItemMeta();
/* 108 */       especial2.setDisplayName("§cNine Tails Mode!");
/* 109 */       especial.setItemMeta(especial2);
/*     */       
/* 111 */       
/* 118 */      
/* 119 */       
/* 120 */     
/*     */       
/* 125 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Naruto").replace("&", "§"));
/* 126 */       Habilidade.setAbility(p, "Naruto");
/*     */       API.give(p);
/*     */ RTP.TeleportArenaRandom(p);
/*     */ 
/* 130 */       for (int i = 0; i <= 34; i++) {
/* 131 */         p.getInventory().addItem(new ItemStack[] { sopa });
/*     */       }
p.getInventory().setItem(1, especial);
/*     */     }
/*     */     
/* 135 */     TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Naruto"));
/*     */     
/* 137 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Naruto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
