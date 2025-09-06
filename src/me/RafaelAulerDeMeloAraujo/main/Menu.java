 package me.RafaelAulerDeMeloAraujo.main;

import java.io.IOException;
import java.util.ArrayList;
/*     */ import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
/*     */ 
/*     */ import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.Coins.XP;
import me.RafaelAulerDeMeloAraujo.Listeners.CombatLog;
import me.RafaelAulerDeMeloAraujo.Listeners.StatusGUI;
import me.RafaelAulerDeMeloAraujo.Listeners.UpdateUtil;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.Level;
import me.RafaelAulerDeMeloAraujo.ScoreboardManager.WaveAnimation;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.API;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Deshfire;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Gladiator;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
/*     */ import me.RafaelAulerDeMeloAraujo.SpecialAbility.Join;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent;
import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.X1.CustomChallenge;
import me.RafaelAulerDeMeloAraujo.X1.X1;
import me.RockinChaos.itemjoin.api.ItemJoinAPI;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.event.player.PlayerLoadEvent;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;


/*     */ public class Menu
/*     */   implements Listener , CommandExecutor
/*     */ {
/*     */   private Main main;
private static WaveAnimation waveAnimation;
private static String text = "";
private static ItemJoinAPI item; {
	if(Bukkit.getPluginManager().getPlugin("ItemJoin") != null) {
		item = new ItemJoinAPI();
	} else {
			item = null;
		}
	}
/*     */  public static ArrayList<String> has = new ArrayList();
/*     */   
/*     */   public Menu(Main main)
/*     */   {
/*  62 */     this.main = main;
/*     */   }
@EventHandler(priority = EventPriority.MONITOR)
/*     */   public void addtoTop2(PlayerJoinEvent e) {
	Player p = e.getPlayer();
	UpdateUtil updateChecker = new UpdateUtil(Main.getInstance(), true);
	if (p.isOp() && !Main.getInstance().getConfig().getBoolean("UpdateCheckerDisabled")) {
	switch (updateChecker.check()) {
	    case OUT_OF_DATE:
	        p.sendMessage("§c[KP-PVP] An update for KP-PVP (" + updateChecker.getNewVersion() + ") was found. Please update at: https://www.spigotmc.org/resources/kp-pvp-the-ultimate-kitpvp-plugin.50969/");
	        break;
	    case UP_TO_DATE:
	    	 p.sendMessage("§a[KP-PVP] You are on latest version");
	    default:
	        break;
	}
	}
	}

@EventHandler
/*  45 */   public void playerdeath(PlayerLevelUPEvent ev) { 
	   int playerLevel = Level.getLevel(ev.getPlayer());
	for (Integer level : Level.getXPAllLevels()) {
		if (playerLevel == level) {
				  for (String commands : Main.customization.getStringList("Levels.Levels." + playerLevel + ".commands")) {
				  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%player%", ev.getPlayer().getName()));
			}
		}
	}
}

/*     */   @EventHandler
/*  45 */   public void playerdeath(PlayerDeathEvent ev) throws IOException { 
	Player p = ev.getEntity();
Player k = p.getKiller();
boolean isCitizensNPC = p.hasMetadata("NPC");
double killstreak = XP.getXP(k);
/*  46 */     if ((p.getKiller() instanceof Player))
/*     */     {
/*     */ 
/*  49 */     
if (!Join.game.contains(k.getName())) {
	return;
}

WavePlayer Sun8oxData2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
if (isCitizensNPC && Main.getInstance().getConfig().getBoolean("BotsKillsAllowed")) {
	Sun8oxData2.getPvp().addKills(1);
	if (killstreak % Main.customization.getInt("XP-Required-To-LevelUP") == 0 && Level.getLevel(k) != 0) {
		sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
	}
	k.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
	XP.addXP(k, Main.customization.getInt("XPEarned-OnKill"));
	Coins.addCoins(k, Main.customization.getInt("Earned-Coins-Per-Kill"));               
	k.sendMessage("§a+" + Main.customization.getInt("XPEarned-OnKill") + "XP");
	k.sendMessage("§a+" + Main.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");
     WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData2);
}
  else if (!isCitizensNPC) {
	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(k.getName());
/*  52 */       p.sendMessage(API.NomeServer + "" + (Main.messages.getString("StreakDestroyed").replace("&", "§").replace("%player%", k.getName()))); 
/*  53 */       addtokillstreak(k);
API.tirarEfeitos(p);        
Sun8oxData.getPvp().addKills(1);
Sun8oxData2.getPvp().addDeaths(1);

API.BuildScore(p);

API.BuildScore(k);
Sun8oxData2.getPvp().setKillstreak(0);
if (killstreak % Main.customization.getInt("XP-Required-To-LevelUP") == 0 && Level.getLevel(k) != 0) {
	sendToGame(String.valueOf(API.NomeServer + Main.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
	PlayerLevelUPEvent helixPlayerDeathEvent = new me.RafaelAulerDeMeloAraujo.SpecialAbility.PlayerLevelUPEvent(
			k
	);
	Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent);
	
}
/*  64 */       int kills2 = Sun8oxData2.getPvp().getKillstreak();
if (kills2 >= 3) {
	broadcast(API.NomeServer + "" + Main.messages.getString("KillStreakLostBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills2)).replace("%player%", p.getName()).replace("%killer%", k.getName()) , p.getWorld());
}
if (Main.getInstance().getConfig().getBoolean("Commands-ON-KILL-Enabled")) {
	  for (String commands : Main.getInstance().getConfig().getStringList("Commands-Executed-On-Kill")) {
	  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%killer%", k.getName()).replace("%killed%", p.getName()));
}
}
if (Main.getInstance().getConfig().getBoolean("ThrowFireworksOnKill")) {
	throwRandomFirework(p);
}
p.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Death.Tell").replaceAll("%player%", k.getName())));
k.sendMessage(String.valueOf(API.NomeServer + Main.getInstace().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
XP.addXP(k, Main.customization.getInt("XPEarned-OnKill"));
XP.removeXP(p, Main.customization.getInt("XPLost-OnDeath"));
Coins.addCoins(k, Main.customization.getInt("Earned-Coins-Per-Kill"));               
Coins.removeCoins(p, Main.customization.getInt("Lost-Coins-Per-Death"));
p.sendMessage("§cYou died to " + k.getName());
k.sendMessage("§a+" + Main.customization.getInt("XPEarned-OnKill") + "XP");
k.sendMessage("§a+" + Main.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");
WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData);

		WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData2);

Bukkit.getConsoleSender().sendMessage("§e" + p.getName() + " (" +  ev.getEntityType() + ")" + " has been killed by " + k.getName() + " (" +  ev.getEntity().getKiller().getType() + ")" + " on kitpvp");
/*     */     }
  }
}

/*     */   
/*     
*/  

/*     */ 
/*     */   public static void addtokillstreak(Player killer)
/*     */   {
/*  61 */     String name = killer.getName();
/*  62 */     if (Join.game.contains(name))
/*     */     {

	WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
/*  64 */       int kills = Sun8oxData.getPvp().getKillstreak();
/*     */      Sun8oxData.getPvp().addKillstreak(1);
if (kills != 0) {
killer.sendMessage(API.NomeServer + "" + Main.messages.getString("KillStreakEarned").replace("&", "§").replace("%killstreak%", String.valueOf(kills)));
}
/*  69 */       if (kills % 3 == 0 && kills != 0) {
	broadcast(API.NomeServer + "" + Main.messages.getString("KillStreakBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills)).replace("%player%", name) , killer.getWorld());
/*  73 */         killer.sendMessage(API.NomeServer + "" + ChatColor.GOLD + "You have been awarded " + Main.customization.getDouble("KS-3") + ( kills * 3 ) + " Coins!");
/*  74 */         Coins.addCoins(killer, Main.customization.getInt("KS-3") + ( kills * 3 ));
/*     */       }
/*  76 */      
/*     */     
}
/*     */   }
/*     */ 

  public static void broadcast(String text, World w){
    for(Player p: w.getPlayers()){
        p.sendMessage(text);
    }
  }
public static void sendToGame(String message) {
    for(String player : Join.game) {
        if(player != null) {
            Player p = Bukkit.getPlayer(player);
            if (p != null) {
            p.sendMessage(message);
        }
        }
    }
    }
/*     */   @EventHandler
/*     */   public void onJoin(PlayerJoinEvent e) {
/*  67 */     Player p = e.getPlayer();
/*     */     
/*  70 */     Habilidade.removeAbility(p);
/*  71 */     Deshfire.Armadura.remove(p);
/*  72 */     Deshfire.Armadura2.remove(p);
/*  73 */     Deshfire.cooldownm.remove(p);
if (!Main.plugin.getConfig().getBoolean("bungeemode")) {
/*  74 */     Join.game.remove(p.getName());
}
/*  75 */     Cooldown.remove(p);
/*     */     
/*     */ 
/*     */ 
/*  79 */     WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(e.getPlayer().getName());
int ks = Sun8oxData.getPvp().getKillstreak();
Sun8oxData.getPvp().setKillstreak(0);

/*     */     
/*  84 */     if (this.main.getConfig().getString("JoinItem.JoinSound").equalsIgnoreCase("true"))
/*  85 */       p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Join")), 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void createButton(Material mat, Inventory inv, int Slot, String name, String lore) {
/*  89 */     ItemStack item =  new ItemStack(mat);
/*     */     
/*  91 */     ItemMeta meta = item.getItemMeta();
/*  92 */     meta.setDisplayName(name);
/*  93 */     meta.setLore(Arrays.asList(new String[] { lore }));
/*  94 */     item.setItemMeta(meta);
/*  95 */     inv.setItem(Slot, item);
/*     */   }
/*     */   @EventHandler
/*     */   public void onEvent(PlayerJoinEvent e)
/*     */   {
     Player p = e.getPlayer(); 
     if (!Main.plugin.getConfig().getBoolean("bungeemode")) {
	 return;
}
if (!Join.game.contains(p.getName())) {
/*  74 */     Join.game.add(p.getName());
}
new BukkitRunnable() {	
	@Override
		public void run() {
/*     */           if (!Join.game.contains(p)) {
	return;
}
/*     */ 	/*     */       

		}}.runTaskTimer(Main.getInstance(), 10 * 20L, 20L * Main.getInstance().getConfig().getInt("ScoreBoard-Interval-Update"));


		API.BuildScore(p);

	/*     */ 
	/* 200 */           World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/* 201 */           double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/* 202 */           double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/* 203 */           double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/* 204 */           Location lobby = new Location(w, x, y, z);


	/*     */ 
	/* 211 */           lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/* 212 */           lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/* 213 */           p.getInventory().clear();
	/*     */           
	/*     */ 
	/* 216 */           p.teleport(lobby);
	/*     */           
	/*     */ 
	/* 219 */           p.getInventory().clear();
	/* 220 */           p.getInventory().setArmorContents(null);
	/*  94 */       ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
	/*  96 */       ItemMeta kitsr2 = kitsr.getItemMeta();
	/*  97 */       kitsr2.setDisplayName(Main.messages.getString("KitsItemName").replace("&", "§"));
	/*  98 */       kitsr.setItemMeta(kitsr2);
	/*  95 */       ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
	/*  96 */       ItemMeta kits2 = kits.getItemMeta();
	/*  97 */       kits2.setDisplayName(Main.messages.getString("ShopItemName").replace("&", "§"));
	/*  98 */       kits.setItemMeta(kits2);
	/*  99 */       ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
	/* 100 */       ItemMeta st2 = st.getItemMeta();
	/* 101 */       st2.setDisplayName(Main.messages.getString("1v1ItemName").replace("&", "§"));
	/* 102 */       st.setItemMeta(st2);
	ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
	/* 227 */           ItemMeta stats2 = kits.getItemMeta();
	/* 228 */           stats2.setDisplayName(Main.messages.getString("StatsItemName").replace("&", "§"));
	/* 229 */           stats.setItemMeta(stats2);
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("StatsItemSlot"), stats);

	ItemStack stats1 = Main.getInstance().getConfig().getItemStack("ClickTestItem");
	/* 227 */           ItemMeta stats12 = stats1.getItemMeta();
	/* 228 */           stats12.setDisplayName(Main.messages.getString("ClickTestItemName").replace("&", "§"));
	/* 229 */           stats1.setItemMeta(stats12);
	ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
	/* 227 */           ItemMeta warp2 = warp.getItemMeta();
	/* 228 */           warp2.setDisplayName("§aWarps");
	/* 229 */           warp.setItemMeta(warp2);
	if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
		}
	ItemStack sair = Main.getInstance().getConfig().getItemStack("LeaveItem");
	/* 227 */           ItemMeta sair2 = sair.getItemMeta();
	/* 228 */           sair2.setDisplayName(Main.messages.getString("LeaveItemName").replace("&", "§"));
	/* 229 */           sair.setItemMeta(sair2);
	if (!Main.getInstance().getConfig().getBoolean("DisableLeaveItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("LeaveItemSlot"), sair);
	}
	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	/* 103 */     	if (!Main.getInstance().getConfig().getBoolean("DisableShop")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
		}

if (!Main.getInstance().getConfig().getBoolean("Disable1v1Item")) {
/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
/*     */       }       
	/*     */ 
	/* 107 */       p.updateInventory();	/*     */       
	if (Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
		 p.getInventory().clear();
		 if(Bukkit.getPluginManager().getPlugin("ItemJoin") != null){
		 item.getItems(p);
			}
	 }
	/* 107 */       p.updateInventory();
	/*     */           
	/*     */ 

	/*     */ 
	/* 235 */           p.setExp(0.0F);
	/* 236 */           p.setExhaustion(20.0F);
	/* 237 */           p.setFireTicks(0);
	/* 238 */           p.setFoodLevel(20000);
	/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), Main.getInstance().getConfig().getString("Title.JoinTitle"), Main.getInstance().getConfig().getString("Title.JoinSubTitle"));
	API.tirarEfeitos(p);
}
/*     */   
@EventHandler
/*     */   public void onLeave2(PlayerQuitEvent e)
/*     */   {
/* 117 */     Player p = e.getPlayer();
if (X1.inx1.contains(p)) {
	X1.sair1v1(p);
}
}
/*     */   @EventHandler
/*     */   public void onLeave(PlayerQuitEvent e)
/*     */   {
/* 117 */     Player p = e.getPlayer();
/*     */     if (Join.game.contains(p.getName())&& !Main.plugin.getConfig().getBoolean("bungeemode")) {
	/*     */ 
	/*     */ 
	/* 133 */    	/* 279 */       Habilidade.removeAbility(p);
	/* 280 */       Deshfire.Armadura.remove(p.getName());
	/* 281 */       Deshfire.Armadura2.remove(p.getName());
	/* 282 */       Deshfire.cooldownm.remove(p);
	/* 283 */       Join.game.remove(p.getName());
	/* 284 */       Join.game.remove(p.getName());
	/* 285 */       Join.game.remove(p.getName());
	/* 286 */       Join.game.remove(p.getName());
	/* 287 */       Join.game.remove(p.getName());
	/* 288 */       Join.game.remove(p.getName());
	/* 289 */       Join.game.remove(p.getName());
	/* 290 */       Join.game.remove(p.getName());
	/* 291 */       Join.game.remove(p.getName());
	/* 292 */       Join.game.remove(p.getName());
	/* 293 */       Join.game.remove(p.getName());
	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
	/* 295 */       Join.game.remove(p.getName());
	/* 296 */       Join.game.remove(p.getName());
	/* 297 */       Join.game.remove(p.getName());

	/*     */ 
	/*     */ /*     */ /*     */ if (TabAPI.getInstance().getScoreboardManager() != null) {
		   TabAPI.getInstance().getEventBus().register(PlayerLoadEvent.class, event -> {
			    TabPlayer tabPlayer = event.getPlayer();
		TabAPI.getInstance().getScoreboardManager().resetScoreboard(tabPlayer);
	});
	}
	/*     */ 
	/* 302 */       Cooldown.remove(p);
	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
	p.setScoreboard(Join.savescore.get(p.getName()));
	p.setLevel(Join.savelevel.get(p.getName()));
	p.setFoodLevel(Join.savehunger.get(p.getName()));
	p.setRemainingAir(Join.saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	API.tirarEfeitos(p);
}
/*     */   }
@EventHandler
/*     */   public void onEventt(PlayerJoinEvent e)
/*     */   {
     Player p = e.getPlayer(); 
     if (p.getGameMode() == GameMode.SPECTATOR) {
    	 p.setGameMode(GameMode.SURVIVAL);
     }
}
/*     */   
/*     */   @EventHandler
/*     */   public void onLeave(PlayerKickEvent e)
/*     */   {
	
/* 128 */     Player p = e.getPlayer();
/*     */     if (Join.game.contains(p.getName()) && !Main.plugin.getConfig().getBoolean("bungeemode")) {
	/*     */ 
	/*     */ 
	/* 133 */    	/* 279 */       Habilidade.removeAbility(p);
	/* 280 */       Deshfire.Armadura.remove(p.getName());
	/* 281 */       Deshfire.Armadura2.remove(p.getName());
	/* 282 */       Deshfire.cooldownm.remove(p);
	/* 283 */       Join.game.remove(p.getName());
	/* 284 */       Join.game.remove(p.getName());
	/* 285 */       Join.game.remove(p.getName());
	/* 286 */       Join.game.remove(p.getName());
	/* 287 */       Join.game.remove(p.getName());
	/* 288 */       Join.game.remove(p.getName());
	/* 289 */       Join.game.remove(p.getName());
	/* 290 */       Join.game.remove(p.getName());
	/* 291 */       Join.game.remove(p.getName());
	/* 292 */       Join.game.remove(p.getName());
	/* 293 */       Join.game.remove(p.getName());
	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
	/* 295 */       Join.game.remove(p.getName());
	/* 296 */       Join.game.remove(p.getName());
	/* 297 */       Join.game.remove(p.getName());

	/*     */ 
	/*     */ 
	/*     */ 
	/* 302 */       Cooldown.remove(p);
	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
	p.setScoreboard(Join.savescore.get(p.getName()));
	p.setLevel(Join.savelevel.get(p.getName()));
	p.setFoodLevel(Join.savehunger.get(p.getName()));
	p.setRemainingAir(Join.saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	API.tirarEfeitos(p);
}
/*     */   } 

/*     */ 

/*     */ 
/*     */   @EventHandler
/*     */   public void quickcommand(PlayerCommandPreprocessEvent e)
/*     */   {
/* 336 */     if ((e.getMessage().equalsIgnoreCase("/kitpvp kit")) && (Join.game.contains(e.getPlayer().getName()))) {
/* 337 */       e.setCancelled(true);
/* 338 */       Player p = e.getPlayer();
/* 339 */       p.chat("/kpkitmenu");
/*     */       
/* 341 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 343 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 344 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand1(PlayerCommandPreprocessEvent e)
/*     */   {
/* 352 */     if (e.getMessage().equalsIgnoreCase("/kitpvp kits")) {
/* 353 */       e.setCancelled(true);
/* 354 */       Player p = e.getPlayer();
/* 355 */       p.chat("/kpkitmenu");
/* 356 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 358 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 359 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand2(PlayerCommandPreprocessEvent e)
/*     */   {
/* 367 */     if (e.getMessage().equalsIgnoreCase("/kitpvp kitmenu")) {
/* 368 */       e.setCancelled(true);
/* 369 */       Player p = e.getPlayer();
/* 370 */       p.chat("/kpkitmenu");
/* 371 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 373 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 374 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void quickcommand3(PlayerCommandPreprocessEvent e)
/*     */   {
/* 382 */     if (e.getMessage().equalsIgnoreCase("/kpkits")) {
/* 383 */       e.setCancelled(true);
/* 384 */       Player p = e.getPlayer();
/* 385 */       p.chat("/kpkitmenu");
/*     */       
/*     */ 
/* 388 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 390 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 391 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   @EventHandler
/*     */   public void quickcommand3f(PlayerCommandPreprocessEvent e)
/*     */   {
/* 382 */     if (e.getMessage().equalsIgnoreCase("/kp kits")) {
/* 383 */       e.setCancelled(true);
/* 384 */       Player p = e.getPlayer();
/* 385 */       p.chat("/kpkitmenu");
/*     */       
/*     */ 
/* 388 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 390 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 391 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ @EventHandler
/*     */   public void quickcommand3f(PlayerChangedWorldEvent e) {
	if (e.getFrom().equals(Bukkit.getWorld(Main.plugin.getConfig().getString("Spawn.World"))) && Join.game.contains(e.getPlayer().getName())) {
Player p = e.getPlayer();
if (Main.getInstance().getConfig().getBoolean("DisableWorldLeaveKitPvPEvent")) {
	return;
}
    	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
    		return;
    	}
    	if (Main.plugin.getConfig().getBoolean("bungeemode")) {
    		return;
    	}
    	/* 279 */       Habilidade.removeAbility(p);
    	/* 280 */       Deshfire.Armadura.remove(p);
    	/* 281 */       Deshfire.Armadura2.remove(p);
    	/* 282 */       Deshfire.cooldownm.remove(p);
    	/* 283 */       Join.game.remove(p.getName());
    	/* 284 */       Join.game.remove(p.getName());
    	/* 285 */       Join.game.remove(p.getName());
    	/* 286 */       Join.game.remove(p.getName());
    	/* 287 */       Join.game.remove(p.getName());
    	/* 288 */       Join.game.remove(p.getName());
    	/* 289 */       Join.game.remove(p.getName());
    	/* 290 */       Join.game.remove(p.getName());
    	/* 291 */       Join.game.remove(p.getName());
    	/* 292 */       Join.game.remove(p.getName());
    	/* 293 */       Join.game.remove(p.getName());
    	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
    	/* 295 */       Join.game.remove(p.getName());
    	/* 296 */       Join.game.remove(p.getName());
    	/* 297 */       Join.game.remove(p.getName());

    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/* 302 */       Cooldown.remove(p);
    	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
    	/* 304 */       p.getInventory().clear();
    	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
    	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
    	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
    	p.setScoreboard(Join.savescore.get(p.getName()));
    	p.setLevel(Join.savelevel.get(p.getName()));
    	p.setFoodLevel(Join.savehunger.get(p.getName()));
    	p.setRemainingAir(Join.saveair.get(p.getName()));
    	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
    	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

    	/*     */   
    	/* 311 */       p.updateInventory();
    	API.tirarEfeitos(p);
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
	
}
/*     */   @EventHandler
/*     */   public void quickcommand4(PlayerCommandPreprocessEvent e)
/*     */   {
/* 400 */     if (e.getMessage().equalsIgnoreCase("/kpkit")) {
/* 401 */       e.setCancelled(true);
/* 402 */       Player p = e.getPlayer();
/* 403 */       p.chat("/kpkitmenu");
/* 404 */       if (!Join.game.contains(e.getPlayer().getName()))
/*     */       {
/* 406 */         e.getPlayer().sendMessage("§cYou must be in game to open kit menu!");
/* 407 */         e.getPlayer().closeInventory();
/*     */       }
/*     */     }
/*     */   }
/*     */   @EventHandler
/*     */   public void quickcommand4t(PlayerCommandPreprocessEvent e)
/*     */   {
/* 400 */     if (e.getMessage().equalsIgnoreCase("/spawn") && Main.plugin.getConfig().getBoolean("KP-Spawn-Command-Enabled")) {
/* 401 *
/* 402 */       Player p = e.getPlayer();
/* 403 */       
/* 404 */       if (Join.game.contains(p.getName()))
/*     */       {
	if (CombatLog.emCombate(p)) {
		p.sendMessage("§cYou are in combat!");
		e.setCancelled(true);
		return;
	}
	if (X1.lutadores.containsKey(p.getName())) {
		p.sendMessage("§cYou are in combat!");
		e.setCancelled(true);
		return;
	}
	if (CustomChallenge.lutadores.containsKey(p.getName())) {
		p.sendMessage("§cYou are in combat!");
		e.setCancelled(true);
		return;
	}

API.BuildScore(p);
	 p.sendMessage("§eTeleporting");
	 e.setCancelled(true);
	 p.getInventory().clear();
	 p.setHealth(1.0);
	 X1.inx1.remove(p);
	 Cooldown.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 X1.inx1.remove(p);
	 API.darEfeito(p, PotionEffectType.SLOW, 99, 6);
	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
			{
      public void run()
      {
/* 406 */   for (PotionEffect effect : p.getActivePotionEffects()) {
	/*  70 */         p.removePotionEffect(effect.getType());
	/*     */       }
	/*  72 */       p.sendMessage(String.valueOf(Main.getInstace().getConfig().getString("Prefix").replace("&", "§")) + Main.getInstace().getConfig().getString("Message.Clear").replace("&", "§"));
	/*  73 */       p.getInventory().clear();
	/*  74 */       p.getInventory().setHelmet(new ItemStack(Material.AIR));
	/*  75 */       p.getInventory().setChestplate(new ItemStack(Material.AIR));
	/*  76 */       Habilidade.removeAbility(p);
	/*  77 */       me.RafaelAulerDeMeloAraujo.SpecialAbility.Cooldown.remove(p);
	/*  78 */       Deshfire.cooldownm.remove(p);
	/*  79 */       Deshfire.armadura.remove(p);
	/*  80 */       Gladiator.lutando.remove(p);
	/*  81 */       Gladiator.gladgladiator.remove(p);
	/*  82 */       org.bukkit.World w = org.bukkit.Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Spawn.World"));
	/*  83 */       double x = Main.plugin.getConfig().getDouble("Spawn.X");
	/*  84 */       double y = Main.plugin.getConfig().getDouble("Spawn.Y");
	/*  85 */       double z = Main.plugin.getConfig().getDouble("Spawn.Z");
	/*  86 */       Location lobby = new Location(w, x, y, z);
	/*  87 */       lobby.setPitch((float)Main.plugin.getConfig().getDouble("Spawn.Pitch"));
	/*  88 */       lobby.setYaw((float)Main.plugin.getConfig().getDouble("Spawn.Yaw"));
	/*  89 */       p.getInventory().clear();
	/*  90 */       p.teleport(lobby);
	/*     */       p.setHealth(20);
	/*  92 */       p.getInventory().setLeggings(new ItemStack(Material.AIR));
	/*  93 */       p.getInventory().setBoots(new ItemStack(Material.AIR));
	/*  94 */       p.getInventory().setArmorContents(null);
	/*  94 */       ItemStack kitsr = Main.getInstance().getConfig().getItemStack("KitsItem");
	/*  96 */       ItemMeta kitsr2 = kitsr.getItemMeta();
	/*  97 */       kitsr2.setDisplayName(Main.messages.getString("KitsItemName").replace("&", "§"));
	/*  98 */       kitsr.setItemMeta(kitsr2);
	/*  95 */       ItemStack kits = Main.getInstance().getConfig().getItemStack("ShopItem");
	/*  96 */       ItemMeta kits2 = kits.getItemMeta();
	/*  97 */       kits2.setDisplayName(Main.messages.getString("ShopItemName").replace("&", "§"));
	/*  98 */       kits.setItemMeta(kits2);
	/*  99 */       ItemStack st = Main.getInstance().getConfig().getItemStack("1v1Item");
	/* 100 */       ItemMeta st2 = st.getItemMeta();
	/* 101 */       st2.setDisplayName(Main.messages.getString("1v1ItemName").replace("&", "§"));
	/* 102 */       st.setItemMeta(st2);
	ItemStack stats = Main.getInstance().getConfig().getItemStack("StatsItem");
	/* 227 */           ItemMeta stats2 = kits.getItemMeta();
	/* 228 */           stats2.setDisplayName(Main.messages.getString("StatsItemName").replace("&", "§"));
	/* 229 */           stats.setItemMeta(stats2);
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("StatsItemSlot"), stats);

	ItemStack stats1 = Main.getInstance().getConfig().getItemStack("ClickTestItem");
	/* 227 */           ItemMeta stats12 = stats1.getItemMeta();
	/* 228 */           stats12.setDisplayName(Main.messages.getString("ClickTestItemName").replace("&", "§"));
	/* 229 */           stats1.setItemMeta(stats12);
	ItemStack warp = Main.getInstance().getConfig().getItemStack("WarpItem");
	/* 227 */           ItemMeta warp2 = warp.getItemMeta();
	/* 228 */           warp2.setDisplayName("§aWarps");
	/* 229 */           warp.setItemMeta(warp2);
	if (!Main.getInstance().getConfig().getBoolean("DisableWarpItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("WarpItemSlot"), warp);
		}

	/* 103 */     
	if (!Main.getInstance().getConfig().getBoolean("DisableClickTestItem")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ClickTestItemSlot"), stats1);
		}
	p.getInventory().setItem(Main.getInstance().getConfig().getInt("KitsItemSlot"), kitsr);
	/* 103 */     	if (!Main.getInstance().getConfig().getBoolean("DisableShop")) {
		p.getInventory().setItem(Main.getInstance().getConfig().getInt("ShopItemSlot"), kits);
		}	
	if (!Main.getInstance().getConfig().getBoolean("Disable1v1Item")) {
		/* 104 */       	p.getInventory().setItem(Main.getInstance().getConfig().getInt("1v1ItemSlot"), st);
		/*     */       }       
	/*     */ 
	/* 107 */       p.updateInventory();
	/*     */       p.setAllowFlight(false);
	/*     */ p.setHealth(20);
	/* 107 */    
	/*     */       p.getInventory().setArmorContents(null);
	/*     */ API.tirarEfeitos(p);
	if (Main.getInstance().getConfig().getBoolean("DisableInitialItems")) {
		 p.getInventory().clear();
		 if(Bukkit.getPluginManager().getPlugin("ItemJoin") != null){
		 item.getItems(p);
			}
	 }
	/* 107 */       p.updateInventory();
/*     */         

/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 159 */         ((Player)p).playSound(((Player)p).getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.SucefullMessage")), 2.0F, 2.0F);
/* 160 */           	         } }, 10L);
	}
}

/* 407 */        
/*     */       
/*     */     
/*     */   } 
public static void throwRandomFirework(Player p) {
    Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
    FireworkMeta fwm = fw.getFireworkMeta();

    //Our random generator
    Random r = new Random();

    //Get the type
    int rt = r.nextInt(5) + 1;
    FireworkEffect.Type type = FireworkEffect.Type.BALL;
    if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
    if (rt == 3) type = FireworkEffect.Type.BURST;
    if (rt == 4) type = FireworkEffect.Type.CREEPER;
    if (rt == 5) type = FireworkEffect.Type.STAR;

    //Get our random colours
    int r1i = r.nextInt(17) + 1;
    int r2i = r.nextInt(17) + 1;
    Color c1 = Color.fromRGB(r1i);
    Color c2 = Color.fromRGB(r2i);
    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

    //Then apply the effect to the meta
    fwm.addEffect(effect);

    //Generate some random power and set it


    //Create our effect with this   int rp = r.nextInt(2) + 1;
    int rp = r.nextInt(2) + 1;
    fwm.setPower(rp);

    //Then apply this to our rocket
    fw.setFireworkMeta(fwm);
    
}

@EventHandler
public void onBauKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("ShopItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      p.openInventory(Shop.shop);
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
  }
}
@EventHandler
public void onLeaveKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("LeaveItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
    	if ((!Join.game.contains(p.getName()))) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("MustBeInGame").replace("&", "§"));
    		return;
    	}
    	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("NeedToRespawn").replace("&", "§"));
    		return;
    	}
    	if (Main.plugin.getConfig().getBoolean("bungeemode")) {
    		p.sendMessage(API.NomeServer + Main.messages.getString("BungeeModeEnabled").replace("&", "§"));
    		return;
    	}
    	/*     */       
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/*     */ 
    	/* 279 */       Habilidade.removeAbility(p);
    	/* 280 */       Deshfire.Armadura.remove(p);
    	/* 281 */       Deshfire.Armadura2.remove(p);
    	/* 282 */       Deshfire.cooldownm.remove(p);
    	/* 283 */       Join.game.remove(p.getName());
    	/* 284 */       Join.game.remove(p.getName());
    	/* 285 */       Join.game.remove(p.getName());
    	/* 286 */       Join.game.remove(p.getName());
    	/* 287 */       Join.game.remove(p.getName());
    	/* 288 */       Join.game.remove(p.getName());
    	/* 289 */       Join.game.remove(p.getName());
    	/* 290 */       Join.game.remove(p.getName());
    	/* 291 */       Join.game.remove(p.getName());
    	/* 292 */       Join.game.remove(p.getName());
    	/* 293 */       Join.game.remove(p.getName());
    	/* 294 */       Join.game.remove(p.getName());Join.game.remove(p.getName());
    	/* 295 */       Join.game.remove(p.getName());
    	/* 296 */       Join.game.remove(p.getName());
    	/* 297 */       Join.game.remove(p.getName());

    	/*     */ 
	    TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(p.getUniqueId());
TabAPI.getInstance().getScoreboardManager().resetScoreboard(tabPlayer);
    	/*     */ 
    	/*     */ 
    	/* 302 */       Cooldown.remove(p);
    	/* 303 */       p.sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.KitPvpLeave-Message").replace("&", "§")));
    	/* 304 */       p.getInventory().clear();
    	/* 305 */       p.teleport((Location)Join.saveworld.get(p.getName()));
    	/* 306 */       p.getInventory().setContents((ItemStack[])Join.saveinv.get(p.getName()));
    	/* 307 */       p.setGameMode((GameMode)Join.savegamemode.get(p.getName()));
    	p.setScoreboard(Join.savescore.get(p.getName()));
    	p.setLevel(Join.savelevel.get(p.getName()));
    	p.setFoodLevel(Join.savehunger.get(p.getName()));
    	p.setRemainingAir(Join.saveair.get(p.getName()));
    	/* 308 */       p.getInventory().setArmorContents((ItemStack[])Join.savearmor.get(p.getName()));
    	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.main.getConfig().getString("Title.LeaveTitle"), this.main.getConfig().getString("Title.LeaveSubTitle"));

    	/*     */   
    	/* 311 */       p.updateInventory();
    	API.tirarEfeitos(p);
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
  }
}
@EventHandler
public void onKit(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("KitsItem"))) && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      Bukkit.dispatchCommand(p, "kpkitmenu");
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstace().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
  }
}
@EventHandler
public void onKit2(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if (p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("ClickTestItem")) &&  (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      ClickTest.StartClick(p);
    }
  }
}

@EventHandler
public void onStats(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("StatsItem")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      StatusGUI.openGUI(p, p);
    }
  }
}
@EventHandler
public void onStats2(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("WarpItem")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
      WarpMenu.openwarp(p);
    }
  }
}
@EventHandler
public void v1(PlayerInteractEvent e)
{
  Player p = e.getPlayer();
  if ((p.getItemInHand().equals(Main.getInstance().getConfig().getItemStack("1v1Item")))  && (p.getItemInHand().getItemMeta().hasDisplayName()) && !Habilidade.ContainsAbility(p) && Join.game.contains(p.getName()))
  {
    e.setCancelled(true);
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))
    {
    	if (Main.cfg_x1.getString("x1.coords.spawn.world") == null) {
    		p.sendMessage(ChatColor.YELLOW + "The KitPvP 1vs1 is not seted yet!");
    		return;
    	}
      X1.entrar1v1(p);
      p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
    }
  }
} 
/*     */
@Override
public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
	// TODO Auto-generated method stub
	return false;
}
}

