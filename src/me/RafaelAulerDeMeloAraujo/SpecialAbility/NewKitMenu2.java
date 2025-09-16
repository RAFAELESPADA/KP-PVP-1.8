package me.RafaelAulerDeMeloAraujo.SpecialAbility;

/*     */ import java.util.ArrayList;
import java.util.Random;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewKitMenu2
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public NewKitMenu2(Main main)
/*     */   {
/*  31 */     this.main = main;
/*  32 */     plugin = main;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void kits(InventoryClickEvent e)
/*     */   {
/*  48 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
/*     */     {
/*  50 */       Inventory inv = e.getInventory();
/*  51 */       Player p = (Player)e.getWhoClicked();
/*  52 */       if (inv.getTitle().equals(Main.messages.getString("KitsInventoryName").replace("&", "§")))
/*     */       {
/*  54 */         p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.KitMenu")), 1.0F, 1.0F);
/*     */         
/*  56 */         e.setCancelled(true);
/*     */         
/*     */ API.BuildScore(p);
/*     */ 
/*  60 */      
/* 196 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals((Main.messages.getString("BackPageBottom").replace("&", "§"))))
/*     */         {
/* 198 */           Bukkit.dispatchCommand(p, "kpkitmenu");
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lBerserker")) {
/*  61 */           Bukkit.dispatchCommand(p, "kberserker");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lKombo")) {
/*  61 */           Bukkit.dispatchCommand(p, "kkombo");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lSasuke")) {
/*  61 */           Bukkit.dispatchCommand(p, "ksasuke");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lBlink")) {
/*  61 */           Bukkit.dispatchCommand(p, "kblink");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lDigger")) {
/*  61 */           Bukkit.dispatchCommand(p, "kdigger");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lMinato")) {
/*  61 */           Bukkit.dispatchCommand(p, "kminato");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lNeutralizer")) {
/*  61 */           Bukkit.dispatchCommand(p, "kneutralizer");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lMario")) {
/*  61 */           Bukkit.dispatchCommand(p, "kmario");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lLeopard")) {
/*  61 */           Bukkit.dispatchCommand(p, "kleopard");
/*  62 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lFlash")) {
/*  61 */           Bukkit.dispatchCommand(p, "kflash");
/*  62 */           p.closeInventory();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args)
/*     */   {
/* 209 */     if (!(sender instanceof Player)) {
/* 210 */       return true;
/*     */     }
/* 212 */     Player p = (Player)sender;
/* 213 */     if (cmd.getName().equalsIgnoreCase("kpkitmenu2"))
/*     */     {
/* 215 */       Inventory kits = Bukkit.createInventory(p, 54, Main.messages.getString("KitsInventoryName").replace("&", "§"));

/*     */       
/* 222 */        ItemStack vidro1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
/* 223 */       ItemMeta vidro12 = vidro1.getItemMeta();
/* 224 */       vidro12.setDisplayName("§a§k========");
/* 225 */       vidro1.setItemMeta(vidro12);
/*     */       
/* 227 */       ItemStack barrier = new ItemStack(Material.BARRIER);
/* 228 */       ItemMeta barrier2 = barrier.getItemMeta();
/* 229 */       barrier2.setDisplayName((Main.messages.getString("CloseKitMenuBottom").replace("&", "§")));
/* 230 */       barrier.setItemMeta(barrier2);


/* 227 */       ItemStack seta = new ItemStack(Material.ARROW);
/* 228 */       ItemMeta seta2 = seta.getItemMeta();
/* 229 */       seta2.setDisplayName((Main.messages.getString("BackPageBottom").replace("&", "§")));
/* 230 */       seta.setItemMeta(seta2);
(new BukkitRunnable() {

    public void run() {
    	Random r = new Random();
    	int id = r.nextInt(14);
    	ItemStack vidro21 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)id);
    	/* 223 */       ItemMeta vidro212 = vidro1.getItemMeta();
    	/* 224 */       vidro212.setDisplayName("§a§k========");
    	/* 225 */       vidro21.setItemMeta(vidro212);
/* 258 */       kits.setItem(52, vidro21);
    }
    
}).runTaskTimer((Plugin)Main.getInstance(), 0L, 20L);
/* 259 */       kits.setItem(53, barrier);
kits.setItem(49, seta);

/* 272 */        {
if (p.hasPermission("kitpvp.kit.berserker"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.GOLDEN_CARROT);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lBerserker");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("BerserkerLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("BerserkerDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }
}
if (p.hasPermission("kitpvp.kit.kombo"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.GOLD_BLOCK);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lKombo");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("KomboLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("KomboDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }
/*     */       }
if (p.hasPermission("kitpvp.kit.flash"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.REDSTONE_TORCH_ON);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lFlash");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("FlashLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("FlashDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }

/*     */       }
if (p.hasPermission("kitpvp.kit.sasuke"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.ARROW);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lSasuke");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("SasukeLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("SasukeDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }


/*     */       }
if (p.hasPermission("kitpvp.kit.minato"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.ARMOR_STAND);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lMinato");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("MinatoLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("MinatoDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }


/*     */       }
if (p.hasPermission("kitpvp.kit.neutralizer"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.BARRIER);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lNeutralizer");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("NeutralizerLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("NeutralizerDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }


/*     */       }
if (p.hasPermission("kitpvp.kit.digger"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.DRAGON_EGG);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lDigger");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("DiggerLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("DiggerDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }


/*     */       }
if (p.hasPermission("kitpvp.kit.mario"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.BROWN_MUSHROOM);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lMario");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("MarioLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("MarioDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }
}
if (p.hasPermission("kitpvp.kit.blink"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.NETHER_STAR);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lBlink");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("BlinkLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("BlinkDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }
}
if (p.hasPermission("kitpvp.kit.leopard"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.MONSTER_EGG, 1, (short)98);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lLeopard");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("LeopardLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
if (!Main.getInstance().getConfig().getBoolean("LeopardDisabled")) {
	/* 280 */         kits.addItem(new ItemStack[] { pyro });      
/*     */        }
}
/* 575 */       p.openInventory(kits);
/*     */     }
/* 577 */     return true;
/*     */   }
/*     */
return false; }
}




