package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ import org.bukkit.inventory.meta.ItemMeta;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
import me.RafaelAulerDeMeloAraujo.main.RTP;
/*    */ 
/*    */ public class DoctorK implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public DoctorK(Main main)
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
/* 29 */     if (cmd.getName().equalsIgnoreCase("kdoctor"))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 34 */       if (Habilidade.ContainsAbility(p)) {
/* 35 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.KitUse").replace("&", "§"));
/* 36 */         p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(this.main.getConfig().getString("Sound.KitUse")), 1.0F, 1.0F);
/* 37 */         return true;
/*    */       }
if (Main.kits.getBoolean("DoctorDisabled")) {
	p.sendMessage(API.NomeServer + ChatColor.RED + "The Doctor kit is disabled, sorry");
	return true;
}
/* 39 */       if (!Join.game.contains(p.getName()))
/*    */       {
/* 41 */         p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + " §eYou are not in kitpvp to choose this kit!");
/* 42 */         return true;
/*    */       }
/* 44 */       p.getInventory().clear();
/* 45 */       
/*    */       
/*    */ 
/* 67 */       Habilidade.setAbility(p, "Doctor");
/* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Creeper").replace("&", "§"));
/*    */       
/* 70 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
/* 50 */       ItemMeta sopas = sopa.getItemMeta();
/* 51 */       sopas.setDisplayName("§6Soup");
/* 52 */       sopa.setItemMeta(sopas);
ItemStack especial = new ItemStack(Material.SHEARS);
/* 61 */       ItemMeta especial2 = especial.getItemMeta();
/* 62 */       especial2.setDisplayName("§cMister Doctor");
/* 63 */       especial.setItemMeta(especial2);
/*    */       API.give(p);
/*    */ RTP.TeleportArenaRandom(p);
/*    */ 
for (int i = 0; i <= 34; i++) {
	/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
}

/* 76 */         p.getInventory().setItem(1, especial );
/* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Creeper"));
/*    */       }
/*    */     
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

