/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ 
/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;

/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class FiremanCMD implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public FiremanCMD(Main main)
/*    */   {
/* 20 */     this.main = main;
/* 21 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*    */   {
/* 27 */     Player p = (Player)sender;
/*    */     
/* 29 */     if (cmd.getName().equalsIgnoreCase("kfireman"))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 34 */       if (Habilidade.ContainsAbility(p)) {
/* 35 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 36 */         p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 37 */         return true;
/*    */       }
/* 39 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 42 */         return true;
/*    */       }
/* 44 */       p.getInventory().clear();
/* 45 */       ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
/* 46 */       ItemMeta souperaa = dima.getItemMeta();
/* 47 */       souperaa.setDisplayName("§cSword");
/* 48 */       dima.setItemMeta(souperaa);
/* 49 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
/* 50 */       ItemMeta sopas = sopa.getItemMeta();
/* 51 */       sopas.setDisplayName("§6Soup");
/* 52 */       sopa.setItemMeta(sopas);
/*    */       
/*    */ 
/* 55 */       
/* 67 */       Habilidade.setAbility(p, "Fireman");
/* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Fireman").replace("&", "§"));
/*    */       
/* 70 */       
/*    */       
/*    */ 
/*    */ 
/*    */ API.give(p);
RTP.TeleportArenaRandom(p);
/* 75 */       for (int i = 0; i <= 34; i++) {
/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
/* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Fireman"));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\Basic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
