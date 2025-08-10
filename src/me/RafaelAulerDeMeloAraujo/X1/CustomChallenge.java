package me.RafaelAulerDeMeloAraujo.X1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;
import me.RafaelAulerDeMeloAraujo.main.Main;

public class CustomChallenge implements Listener {
	public static HashMap<UUID, CustomManager> customs  = new HashMap();
	public static ArrayList<Player> customizing = new ArrayList();
	/*     */ public static Map<String, String> convites = new HashMap();

/*  48 */   public static Map<String, String> lutadores = new HashMap();
	/*     */ public static Map<String, String> who = new HashMap();
	 public static String apply(String str) {
	      return str.replaceAll("&" , "§");
	   }
	public static void openi(Player p, Player t) {
		
		Inventory i = Bukkit.createInventory(p, 54, "- " + t.getName());
		/*     */  
		
		
		ItemStack book = new ItemStack(Material.BOOK);
		/* 196 */     ItemMeta book2 = book.getItemMeta();
		/* 197 */     book2.setDisplayName(apply(Main.messages.getString("Custom1v1InfoName")));
		book2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		 ArrayList indiob = new ArrayList();
		   
		    	 List<String> rawList = Main.messages.getStringList("Custom1v1InfoLore");
		    	    List<String> coloredList = new ArrayList<>();
		    	 for (String s : rawList) {
		    	        coloredList.add(ChatColor.translateAlternateColorCodes('&', s));
		    	        book2.setLore(coloredList);
		    	 }
		/* 198 */     book.setItemMeta(book2);
		i.setItem(4, book
				);
		
/* 195 */     ItemStack monk = new ItemStack(Material.DIAMOND_SWORD);
/* 196 */     ItemMeta ims = monk.getItemMeta();
/* 197 */     ims.setDisplayName(apply(Main.messages.getString("Custom1v1DiamondSword")));
ims.addItemFlags(ItemFlag.HIDE_ENCHANTS);
/* 198 */     monk.setItemMeta(ims);
i.setItem(9, monk
		);
		ItemStack monk2 = new ItemStack(Material.BOWL);
		/* 196 */     ItemMeta ims2 = monk2.getItemMeta();
		/* 197 */     ims2.setDisplayName(apply(Main.messages.getString("NoRefill")));
		ims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     monk2.setItemMeta(ims2);
		i.setItem(11, monk2
				);

		ItemStack monk22 = new ItemStack(Material.GLASS);
		/* 196 */     ItemMeta ims22 = monk22.getItemMeta();
		/* 197 */     ims22.setDisplayName(apply(Main.messages.getString("NoArmor")));
		ims22.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     monk22.setItemMeta(ims22);
		i.setItem(13, monk22
				);

		ItemStack monk23 = new ItemStack(Material.POTION);
		/* 196 */     ItemMeta ims23 = monk23.getItemMeta();
		/* 197 */     ims23.setDisplayName(apply(Main.messages.getString("NoEffects")));
		ims23.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     monk23.setItemMeta(ims23);
		i.setItem(15, monk23
				);

		ItemStack monk24 = new ItemStack(Material.THIN_GLASS);
		/* 196 */     ItemMeta ims24 = monk24.getItemMeta();
		/* 197 */     ims24.setDisplayName(apply(Main.messages.getString("NoRecraft")));
		ims24.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     monk24.setItemMeta(ims24);
		i.setItem(17, monk24
				);

        ItemStack cf = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		/* 196 */     ItemMeta cf2 = cf.getItemMeta();
		/* 197 */     cf2.setDisplayName(apply(Main.messages.getString("ConfigureSword")));
		cf2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     cf.setItemMeta(cf2);
		i.setItem(18, cf
				);  

		ItemStack cfb2 = new ItemStack(Material.INK_SACK, 1, (short)8);
		/* 196 */     ItemMeta cf22 = cfb2.getItemMeta();
		/* 197 */     cf22.setDisplayName(apply(Main.messages.getString("ConfigureRefill")));
		cf22.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     cfb2.setItemMeta(cf22);
		i.setItem(20, cfb2
				);

		ItemStack cfc2 = new ItemStack(Material.INK_SACK, 1, (short)8);
		/* 196 */     ItemMeta cfc22 = cfc2.getItemMeta();
		/* 197 */     cfc22.setDisplayName(apply(Main.messages.getString("ConfigureArmor")));
		cfc22.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     cfc2.setItemMeta(cfc22);
		i.setItem(22, cfc2
				);

		ItemStack cfce2 = new ItemStack(Material.INK_SACK, 1, (short)8);
		/* 196 */     ItemMeta cfce22 = cfce2.getItemMeta();
		/* 197 */     cfce22.setDisplayName(apply(Main.messages.getString("ConfigureEffects")));
		cfce22.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     cfce2.setItemMeta(cfce22);
		i.setItem(24, cfce2
				);

		ItemStack cfce212 = new ItemStack(Material.INK_SACK, 1, (short)8);
		/* 196 */     ItemMeta cfce222 = cfce212.getItemMeta();
		/* 197 */     cfce222.setDisplayName(apply(Main.messages.getString("ConfigureRecraft")));
		cfce222.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     cfce212.setItemMeta(cfce222);
		i.setItem(26, cfce212
				);

		ItemStack penis = new ItemStack(Material.INK_SACK, 1, (short)10);
		/* 196 */     ItemMeta penis2 = penis.getItemMeta();
		/* 197 */     penis2.setDisplayName(apply(Main.messages.getString("InvitePlayerCustom1V1")));
		penis2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		/* 198 */     penis.setItemMeta(penis2);
		i.setItem(31, penis
				);
		p.openInventory(i);
	}
	@EventHandler
	/*     */   public void kits(InventoryCloseEvent e)
	/*     */   {
		if (customizing.contains(e.getPlayer())) {
			customizing.remove(e.getPlayer());
			CustomChallenge.who.remove(e.getPlayer().getName());
		}
	}
	/*     */   @EventHandler
	/*     */   public void kits(InventoryClickEvent e)
	/*     */   {
	/*  48 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
	/*     */     {
	/*  50 */       Inventory inv = e.getInventory();

	/*  51 */       Player player = (Player)e.getWhoClicked();
	/*  51 */       Player p = (Player)e.getWhoClicked();
	/*  52 */       if (inv.getTitle().equals("- " + who.get(player.getName())))
	/*     */       {
	/*  54 */         p.playSound(p.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.KitMenu")), 1.0F, 1.0F);
	/*     */         
	/*  56 */         e.setCancelled(true);
				 String nick = inv.getTitle().replace("- ", "");
				 

				 
				 Player target = Bukkit.getPlayer(nick);
				 if (target == null) {
					 player.sendMessage("§cPlayer offline.");
		             player.closeInventory();
		             return;
				 }
				 if (e.getSlot() != 31) {
					 return;
				 }
	if (!convites.containsKey(p.getName())) {
		p.closeInventory();
		/* 296 */           p.sendMessage(Main.cfg_x1.getString("x1.msg.invite").replace("$player$", target.getName()).replace("&", "§"));
		/* 297 */           target.sendMessage(
		/* 298 */             Main.cfg_x1.getString("x1.msg.guest").replace("$player$", p.getName()).replace("&", "§"));
		/* 299 */           convites.put(p.getName(), target.getName());
		/* 300 */           Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new BukkitRunnable()
		/*     */           {
		/*     */             public void run()
		/*     */             {
		/* 304 */               if (X1.convites.containsKey(p.getName())) {
		/* 305 */                 X1.convites.remove(p.getName());
		/*     */               }
		/*     */             }
		/* 308 */           }, 200L);
		}}}}
	


	
			
	        
	    	
	        public static void customInv(Player player1, Player player2) {
	        	if (!convites.containsKey(player1.getName())) {
	    			
	    			
	    	        CustomManager customManager = new CustomManager(player1.getUniqueId());
	    	        customManager.setEfeitos(0);

	    	        who.put(player1.getName(), player2.getName());
	    	        customs.put(player1.getUniqueId(), customManager);
	    	        customizing.add(player1);
	    	        openi(player1, player2);
	    		}
	    	}
	    	
	        @EventHandler
	    	/*     */   public void kiats(InventoryClickEvent e)
	    	/*     */   {
	    	/*  48 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
	    	/*     */     {
	    	/*  50 */       Inventory inv = e.getInventory();

	    	/*  51 */       Player player = (Player)e.getWhoClicked();
	    	/*  51 */       Player p = (Player)e.getWhoClicked();
	    	/*  52 */       if (inv.getTitle().equals("- " + CustomChallenge.who.get(player.getName())))
	    	/*     */       {
	    				
	    				Player target = Bukkit.getPlayerExact(String.valueOf(CustomChallenge.who.get(player.getName())));
	    				if (target == null) {
	    					player.sendMessage("§cPlayer offline.");
	    		            player.closeInventory();
	    		            return;
	    				}
	    				if (customs.get(player.getUniqueId()) == null) {
	    					return;
	    				}
	    		    	CustomManager customManager = CustomChallenge.customs.get(player.getUniqueId());

 

	    if (customManager.getEfeitos() == 0 && e.getSlot() == 24) {
	    	ItemStack ef2 = new ItemStack(Material.POTION);
			 /* 196 */     ItemMeta efims2 = ef2.getItemMeta();
			 /* 197 */     efims2.setDisplayName(apply(Main.messages.getString("EffectsCustom1v1Name")));
			 /* 276 */         ArrayList eindiob2 = new ArrayList();
			 /* 277 */         eindiob2.add("§fEffects: §bNone");
			 efims2.setLore(eindiob2);
			 efims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     ef2.setItemMeta(efims2);
				customManager.setEfeitos(1);
			 inv.setItem(15, ef2);
		} else if (customManager.getEfeitos() == 1 && e.getSlot() == 24) {
			ItemStack ef2 = new ItemStack(Material.POTION, 1, (short)8194);
			 /* 196 */     ItemMeta efims2 = ef2.getItemMeta();
			 /* 197 */     efims2.setDisplayName(apply(Main.messages.getString("EffectsCustom1v1Name")));
			 /* 276 */         ArrayList eindiob2 = new ArrayList();
			 /* 277 */         eindiob2.add("§fEffects: §bSpeed");
			 efims2.setLore(eindiob2);
			 efims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     ef2.setItemMeta(efims2);
				customManager.setEfeitos(2);
			 inv.setItem(15, ef2);
		}  else if (customManager.getEfeitos() == 2 && e.getSlot() == 24) {
			ItemStack ef2 = new ItemStack(Material.POTION, 1, (short)8201);
			 /* 196 */     ItemMeta efims2 = ef2.getItemMeta();
			 /* 197 */     efims2.setDisplayName(apply(Main.messages.getString("EffectsCustom1v1Name")));
			 /* 276 */         ArrayList eindiob2 = new ArrayList();
			 /* 277 */         eindiob2.add("§fEffects: §bStrenght");
			 efims2.setLore(eindiob2);
			 efims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     ef2.setItemMeta(efims2);
				customManager.setEfeitos(3);
			 inv.setItem(15, ef2);
		} else if (customManager.getEfeitos() == 3 && e.getSlot() == 24) {
			ItemStack ef2 = new ItemStack(Material.POTION, 1, (short)8227);
			 /* 196 */     ItemMeta efims2 = ef2.getItemMeta();
			 /* 197 */     efims2.setDisplayName(apply(Main.messages.getString("EffectsCustom1v1Name")));
			 /* 276 */         ArrayList eindiob2 = new ArrayList();
			 /* 277 */         eindiob2.add("§fEffects: §bStrenght and Speed");
			 efims2.setLore(eindiob2);
				customManager.setEfeitos(0);
			 efims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     ef2.setItemMeta(efims2);
			 inv.setItem(15, ef2);
		}
	    if (customManager.isRecraft() && e.getSlot() == 26) {
			customManager.setRecraft(false);
    		ItemStack monkB = new ItemStack(Material.THIN_GLASS);
			 /* 196 */     ItemMeta imsB = monkB.getItemMeta();
			 /* 197 */     imsB.setDisplayName(apply(Main.messages.getString("RecraftCustom1v1Name")));
			  ArrayList indiobB = new ArrayList();
				 /* 277 */         indiobB.add("§dNo Recraft");
				 imsB.setLore(indiobB);
			 imsB.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     monkB.setItemMeta(imsB);
			 inv. setItem(17, monkB);
		} else if (e.getSlot() == 26) {
			customManager.setRecraft(true);
    		ItemStack monkC = new ItemStack(Material.BROWN_MUSHROOM);
			 /* 196 */     ItemMeta imsC = monkC.getItemMeta();
			 /* 197 */     imsC.setDisplayName(apply(Main.messages.getString("RecraftCustom1v1Name")));
			 ArrayList indiobC = new ArrayList();
			 /* 277 */         indiobC.add("§dThe fight will have Recraft");
			 imsC.setLore(indiobC);
			 imsC.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			 /* 198 */     monkC.setItemMeta(imsC);
			 inv.	setItem(17, monkC);
		}

	}
	    	CustomManager customManager = CustomChallenge.customs.get(player.getUniqueId());
	    	if (customManager == null) {
	    		return;
	    	}
				switch (customManager.getArmadura()) {
				case GLASS:
					if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
						break;	
						}
						
					customManager.setArmadura(Material.LEATHER_CHESTPLATE);
					ItemStack monk = new ItemStack(Material.LEATHER_CHESTPLATE);
					 /* 196 */     ItemMeta ims = monk.getItemMeta();
					 /* 197 */     ims.setDisplayName(apply(Main.messages.getString("LeatherArmorCustom1v1Name")));
					 /* 276 */         ArrayList indiob = new ArrayList();
					 /* 277 */         indiob.add("§fThe fight will have leather armor");

						customManager.setArmadura(Material.LEATHER_CHESTPLATE);
					 ims.setLore(indiob);
					 ims.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk.setItemMeta(ims);
					
					inv.setItem(13, monk);
					break;
				case LEATHER_CHESTPLATE:
					if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
						break;
						}
					customManager.setArmadura(Material.IRON_CHESTPLATE);
					ItemStack monk2 = new ItemStack(Material.IRON_CHESTPLATE);
					 /* 196 */     ItemMeta ims2 = monk2.getItemMeta();
					 /* 197 */     ims2.setDisplayName(apply(Main.messages.getString("IronArmorCustom1v1Name")));
					 /* 276 */         ArrayList indiob2 = new ArrayList();
					 /* 277 */         indiob2.add("§fThe fight will have iron armor");

						customManager.setArmadura(Material.IRON_CHESTPLATE);
					 ims2.setLore(indiob2);
					 ims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk2.setItemMeta(ims2);
					
					inv.setItem(13, monk2);
					break;
				case IRON_CHESTPLATE:
					if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
						break;
						}
					customManager.setArmadura(Material.DIAMOND_CHESTPLATE);
					ItemStack monk3 = new ItemStack(Material.DIAMOND_CHESTPLATE);
					 /* 196 */     ItemMeta ims3 = monk3.getItemMeta();
					 /* 197 */     ims3.setDisplayName(apply(Main.messages.getString("DiamondArmorCustom1v1Name")));
					 /* 276 */         ArrayList indiob3 = new ArrayList();
					 /* 277 */         indiob3.add("§fThe fight will have diamond armor");

						customManager.setArmadura(Material.DIAMOND_CHESTPLATE);
					 ims3.setLore(indiob3);
					 ims3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk3.setItemMeta(ims3);
				
					inv.setItem(13, monk3);
					break;
	    	case DIAMOND_CHESTPLATE:
				if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
					break;	
					}
					customManager.setArmadura(Material.GOLD_CHESTPLATE);
						ItemStack monk4 = new ItemStack(Material.GOLD_CHESTPLATE);
						 /* 196 */     ItemMeta ims4 = monk4.getItemMeta();
						 /* 197 */     ims4.setDisplayName(apply(Main.messages.getString("GoldArmorCustom1v1Name")));
						 /* 276 */         ArrayList indiob4 = new ArrayList();
						 /* 277 */         indiob4.add("§fThe fight will have Gold Armor");
						 ims4.setLore(indiob4);
						 ims4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 /* 198 */     monk4.setItemMeta(ims4);
						inv.setItem(13, monk4);
						break;
				
	    	case GOLD_CHESTPLATE:
				if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
					break;
					}
				customManager.setArmadura(Material.CHAINMAIL_CHESTPLATE);
						ItemStack monk22 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
						 /* 196 */     ItemMeta ims22 = monk22.getItemMeta();
						 /* 197 */     ims22.setDisplayName(apply(Main.messages.getString("ChainCustom1v1Name")));
						 /* 276 */         ArrayList indiob22 = new ArrayList();
						 /* 277 */         indiob22.add("§fThe fight will have Chain armor");
						 ims22.setLore(indiob22);
						 ims22.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 /* 198 */     monk22.setItemMeta(ims22);
						inv.setItem(13, monk22);
						break;
	    	case CHAINMAIL_CHESTPLATE:
				if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
					break;
					}
				customManager.setArmadura(Material.GLASS);
					ItemStack NADA = new ItemStack(Material.GLASS);
					 /* 196 */     ItemMeta NADA2 = NADA.getItemMeta();
					 /* 197 */     NADA2.setDisplayName(apply(Main.messages.getString("NoArmorCustom1v1Name")));
					 /* 276 */         ArrayList indiobNADA = new ArrayList();
					 /* 277 */         indiobNADA.add("§fThe fight will have NO armor");
					 NADA2.setLore(indiobNADA);
					 NADA2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     NADA.setItemMeta(NADA2);
						customManager.setArmadura(Material.GLASS);
					inv.setItem(13, NADA);
					break;
				default:
					if (!(e.getSlot() == 22 && e.getCurrentItem().getType() == Material.INK_SACK && e.getCurrentItem().getDurability() == 8)) {
						break;
						}
					customManager.setArmadura(Material.GLASS);
					ItemStack NADA4 = new ItemStack(Material.GLASS);
					 /* 196 */     ItemMeta NADA42 = NADA4.getItemMeta();
					 /* 197 */     NADA42.setDisplayName(apply(Main.messages.getString("NoArmorCustom1v1Name")));
					 /* 276 */         ArrayList indiobNADA4 = new ArrayList();
					 /* 277 */         indiobNADA4.add("§fThe fight will have NO armor");
					 NADA42.setLore(indiobNADA4);
					 NADA42.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     NADA4.setItemMeta(NADA42);
						customManager.setArmadura(Material.GLASS);
					inv.setItem(13, NADA4);
					break;
					
				
				}
				if (customManager.isFullSopa()&& e.getSlot() == 20) {
	        		customManager.setFullSopa(false);
	        		ItemStack monk = new ItemStack(Material.BOWL);
					 /* 196 */     ItemMeta ims = monk.getItemMeta();
					 /* 197 */     ims.setDisplayName(apply(Main.messages.getString("NoArmorCustom1v1Name")));
					 ims.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk.setItemMeta(ims);
					
						inv.setItem(11, monk);
	        	
	        	} else if (!customManager.isFullSopa() && e.getSlot() == 20) {
	        		customManager.setFullSopa(true);
	        		ItemStack monk = new ItemStack(Material.MUSHROOM_SOUP);
					 /* 196 */     ItemMeta ims = monk.getItemMeta();
					 /* 197 */     ims.setDisplayName(apply(Main.messages.getString("NoRefill")));
					 ims.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk.setItemMeta(ims);
					
						inv.setItem(11, monk);
	        	}
			
				if (e.getCurrentItem().getType() == Material.GOLD_SWORD && e.getSlot() == 9) {
					customManager.setEspada(Material.WOOD_SWORD);
					 ItemStack monk = new ItemStack(Material.WOOD_SWORD);
					 /* 196 */     ItemMeta ims = monk.getItemMeta();
					 /* 197 */     ims.setDisplayName(apply(Main.messages.getString("WoodSwordCustom1v1")));
					 ims.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk.setItemMeta(ims);
					
					inv.setItem(9, monk);
				}

				else if (e.getCurrentItem().getType() == Material.WOOD_SWORD && e.getSlot() == 9) {
					customManager.setEspada(Material.STONE_SWORD);
					 ItemStack monk2 = new ItemStack(Material.STONE_SWORD);
					 /* 196 */     ItemMeta ims2 = monk2.getItemMeta();
					 /* 197 */     ims2.setDisplayName(apply(Main.messages.getString("StoneSwordCustom1v1")));
					 ims2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk2.setItemMeta(ims2);
					 
					inv.setItem(9, monk2);
				}
				else if (e.getCurrentItem().getType() == Material.STONE_SWORD && e.getSlot() == 9) {
					customManager.setEspada(Material.IRON_SWORD);

					 ItemStack monk3 = new ItemStack(Material.IRON_SWORD);
					 /* 196 */     ItemMeta ims3 = monk3.getItemMeta();
					 /* 197 */     ims3.setDisplayName(apply(Main.messages.getString("IronSwordCustom1v1")));
					 ims3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk3.setItemMeta(ims3);
					 
					 inv.setItem(9, monk3);
				}
				else if (e.getCurrentItem().getType() == Material.IRON_SWORD && e.getSlot() == 9) {
					customManager.setEspada(Material.DIAMOND_SWORD);

					 ItemStack monk4 = new ItemStack(Material.DIAMOND_SWORD);
					 /* 196 */     ItemMeta ims4 = monk4.getItemMeta();
					 /* 197 */     ims4.setDisplayName(apply(Main.messages.getString("DiamondSwordCustom1v1")));
					 ims4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk4.setItemMeta(ims4);
					
					inv.setItem(9, monk4);
					
				}
				else if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD && e.getSlot() == 9) {
					customManager.setEspada(Material.GOLD_SWORD);

					 ItemStack monk4 = new ItemStack(Material.GOLD_SWORD);
					 /* 196 */     ItemMeta ims4 = monk4.getItemMeta();
					 /* 197 */     ims4.setDisplayName(apply(Main.messages.getString("GoldSwordCustom1v1")));
					 ims4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					 /* 198 */     monk4.setItemMeta(ims4);
					
					inv.setItem(9, monk4);
					
				}
	    	}}
	        
	        /*     */   
	        /*     */   
	

	/*     */   
	
	
			
	        public static void aceitar(Player p1, Player p2)
	        /*     */   {

	        	CustomManager customManager = CustomChallenge.customs.get(p1.getUniqueId());
	        	setupCustomInv(p1, customManager);
	        	setupCustomInv(p2, customManager);
	        	org.bukkit.World w1 = Bukkit.getServer().getWorld(Main.cfg_x1.getString("x1.coords.loc_1.world"));
	        	org.bukkit.World w2 = Bukkit.getServer().getWorld(Main.cfg_x1.getString("x1.coords.loc_2.world"));
	        /* 152 */     p1.teleport(new Location(w1, Main.cfg_x1.getDouble("x1.coords.loc_1.x"), 
	        /* 153 */       Main.cfg_x1.getDouble("x1.coords.loc_1.y"), Main.cfg_x1.getDouble("x1.coords.loc_1.z"), 
	        /* 154 */       Float.valueOf((float)Main.cfg_x1.getDouble("x1.coords.loc_1.yaw")).floatValue(), Float.valueOf((float)Main.cfg_x1.getDouble("x1.coords.loc_1.pitch")).floatValue()));
	        /* 155 */     p2.teleport(new Location(w2, Main.cfg_x1.getDouble("x1.coords.loc_2.x"), 
	        /* 156 */       Main.cfg_x1.getDouble("x1.coords.loc_2.y"), Main.cfg_x1.getDouble("x1.coords.loc_2.z"), 
	        /* 157 */       Float.valueOf((float)Main.cfg_x1.getDouble("x1.coords.loc_2.yaw")).floatValue(), Float.valueOf((float)Main.cfg_x1.getDouble("x1.coords.loc_2.pitch")).floatValue()));
	        /* 163 */     convites.remove(p1.getName());
	        /* 164 */     for (Player pp : Bukkit.getOnlinePlayers()) {
	        /* 165 */       p1.hidePlayer(pp);
	        /*     */     }
	        /* 167 */     X1.hide.add(p1);
	        X1.frezze.add(p1);
	        Habilidade.setAbility(p1, "1v1Fight");
	        Habilidade.setAbility(p2, "1v1Fight");
	        X1.frezze.add(p2);
	        /* 168 */     for (Player pp : Bukkit.getOnlinePlayers()) {
	        /* 169 */       p2.hidePlayer(pp);
	        /*     */     }
	        /* 171 */     X1.hide.add(p2);
	        /* 172 */     p1.showPlayer(p2);
	        /* 173 */     p2.showPlayer(p1);

/* 161 */     lutadores.put(p1.getName(), p2.getName());
/* 162 */     lutadores.put(p2.getName(), p1.getName());
	        /* 174 */     p1.updateInventory();
	        /* 175 */     p2.updateInventory();
	        /* 176 */     p2.sendMessage(Main.cfg_x1.getString("x1.msg.invite_accept").replace("$player$", p1.getName()).replace("&", "§"));
	        /* 177 */     p1.sendMessage(Main.cfg_x1.getString("x1.msg.guest_accept").replace("$player$", p2.getName()).replace("&", "§"));
	        p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	        p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	        /* 280 */       new BukkitRunnable()
	        /*     */       {
	        /*     */         public void run()
	        /*     */         {
	                           TitleAPI.sendTitle(p1, 10, 10, 10, "§4§l3");
	                           p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           TitleAPI.sendTitle(p2, 10, 10, 10, "§4§l3");
	        /*     */         }
	        /* 280 */       }.runTaskLater(Main.plugin, 20L);
	        /* 280 */       new BukkitRunnable()
	        /*     */       {
	        /*     */         public void run()
	        /*     */         {
	                           TitleAPI.sendTitle(p1, 10, 10, 10, "§6§l2");
	                           p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           TitleAPI.sendTitle(p2, 10, 10, 10, "§6§l2");
	        /*     */         }
	        /* 280 */       }.runTaskLater(Main.plugin, 40L);
	        /*     */     /* 280 */       new BukkitRunnable()
	        /*     */       {
	        /*     */         public void run()
	        /*     */         {
	                           TitleAPI.sendTitle(p1, 10, 10, 10, "§a§l1");
	                           p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           TitleAPI.sendTitle(p2, 10, 10, 10, "§a§l1");
	                          }
	        /* 280 */       }.runTaskLater(Main.plugin, 60L);
	        /*     */     /* 280 */       new BukkitRunnable()
	        /*     */       {
	        /*     */         public void run()
	        /*     */         {
	                           TitleAPI.sendTitle(p1, 10, 10, 10, "§2§lGO!");
	                           p1.playSound(p1.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           p2.playSound(p2.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("Sound.1v1")), 5.0F, 5.0F);
	                           TitleAPI.sendTitle(p2, 10, 10, 10, "§2§lGO!");
	                           X1.frezze.remove(p1);
	                           X1.frezze.remove(p2);
	        /*     */         }
	        /* 280 */       }.runTaskLater(Main.plugin, 80L);
	        /*     */     
	        /*     */   }
	        private static void setupCustomInv(Player p, CustomManager CM) {
	    		if (CM.getEfeitos() == 1) {
	    			p.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.SPEED, 999999, 0));
	    		} else if (CM.getEfeitos() == 2) {
	    			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 0));
	    		} else if (CM.getEfeitos() == 3) {
	    			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 0));
	    			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0));
	    		}
	    		
	    		PlayerInventory inv = p.getInventory();
	    		
	    		inv.clear();
	    		inv.setArmorContents(null);
	    		
	    	    if (CM.getEspada().equals(Material.WOOD_SWORD)) {
	    	    	inv.setItem(0, new ItemStack(Material.WOOD_SWORD));
	    		} else if (CM.getEspada().equals(Material.STONE_SWORD)) {
	    	    	inv.setItem(0, new ItemStack(Material.STONE_SWORD));
	    		}
	    	    	else if (CM.getEspada().equals(Material.GOLD_SWORD)) {
		    	    	inv.setItem(0, new ItemStack(Material.GOLD_SWORD));
	    		} else if (CM.getEspada().equals(Material.IRON_SWORD)) {
	    	    	inv.setItem(0, new ItemStack(Material.IRON_SWORD));
	    		} else {
	    	    	inv.setItem(0, new ItemStack(Material.STONE_SWORD));
	    		}
	    		if (!CM.getArmadura().equals(Material.GLASS)) {
	    			if (CM.getArmadura().equals(Material.LEATHER_CHESTPLATE)) {
	    				inv.setHelmet(new ItemStack(Material.LEATHER_HELMET));
	    				inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
	    				inv.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
	    				inv.setBoots(new ItemStack(Material.LEATHER_BOOTS));
	    			} else if (CM.getArmadura().equals(Material.IRON_CHESTPLATE)) {
	    				inv.setHelmet(new ItemStack(Material.IRON_HELMET));
	    				inv.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
	    				inv.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
	    				inv.setBoots(new ItemStack(Material.IRON_BOOTS));
	    			}
	    				else if (CM.getArmadura().equals(Material.GOLD_CHESTPLATE)) {
		    				inv.setHelmet(new ItemStack(Material.GOLD_HELMET));
		    				inv.setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
		    				inv.setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
		    				inv.setBoots(new ItemStack(Material.GOLD_BOOTS));
	    				}
	    				else if (CM.getArmadura().equals(Material.CHAINMAIL_CHESTPLATE)) {
		    				inv.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
		    				inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		    				inv.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		    				inv.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
	    				}
	    			} else if (CM.getArmadura().equals(Material.DIAMOND_CHESTPLATE)) {
	    				inv.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	    				inv.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	    				inv.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
	    				inv.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	    			}
	    		
	    		if (CM.isFullSopa()) {
	    	        for (ItemStack is : inv.getContents()) {
	    	             if (is == null) {
	    	                inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
	    	        }
	    	        }} else {
	    	    	for (int i = 1; i < 9; i++) {
	    	    		 inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
	    	    	}
	    		}
	    		if (CM.isRecraft()) {
	    	    	inv.setItem(13, new ItemStack(Material.BOWL, 64));
	    	    	inv.setItem(14, new ItemStack(Material.RED_MUSHROOM, 64));
	    	    	inv.setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64));
	    		}
	    		
	    		p.updateInventory();
	    	}
	    	
	        
	        }


	
	


	
