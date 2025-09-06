package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.main.AntiDeathDrop;
import me.RafaelAulerDeMeloAraujo.main.Main;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.util.WaveCooldown2;





public class API
{
  
    public static String NomeServer;

    public static String fimcooldown;
    /*     */ private static me.RafaelAulerDeMeloAraujo.ScoreboardManager.WaveAnimation waveAnimation;
    private static String text = "";
	  public static String monkCooldownMessage = "§c§bYou cannot use your ability for more: §5 %s seconds!";
	  public String monkedMessage = "§cYou use your ability !";
	  public static int cooldownmonk = 15;
	  public static boolean sendThroughInventory = true;
	  public static transient HashMap<ItemStack, Long> monkStaff = new HashMap<ItemStack, Long>();
	
	public static ArrayList<String> admin = new ArrayList<String>();
	public static ArrayList<String> report = new ArrayList<String>();
	
	public static ArrayList<String> used = new ArrayList<String>();
	public static ArrayList<String> warp = new ArrayList<String>();
	
	public static ArrayList<String> Velotrol2 = new ArrayList<String>();
	public static ArrayList<String> freeze = new ArrayList<String>();
	public static ArrayList<String> freezing = new ArrayList<String>();
	
	public static HashMap<Player, String> kit = new HashMap<Player, String>();
    
    public static void MensagemCooldown(final Player p) {
    	sendMessageCooldown(p);
        p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("KitCooldown").replace("&", "§")).replace("%time%", String.valueOf(Cooldown.cooldown(p))));
    }
   
    	   static {
    	       
    	        API.NomeServer = Main.getInstance().getConfig().getString("Prefix").replace("&", "§");
    	        API.fimcooldown = Main.getInstance().getConfig().getString("Prefix").replace("&", "§") + (Main.messages.getString("KitCooldownEnd").replace("&", "§"));
    	    
    	    
    	    
    	   }

    	   public static void BuildScore(final Player p) {
    		   if (Main.getInstance().getConfig().getBoolean("EnableTABScoreAutomaticIntegration")) { 
    			   if (!Bukkit.getPluginManager().getPlugin("TAB").isEnabled()) {
    				   return;
    			   }
    			    TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(p.getUniqueId());
    			    //do something
    			
    		   ScoreboardManager sb = TabAPI.getInstance().getScoreboardManager();
    		   List<String> lines = new ArrayList();
    		   lines.add("§e");
    		   lines.add(Main.messages.getString("ScoreBoardKit").replace("&", "§") + Habilidade.getAbility(p));
    		   lines.add(Main.messages.getString("ScoreBoardKills").replace("&", "§") + AntiDeathDrop.GetKills(p));
               lines.add(Main.messages.getString("ScoreBoardDeaths").replace("&", "§") + AntiDeathDrop.GetDeaths(p));
               WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
       		int ks = Sun8oxData.getPvp().getKillstreak();
               lines.add(Main.messages.getString("ScoreBoardKS").replace("&", "§") + ks);
               lines.add(Main.messages.getString("ScoreBoardCoins").replace("&", "§") + Coins.getCoins(p));
               lines.add(Main.messages.getString("ScoreBoardXP").replace("&", "§") + XP.getXP(p));
               lines.add(Main.messages.getString("ScoreBoardLevel").replace("&", "§") + Level.getLevel(p));
               lines.add(Main.messages.getString("ScoreBoardPlayers").replace("&", "§") + Join.game.size());
               lines.add(Main.messages.getString("ScoreBoardNeedXP").replace("&", "§") + Level.getXPToLevelUp(p));
               lines.add("§f");
    		   Scoreboard score = sb.createScoreboard("kppvp", Main.messages.getString("ScoreBoardTitle").replace("&", "§"), lines);
    		   sb.showScoreboard(tabPlayer, score);
    		
    	   }
    	   }
    	   public static void sopa(final Player p) {
    		   ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
    		   /* 50 */       ItemMeta sopas = sopa.getItemMeta();
    		   /* 51 */       sopas.setDisplayName("§6Soup");
    		   /* 52 */       sopa.setItemMeta(sopas);
    		   for (int i = 0; i <= 34; i++) {
    				/* 76 */         p.getInventory().addItem(new ItemStack[] { sopa });
    			}
   	    }
	    public static void tirarArmadura(final Player p) {
	        p.getInventory().setHelmet(new ItemStack(Material.AIR));
	        p.getInventory().setChestplate(new ItemStack(Material.AIR));
	        p.getInventory().setLeggings(new ItemStack(Material.AIR));
	        p.getInventory().setBoots(new ItemStack(Material.AIR));
	    }
	    public static void ferro(final Player p) {
	        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
	        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
	    }
	    protected boolean hasCooldown(Player player) {
	        return WaveCooldown2.hasCooldown(player, "Kit");
	    }

	    protected boolean hasCooldown(Player player, String cooldown) {
	        return WaveCooldown2.hasCooldown(player, cooldown);
	    }

	    protected boolean inCooldown(Player player, String cooldown) {
	        return WaveCooldown2.inCooldown(player, cooldown);
	    }

	    protected boolean inCooldown(Player player) {
	        return WaveCooldown2.inCooldown(player, "Kit");
	    }

	    protected static void sendMessageCooldown(Player player) {
	    	WaveCooldown2.sendMessage(player, "Kit");
	    }

	    protected void sendMessageCooldown(Player player, String cooldown) {
	     WaveCooldown2.sendMessage(player, cooldown);
	    }

	    protected void addCooldown(Player player, String cooldownName, long time) {
	        if (WaveCooldown2.hasCooldown(player, cooldownName)) {
	            WaveCooldown2.removeCooldown(player, cooldownName);
	        }
	        WaveCooldown2.addCooldown(player, new net.wavemc.core.util.WaveCooldownAPI(cooldownName, time));
	    }

	    protected static void addCooldown(Player player, long time) {
	        if (WaveCooldown2.hasCooldown(player, "Kit")) {
	            WaveCooldown2.removeCooldown(player, "Kit");
	        }
	        WaveCooldown2.addCooldown(player, new net.wavemc.core.util.WaveCooldownAPI("Kit", time));
	    }

	    protected void addItemCooldown(Player player, ItemStack item, String cooldownName, long time) {
	        if (WaveCooldown2.hasCooldown(player, cooldownName)) {
	            WaveCooldown2.removeCooldown(player, cooldownName);
	        }
	        WaveCooldown2.addCooldown(player, new net.wavemc.core.util.ItemCooldown(item, cooldownName, time));
	    }

	    public static ItemStack darArmadura(final Material material, final Color cor) {
	        final ItemStack item = new ItemStack(material);
	        final LeatherArmorMeta itemm = (LeatherArmorMeta)item.getItemMeta();
	        itemm.setColor(cor);
	        item.setItemMeta((ItemMeta)itemm);
	        return item;
	    }
	    
		  public static void darEfeito(final Player p, final PotionEffectType tipo, final int duracao, final int level) {
		        p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
		    }
			public static String cor(String s) {
				return s.replace("&", "§");
			} 

public static void tirarEfeitos(final Player p) {
    p.removePotionEffect(PotionEffectType.ABSORPTION);
    p.removePotionEffect(PotionEffectType.BLINDNESS);
    p.removePotionEffect(PotionEffectType.CONFUSION);
    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
    p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    p.removePotionEffect(PotionEffectType.HARM);
    p.removePotionEffect(PotionEffectType.HEAL);
    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
    p.removePotionEffect(PotionEffectType.HUNGER);
    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    p.removePotionEffect(PotionEffectType.INVISIBILITY);
    p.removePotionEffect(PotionEffectType.JUMP);
    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    p.removePotionEffect(PotionEffectType.POISON);
    p.removePotionEffect(PotionEffectType.REGENERATION);
    p.removePotionEffect(PotionEffectType.SATURATION);
    p.removePotionEffect(PotionEffectType.SLOW);
    p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

    p.removePotionEffect(PotionEffectType.INVISIBILITY);
    p.removePotionEffect(PotionEffectType.SPEED);
    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
    p.removePotionEffect(PotionEffectType.WEAKNESS);
    p.removePotionEffect(PotionEffectType.WITHER);
}
public static void give2(Player p)
{
	if (!Main.getInstance().getConfig().getBoolean("DisableCompassItem")) {
		
   ItemStack marrom21 = new ItemStack(Material.COMPASS);
   ItemMeta marrom211 = marrom21.getItemMeta();
   marrom211.setDisplayName("§eCompass");
   List<String> itemlorem11 = new ArrayList();
   itemlorem11.add("§cFind enemies near you!");
   marrom211.setLore(itemlorem11);
   marrom21.setItemMeta(marrom211);
   p.getInventory().setItem(8, marrom21); 
	}
	if (!Main.getInstance().getConfig().getBoolean("DisableRecraftItem")) {
  ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
  ItemMeta vermelho2 = vermelho.getItemMeta();
  vermelho2.setDisplayName("§cRed Mushroom");
  vermelho.setItemMeta(vermelho2);
  
  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
  ItemMeta marrom2 = marrom.getItemMeta();
  marrom2.setDisplayName("§eBrown Mushroom");
  marrom.setItemMeta(marrom2);
  
  ItemStack item = new ItemStack(Material.BOWL, 64);
  ItemMeta item2 = item.getItemMeta();
  item2.setDisplayName("§7Bowl");
  @SuppressWarnings({ "unchecked", "rawtypes" })
	List<String> itemlore = new ArrayList();
  itemlore.add("§4Make soups with that bowls!");
  item2.setLore(itemlore);
  item.setItemMeta(item2);
  p.getInventory().setItem(14, vermelho);
  p.getInventory().setItem(15, marrom);
  p.getInventory().setItem(13, item);
	}
}
public static void give(Player p)
{
	if (!Main.getInstance().getConfig().getBoolean("DisableCompassItem")) {
		
   ItemStack marrom21 = new ItemStack(Material.COMPASS);
   ItemMeta marrom211 = marrom21.getItemMeta();
   marrom211.setDisplayName("§eCompass");
   List<String> itemlorem11 = new ArrayList();
   itemlorem11.add("§cFind enemies near you!");
   marrom211.setLore(itemlorem11);
   marrom21.setItemMeta(marrom211);
   p.getInventory().setItem(8, marrom21); 
	}
	if (!Main.getInstance().getConfig().getBoolean("DisableRecraftItem")) {
  ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
  ItemMeta vermelho2 = vermelho.getItemMeta();
  vermelho2.setDisplayName("§cRed Mushroom");
  vermelho.setItemMeta(vermelho2);
  
  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
  ItemMeta marrom2 = marrom.getItemMeta();
  marrom2.setDisplayName("§eBrown Mushroom");
  marrom.setItemMeta(marrom2);
  
  ItemStack item = new ItemStack(Material.BOWL, 64);
  ItemMeta item2 = item.getItemMeta();
  item2.setDisplayName("§7Bowl");
  @SuppressWarnings({ "unchecked", "rawtypes" })
	List<String> itemlore = new ArrayList();
  itemlore.add("§4Make soups with that bowls!");
  item2.setLore(itemlore);
  item.setItemMeta(item2);
  p.getInventory().setItem(14, vermelho);
  p.getInventory().setItem(15, marrom);
  p.getInventory().setItem(13, item);
	}
  if (Habilidade.getAbility(p) == "Fisherman") {
	  return;
  }
  if (!Main.getInstance().getConfig().getBoolean("FullIron") && Habilidade.getAbility(p) != "Tank") {
	  ItemStack capacete0 = new ItemStack(Material.AIR);
		p.getInventory().setHelmet(capacete0);
	/* 64 */       p.getInventory().setChestplate(capacete0);
	/* 65 */       p.getInventory().setLeggings(capacete0);
	/* 66 */       p.getInventory().setBoots(capacete0);
  }
  else if (Habilidade.getAbility(p) == "Cactus" || Habilidade.getAbility(p) == "Airman" || Habilidade.getAbility(p) == "Pyro" || Habilidade.getAbility(p) == "Jumper" || Habilidade.getAbility(p) == "Spiderman" && Main.getInstance().getConfig().getBoolean("FullIron")) {
	  ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   /* 48 */       dima.setItemMeta(souperaa);
	                  
	   p.getInventory().setItem(0, dima);
	   return;
  }
  else if (Habilidade.getAbility(p) == "Wasp") {
	  ItemStack sword7 = new ItemStack(Material.BLAZE_ROD);
	  /* 609 */       sword7.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 8);
	  /* 610 */       p.getInventory().setItem(0, sword7);
	  return;
  }
  else if (Habilidade.getAbility(p) == "PvP" && Main.getInstance().getConfig().getBoolean("FullIron")) {
	  ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   dima.addEnchantment(Enchantment.DAMAGE_ALL, 2);
	   /* 48 */       dima.setItemMeta(souperaa);
	                  
	   p.getInventory().setItem(0, dima);
	   return;
  }
  else if (Habilidade.getAbility(p) == "PvP" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
	  ItemStack dima = new ItemStack(Material.IRON_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   dima.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	   /* 48 */       dima.setItemMeta(souperaa);
	                  
	   p.getInventory().setItem(0, dima);
	   return;
  }
	   else if (Habilidade.getAbility(p) == "Cactus" || Habilidade.getAbility(p) == "Airman" || Habilidade.getAbility(p) == "Jumper" || Habilidade.getAbility(p) == "Wasp" || Habilidade.getAbility(p) == "Spiderman" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
		   ItemStack capacete0 = new ItemStack(Material.AIR);
			p.getInventory().setHelmet(capacete0);
		/* 64 */       p.getInventory().setChestplate(capacete0);
		/* 65 */       p.getInventory().setLeggings(capacete0);
		/* 66 */       p.getInventory().setBoots(capacete0);
	   }
	   else  if (Habilidade.getAbility(p) == "Pyro" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
	  p.getInventory().clear();
	   ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
	   /*  48 */     ItemMeta sopas1 = sopa.getItemMeta();
	   /*  49 */     sopas1.setDisplayName("§6Soup");
	   /*  50 */     sopa.setItemMeta(sopas1);
	   for (Object bow = p.getActivePotionEffects().iterator(); ((Iterator)bow).hasNext();) { PotionEffect effect = (PotionEffect)((Iterator)bow).next();
	   /* 329 */         p.removePotionEffect(effect.getType());
	   /*     */       }
	   /* 331 */       ItemStack sword3 = new ItemStack(Material.STONE_SWORD);
	   /* 332 */       sword3.addEnchantment(Enchantment.KNOCKBACK, 2);
	   /* 333 */       sword3.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	   /* 334 */       ItemStack flecha = new ItemStack(Material.ARROW, 64);
	   /* 335 */       sword3.addEnchantment(Enchantment.FIRE_ASPECT, 1);
	   /* 336 */       Object bow1 = new ItemStack(Material.BOW);
	   /* 337 */       ((ItemStack)bow1).addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	   ((ItemStack)bow1).addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
	   /* 338 */       ((ItemStack)bow1).addEnchantment(Enchantment.ARROW_FIRE, 1);
	   /* 339 */       ItemStack capacete0 = new ItemStack(Material.AIR);
		p.getInventory().setHelmet(capacete0);
	/* 64 */       p.getInventory().setChestplate(capacete0);
	/* 65 */       p.getInventory().setLeggings(capacete0);
	/* 66 */       p.getInventory().setBoots(capacete0);
	   /* 340 */     Bukkit.getConsoleSender().sendMessage("SOMEONE CHOOSED KIT PYRO WHEN FULL IRON IS SET TO OFF!");
	   /* 341 */       p.getInventory().addItem(new ItemStack[] { (ItemStack)bow1 });
	   /* 342 */       p.getInventory().addItem(new ItemStack[] { flecha });
	   /* 343 */       for (int i = 0; i <= 33; i++) {
	   /* 344 */         p.getInventory().addItem(new ItemStack[] { sopa });
	   /*     */       }
  }
	    if (Habilidade.getAbility(p) != "Basic" && Habilidade.getAbility(p) != "Viking" && Habilidade.getAbility(p) != "Wasp" && Habilidade.getAbility(p) != "PvP" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
	   ItemStack dima = new ItemStack(Material.STONE_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   /* 48 */       dima.setItemMeta(souperaa);
	   Bukkit.getConsoleSender().sendMessage("Giving Stone Sword to " + p.getName() + "because he chooses the kit: " + Habilidade.getAbility(p));
	                  p.getInventory().setItem(0, dima);
	   }
  else if (Habilidade.getAbility(p) != "Basic" && Habilidade.getAbility(p) != "Viking" && Habilidade.getAbility(p) != "Wasp" && Habilidade.getAbility(p) != "PvP"  && Main.getInstance().getConfig().getBoolean("FullIron")) {
	   ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   /* 48 */       dima.setItemMeta(souperaa);
	                  Bukkit.getConsoleSender().sendMessage("Giving Diamond Sword to " + p.getName() + "because he chooses the kit: " + Habilidade.getAbility(p));
	   p.getInventory().setItem(0, dima);
	   }
   else if (Habilidade.getAbility(p) == "Viking" && Main.getInstance().getConfig().getBoolean("FullIron")) {
	   ItemStack dima = new ItemStack(Material.DIAMOND_AXE);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cAxe");
	   /* 48 */       dima.setItemMeta(souperaa);
	                  dima.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	                  p.getInventory().setItem(0, dima);
   }
	                  else if (Habilidade.getAbility(p) == "Viking" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
	                	  ItemStack dima = new ItemStack(Material.STONE_AXE);
	               	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	               	   /* 47 */       souperaa.setDisplayName("§cAxe");
	               	   /* 48 */       dima.setItemMeta(souperaa);
	               	                 
	               	p.getInventory().setItem(0, dima);
	   }
	                  else if (Habilidade.getAbility(p) == "Basic" && Main.getInstance().getConfig().getBoolean("FullIron")) {
   ItemStack dima = new ItemStack(Material.DIAMOND_SWORD);
   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
   /* 47 */       souperaa.setDisplayName("§cSword");
   /* 48 */       dima.setItemMeta(souperaa);
                  dima.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                  p.getInventory().addItem(new ItemStack[] { dima });
   }
   else if (Habilidade.getAbility(p) == "Basic" && !Main.getInstance().getConfig().getBoolean("FullIron")) {
	   ItemStack dima = new ItemStack(Material.STONE_SWORD);
	   /* 46 */       ItemMeta souperaa = dima.getItemMeta();
	   /* 47 */       souperaa.setDisplayName("§cSword");
	   /* 48 */       dima.setItemMeta(souperaa);
	                  dima.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	                  p.getInventory().addItem(new ItemStack[] { dima });
	   }
   
   if (Main.getInstance().getConfig().getBoolean("FullIron") && Habilidade.getAbility(p) != "Tank") {
   ItemStack capacete0 = new ItemStack(Material.IRON_HELMET);
	/*    */       
	/* 57 */       ItemStack peitoral0 = new ItemStack(Material.IRON_CHESTPLATE);
	/*    */       
	/* 59 */       ItemStack calca0 = new ItemStack(Material.IRON_LEGGINGS);
	/*    */       
	/* 61 */       ItemStack Bota0 = new ItemStack(Material.IRON_BOOTS);
	/*    */       
	/* 63 */       p.getInventory().setHelmet(capacete0);
	/* 64 */       p.getInventory().setChestplate(peitoral0);
	/* 65 */       p.getInventory().setLeggings(calca0);
	/* 66 */       p.getInventory().setBoots(Bota0);
   }
	else if (!Main.getInstance().getConfig().getBoolean("FullIron") && Habilidade.getAbility(p) != "Tank"){
		ItemStack capacete0 = new ItemStack(Material.AIR);
		p.getInventory().setHelmet(capacete0);
	/* 64 */       p.getInventory().setChestplate(capacete0);
	/* 65 */       p.getInventory().setLeggings(capacete0);
	/* 66 */       p.getInventory().setBoots(capacete0);
   }
	else if (Main.getInstance().getConfig().getBoolean("FullIron") && Habilidade.getAbility(p) == "Tank"){
		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		/* 399 */       p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		/* 400 */       p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		/* 401 */       p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		}
	else if (!Main.getInstance().getConfig().getBoolean("FullIron") && Habilidade.getAbility(p) == "Tank"){
		p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		/* 399 */       p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		/* 400 */       p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		/* 401 */       p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
		}

}
}
