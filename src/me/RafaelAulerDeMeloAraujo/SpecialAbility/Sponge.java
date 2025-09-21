/*    */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*    */ 
/*    */ import java.util.ArrayList;

import org.bukkit.Bukkit;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;

/*    */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*    */ 
/*    */ public class Sponge
/*    */   implements Listener
/*    */ {
/*    */   private Main main;
/*    */   static Main plugin;
/*    */   
/*    */   public Sponge(Main main)
/*    */   {
/* 31 */     this.main = main;
/* 32 */     plugin = main;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 37 */   ArrayList<String> fall = new ArrayList<>();
/*    */   ArrayList<String> fall2 = new ArrayList<>();

public void Atirar(Player p) {
	
	double y = Main.getInstance().getConfig().getInt("SpongeBoostAmplifier");
	Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
	if (block.getType() == Material.SPONGE && Join.game.contains(p.getName())) {
		double height = Math.sqrt(2 * 0.08 * y);
		
	Vector vector = new Vector(p.getVelocity().getX(), height, p.getVelocity().getZ());

	this.fall.remove(p.getName());
	this.fall.add(p.getName());
	 p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.SpongeUse")), 4.0F, 4.0F);
	Vector vector2 = new Vector(p.getVelocity().getX(), 8, p.getVelocity().getZ());
	(new BukkitRunnable() {
        public void run() {
		p.setVelocity(vector);

		
        }}).runTaskLater((Plugin)Main.getInstance(), 3L);
	(new BukkitRunnable() {
        public void run() {
		p.setVelocity(vector2);
        }}).runTaskLater((Plugin)Main.getInstance(), 21L);
    }
	}

public void Atirar2(Player p) {
	final Location loc = p.getEyeLocation();

    final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
	Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
	if (block.getType() == Material.SLIME_BLOCK && Join.game.contains(p.getName())) {
		p.setVelocity(sponge);
	    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
		if (!this.fall2.contains(p.getName())) {
		this.fall2.add(p.getName());
	}
	}
}
@EventHandler
public void RemoverDan2o(EntityDamageEvent e) 
{
   if (!(e.getEntity() instanceof Player)) {
       return;
   }
   Player p = (Player) e.getEntity();
   if (e.getCause() == EntityDamageEvent.DamageCause.FALL && !Habilidade.ContainsAbility(p) && this.fall2.contains(p.getName()) && e.getEntity().getLocation().getY() < Main.plugin.getConfig().getInt("Spawn.Y") && Join.game.contains(p.getName()) && p.getWorld().equals(Bukkit.getWorld(Main.plugin.getConfig().getString("Spawn.World"))))  {
	   e.setCancelled(true);
	   /* 44 */       p.getInventory().clear();
	   /* 45 */       
	   /*    */       
	   /*    */ 
	   /* 67 */       Habilidade.setAbility(p, "Basic");
	   /* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Basic").replace("&", "§"));
	   /*    */       
	   /* 70 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
	   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
	   /* 51 */       sopas.setDisplayName("§6Soup");
	   /* 52 */       sopa.setItemMeta(sopas);
	   /*    */       API.give(p);
	   for (int i = 0; i <= 34; i++) {
	   	/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
	   }
	   /* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Basic"));
	   /*    */       }
	   /*    */     
   this.fall2.remove(p.getName());
   }
   
@EventHandler
private void Jumps(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	Atirar(p);
	Atirar2(p);
}
 
   @EventHandler
	public void RemoverDano(EntityDamageEvent e) 
	{
	   if (!(e.getEntity() instanceof Player)) {
           return;
       }
		Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
		{
			this.fall.remove(p.getName());
			e.setCancelled(true);

		}
		if (Habilidade.ContainsAbility(p) && this.fall2.contains(p.getName())) {
			Bukkit.getConsoleSender().sendMessage("PROTECTED " + p.getName() + " from fall damage!");
			this.fall2.remove(p.getName());
			e.setCancelled(true);
			 p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.SpongeUse")), 4.0F, 4.0F);
		}
		else if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall2.contains(p.getName())) 
		{
			if (!Habilidade.ContainsAbility(p)) {
				/* 44 */       p.getInventory().clear();
				   /* 45 */       
				   /*    */       
				this.fall2.remove(p.getName());
				   /*    */       e.setCancelled(true); 
				   /* 67 */       Habilidade.setAbility(p, "Basic");
				   /* 68 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + this.main.getConfig().getString("Message.Kit").replaceAll("%kit%", "Basic").replace("&", "§"));
				   /*    */       
				   /* 70 */       ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
				   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
				   /* 51 */       sopas.setDisplayName("§6Soup");
				   /* 52 */       sopa.setItemMeta(sopas);
				   /*    */       API.give(p);
				   for (int i = 0; i <= 34; i++) {
				   	/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
				   }
				   /* 77 */         me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI.sendTitle(p, Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(20), this.main.getConfig().getString("Title.KitTitle"), this.main.getConfig().getString("Title.KitSubTitle").replaceAll("%kit%", "Basic"));
				   /*    */}
		
		
			
		
		if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
		{
			this.fall.remove(p.getName());

			this.fall2.remove(p.getName());
		}
	}
	}
}
