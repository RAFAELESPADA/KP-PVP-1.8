package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.RafaelAulerDeMeloAraujo.main.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class Bot {
  private NPC bot;
  public static HashMap<String, Bot> ownedBot = new HashMap<>();
  
  public static HashMap<String, Player> attackPlayer = new HashMap<>();
  
  public Bot() {
    NPCRegistry localNPCRegistry = CitizensAPI.getNPCRegistry();
    NPC localNPC = localNPCRegistry.createNPC(EntityType.PLAYER, "SupremeBot");
    this.bot = localNPC;
  }
  
  public void setName(String name) {
    this.bot.setName(name);
  }
  
  public void setInvincible(boolean b) {
    this.bot.setProtected(b);
  }
  
  public void setLook(EntityType look) {
    this.bot.setBukkitEntityType(look);
  }

  public boolean isThere(Player p, Position position) {
    if (p.getLocation().getWorld() == position.toLocation().getWorld())
      return true; 
    return false;
  }
  
  public void setGuardienFor(final Player p) {
    final Bot clone = this;
    ownedBot.put(p.getName(), clone);
    (new BukkitRunnable() {
        int i = 0;
        
        public void run() {
          this.i++;
          if (this.i == 1200) {
            clone.despawn();
            cancel();
            Bot.ownedBot.remove(p.getName());
            Bot.attackPlayer.remove(p.getName());
          } 
          if (!p.isOnline()) {
            clone.despawn();
            Bot.ownedBot.remove(p.getName());
            Bot.attackPlayer.remove(p.getName());
            cancel();
          } 
          if (!Bot.this.bot.isSpawned()) {
            cancel();
            Bot.ownedBot.remove(p.getName());
            Bot.attackPlayer.remove(p.getName());
          } 
          if (isThere(p, Position.SPAWN)) {
            clone.despawn();
            Bot.ownedBot.remove(p.getName());
            Bot.attackPlayer.remove(p.getName());
            cancel();
          } 
          if (p.isDead()) {
            clone.despawn();
            Bot.ownedBot.remove(p.getName());
            Bot.attackPlayer.remove(p.getName());
            cancel();
          } 
          if (Bot.attackPlayer.containsKey(p.getName())) {
            Player attack = Bot.attackPlayer.get(p.getName());
            
            if (p.getLocation().distance(p.getLocation()) < 10.0D && !attack.isDead() && attack != null && !clone.getNPC().getName().contains(attack.getName())) {
              Bot.this.attackEntity((LivingEntity)Bot.attackPlayer.get(p.getName()));
            } else {
              Bot.this.followEntity((Entity)p);
            } 
          } else {
            Bot.this.followEntity((Entity)p);
          } 
          clone.setItemInHand(p.getItemInHand());
          ((LivingEntity)clone.getNPC().getEntity()).getEquipment().setHelmet(p.getInventory().getHelmet());
          ((LivingEntity)clone.getNPC().getEntity()).getEquipment().setBoots(p.getInventory().getBoots());
          ((LivingEntity)clone.getNPC().getEntity()).getEquipment()
            .setChestplate(p.getInventory().getChestplate());
          ((LivingEntity)clone.getNPC().getEntity()).getEquipment().setLeggings(p.getInventory().getLeggings());
        }
      }).runTaskTimer((Plugin)Main.getInstance(), 5L, 1L);
  }
  
  public void setShadow(final Player p, final Player p2) {
    final Bot clone = this;
    clone.setName(p.getName());
    clone.setInvincible(false);
    clone.summon(p.getLocation(), true);
    (new BukkitRunnable() {
        int i = 0;
        
        public void run() {
          this.i++;
          if (this.i == 200) {
            clone.despawn();
            cancel();
          } 
          if (!p.isOnline()) {
            clone.despawn();
            cancel();
          } 
          if (!Bot.this.bot.isSpawned())
            cancel(); 
          if (isThere(p, Position.SPAWN)) {
            clone.despawn();
            cancel();
          } 
          if (p.isDead()) {
            clone.despawn();
            cancel();
          } 
          if (!p2.isDead() && p2.getWorld() == p.getWorld() && p2.isOnline()) {
            clone.attackEntity((LivingEntity)p2);
          } else {
            clone.despawn();
            cancel();
          } 
          clone.setItemInHand(new ItemStack(Material.AIR));
        }
      }).runTaskTimer((Plugin)Main.getInstance(), 5L, 1L);
  }
  
  public void setMaxHealth(double d) {
    ((Damageable)this.bot.getEntity()).setMaxHealth(d);
  }
  
  public void setHealth(double d) {
    ((Damageable)this.bot.getEntity()).setHealth(d);
  }
  
  public double getMaxHealth() {
    return ((Damageable)this.bot.getEntity()).getMaxHealth();
  }
  
  public double getHealth() {
    return ((Damageable)this.bot.getEntity()).getHealth();
  }
  
  public void navigateTo(Location loc) {
    this.bot.getNavigator().setTarget(loc);
  }
  
  public void followEntity(Entity e) {
    this.bot.getNavigator().setTarget(e.getLocation());
  }
  
  public void attackEntity(LivingEntity e) {
    this.bot.getNavigator().setTarget(e.getLocation());
    this.bot.getNavigator().setTarget(e, true);
  }
  
  public void summon(Location paramLocation, boolean paramBoolean) {
    this.bot.spawn(paramLocation);
    if (paramBoolean)
      (new BukkitRunnable() {
          public void run() {
            if (!Bot.this.isSpawned()) {
              cancel();
              Bukkit.getConsoleSender().sendMessage("Bot got removed");
            } 
            if (((Damageable)Bot.this.bot.getEntity()).getHealth() < 13.0D) {
              ((Damageable)Bot.this.bot.getEntity())
                .setHealth(((Damageable)Bot.this.bot.getEntity()).getHealth() + 8.0D);
              if (Math.random() - Math.random() > 0.0D) {
                Item localItem = Bot.this.bot.getEntity().getWorld().dropItemNaturally(
                    Bot.this.bot.getEntity().getLocation(), new ItemStack(Material.BOWL));
                localItem.setVelocity(Bot.this.bot.getEntity().getLocation().getDirection().multiply(0.4D));
              } 
            } 
          }
        }).runTaskTimer((Plugin)Main.getInstance(), 20L, 1L); 
  }
  
  public void hackTest() {}
  
  public NPC getNPC() {
    return this.bot;
  }
  
  public void setItemInHand(ItemStack paramItemStack) {
    ((LivingEntity)this.bot.getEntity()).getEquipment().setItemInHand(paramItemStack);
  }
  
  public boolean isSpawned() {
    return this.bot.isSpawned();
  }
  
  public void despawn() {
    this.bot.destroy();
  }
}

