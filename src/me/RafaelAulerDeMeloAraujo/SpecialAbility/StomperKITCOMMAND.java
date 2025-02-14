/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;

import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class StomperKITCOMMAND
/*    */   implements CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public StomperKITCOMMAND(Main main)
/*    */   {
/* 25 */     this.main = main;
/* 26 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 32 */     Player p = (Player)sender;
/*    */     
/* 34 */     if (cmd.getName().equalsIgnoreCase("stomper"))
/*    */     {
/*    */ 
/* 37 */       if (!p.hasPermission("kitpvp.kit.stomper"))
/*    */       {
/* 39 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
/* 40 */         return true;
/*    */       }
/* 42 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 44 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 45 */         return true;
/*    */       }
/* 47 */       if (Habilidade.ContainsAbility(p)) {
/* 48 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 49 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 50 */         return true;
/*    */       }
if (Main.kits.getBoolean("StomperDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Stomper kit is disabled, sorry");
	return true;
}
/* 52 */       p.getInventory().clear();
/* 53 */       
/* 57 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
/* 58 */       ItemMeta sopas = sopa.getItemMeta();
/* 59 */       sopas.setDisplayName("§6Soup");
/* 60 */       sopa.setItemMeta(sopas);
/* 61 */       ItemStack especial = new ItemStack(Material.DIAMOND);
/* 62 */       ItemMeta especial2 = especial.getItemMeta();
/* 63 */       especial2.setDisplayName("§9Boost");
/* 64 */       especial.setItemMeta(especial2);
/*    */       
/* 66 */       
/*    */       
/* 79 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Stomper").replace("&", "§"));
/* 80 */       Habilidade.setAbility(p, "Stomper");
/* 81 */       
/* 82 */       
/*    */       
/*    */ 
/*    */ API.give(p);
if (!Main.getInstance().getConfig().getBoolean("DisableStomperItem")) {
/* 82 */       p.getInventory().setItem(1, especial);
}
RTP.TeleportArenaRandom(p);
/* 86 */       for (int i = 0; i <= 34; i++) {
/* 87 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 88 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 89 */         TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Stomper"));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 94 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\StomperKITCOMMAND.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
