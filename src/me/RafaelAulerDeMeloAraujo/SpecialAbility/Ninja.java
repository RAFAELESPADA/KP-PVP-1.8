/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
import java.util.concurrent.TimeUnit;

/*     */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ninja
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public Ninja(Main main)
/*     */   {
/*  38 */     this.main = main;
/*  39 */     plugin = main;
/*     */   }
/*     */   
/*  42 */   public static HashMap<String, Long> cooldownbk = new HashMap();
/*  43 */   public static HashMap<Player, Player> a = new HashMap();
/*     */   
/*     */   @EventHandler
/*     */   public void dano(EntityDamageByEntityEvent e)
/*     */   {
/*  48 */     if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player)))
/*     */     {
/*  50 */       Player p = (Player)e.getDamager();
/*  51 */       Player p2 = (Player)e.getEntity();
/*  52 */       if (Habilidade.getAbility(p) == "Ninja") {
/*  53 */         a.put(p, p2);
/*  54 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void shift(PlayerToggleSneakEvent e) {
/*  61 */     final Player p = e.getPlayer();
/*  62 */     Player p2 = (Player)a.get(p);
/*  63 */     if (Habilidade.getAbility(p) == "Ninja") {
	if (p2 == null) {
	return;
}
/*  64 */       if (p2.isDead()) {
/*  65 */         return;
/*     */       }
if (p2.getLocation().distance(p.getLocation()) > Main.kits.getDouble("NinjaErrorBlockNumber") && p2 != null) { 
	p.sendMessage(API.NomeServer + Main.messages.getString("NinjaError").replace("&", "§"));
	return;
}   
	/*  52 */       if ((!cooldownbk.containsKey(p.getName())) || (((Long)cooldownbk.get(p.getName())).longValue() <= System.currentTimeMillis() && p2 != null))
	/*     */       {
/*  71 */         p.teleport(p2.getLocation());
p.sendMessage(Main.messages.getString("NinjaTeleport").replace("&", "§").replace("%player%", p2.getName()));
/*  73 */         cooldownbk.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Main.kits.getLong("NinjaCooldown"))));
/*  80 */         return;
/*     */       }
}
}




/*     */   
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
/*     */   {
/*  89 */     Player p = (Player)sender;
/*  90 */     
/*  94 */     ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
/*  95 */     ItemMeta sopas = sopa.getItemMeta();
/*  96 */     sopas.setDisplayName("§6Soup");
/*  97 */     sopa.setItemMeta(sopas);
/*     */     
/*  99 */     
/*     */     
/*     */ 
/*     */ 
/* 109 */     if (cmd.equalsIgnoreCase("ninja")) {
/* 110 */       if (Habilidade.ContainsAbility(p)) {
/* 111 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 112 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 113 */         return true;
/*     */       }
/* 115 */       if (!Join.game.contains(p.getName()))
/*     */       {
/* 117 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 118 */         return true;
/*     */       }
/* 120 */       if (!p.hasPermission("kitpvp.kit.ninja")) {
/* 121 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", cmd));
/* 122 */         return true;
/*     */       }
/*     */       
/* 125 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Ninja").replace("&", "§"));
/* 126 */       p.setGameMode(GameMode.ADVENTURE);
/* 127 */       p.getInventory().clear();
/* 128 */       Habilidade.setAbility(p, "Ninja");
/*     */       
/* 130 */       
/*     */       API.give(p);
RTP.TeleportArenaRandom(p);
/* 137 */       for (int i = 0; i <= 34; i++) {
/* 138 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 139 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/*     */       }
/*     */     }
/* 142 */     TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Ninja"));
/*     */     
/*     */ 
/* 145 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Ninja.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
