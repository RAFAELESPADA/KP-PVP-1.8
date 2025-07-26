package me.RafaelAulerDeMeloAraujo.SpecialAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.RafaelAulerDeMeloAraujo.main.Main;

public class Gladiator implements Listener {
    public static ArrayList<String> gladgladiator;
    public boolean generateGLASS;
    public HashMap<String, Location> oldl;
    public static HashMap<String, String> lutando;
    public HashMap<Player, Location> localizacao;
    public HashMap<Location, Block> bloco;
    public HashMap<Integer, String[]> players;
    public HashMap<String, Integer> tasks;
    int nextID;
    public int id1;

    private static String prefix;
    public static final HashMap<String, Location> oldLocation;
    public static final HashMap<Player, Player> combateGlad;
    public static final HashMap<String, List<Location>> blocks;
    public static int id;
    public int id2;


    static {
        Gladiator.gladgladiator = new ArrayList<String>();
        Gladiator.lutando = new HashMap<String, String>();
           prefix = ChatColor.BLUE + "GLAD ";
            oldLocation = new HashMap<String, Location>();
            combateGlad = new HashMap<Player, Player>();
            blocks = new HashMap<String, List<Location>>();
            id = 0;
        }
    

    public Gladiator() {
        this.generateGLASS = true;
        this.oldl = new HashMap<String, Location>();
        this.localizacao = new HashMap<Player, Location>();
        this.bloco = new HashMap<Location, Block>();
        this.players = new HashMap<Integer, String[]>();
        this.tasks = new HashMap<String, Integer>();
        this.nextID = 0;
    }

    @EventHandler
    public void aoComando(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (Gladiator.lutando.containsKey(p.getName()) && e.getMessage().startsWith("/")) {
            e.setCancelled(true);
            p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("NoGladCreateArena").replace("&", "Â§")));
        }
    }

    @EventHandler
    public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
        final Player p = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        final Player r = (Player) event.getRightClicked();
        if (p.getItemInHand().getType() != Material.IRON_FENCE || !Habilidade.getAbility(p).equalsIgnoreCase("Gladiator")) {
            return;
        }
        if (Gladiator.lutando.containsKey(p.getName()) || Gladiator.lutando.containsKey(r.getName())) {
            event.setCancelled(true);
            p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("AlreadyInGlad").replace("&", "Â§")));
            return;
        }
        if (!Habilidade.ContainsAbility(r)) {
        	p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("Only-Use-Glad-On-Players-That-Have-Choosed-A-Kit").replace("&", "Â§")));
        	event.setCancelled(true);
        	return;
        }
        boolean isCitizensNPC = r.hasMetadata("NPC");
        if (isCitizensNPC) {
        	p.sendMessage("Please dont challenge our npcs to Gladiator");
        	return;
        }
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
            r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
            p.sendMessage(API.NomeServer + "§7You challenged "  + r.getName() + "! You have five seconds of invencibility!");
            p.sendMessage(API.NomeServer + "§7If dont have a winner in five minutes you will return to your previous location!");
            r.sendMessage(API.NomeServer + "§7You have been challenged by a gladiator! You have five seconds of invencibility!");
            r.sendMessage(API.NomeServer + "§7If dont have a winner in five minutes you will return to your previous location!");
            Gladiator.lutando.put(p.getName(), r.getName());
            Gladiator.lutando.put(r.getName(), p.getName());
            Gladiator.gladgladiator.add(p.getName());
            Gladiator.gladgladiator.add(r.getName());
            newGladiatorArena(p , r , p.getLocation());
            this.id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                        r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 3));
                    }
                }
            }, 2400L);
            this.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(p.getName()) && Gladiator.lutando.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.lutando.containsKey(r.getName()) && Gladiator.lutando.get(r.getName()).equalsIgnoreCase(p.getName())) {
                        removeGlad(p);
                        removeGlad(r);
                        p.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("GladNoWinner").replace("&", "Â§")));
                        r.sendMessage(String.valueOf(API.NomeServer) + (Main.messages.getString("GladNoWinner").replace("&", "Â§")));
                    }
                }
            }, 4800L);
        }
    
    
    @EventHandler
    public void onTeleportENDER(PlayerTeleportEvent e) {
    	Player p = e.getPlayer();
    	if (e.getCause() == TeleportCause.ENDER_PEARL && Gladiator.lutando.containsKey(p.getName())) {
    		p.sendMessage(API.NomeServer + "§7You cannot use enderpearls on gladiator!");
    		e.setCancelled(true);
    	}   	
    }

    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_FENCE && Habilidade.getAbility(p).equalsIgnoreCase("Gladiator")) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlyaerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getClickedBlock().setType(Material.BEDROCK);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getClickedBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getBlock().setType(Material.BEDROCK);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.lutando.containsKey(e.getPlayer().getName())) {
                        e.getBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBrea(final PlayerMoveEvent e) {

        if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
        	Player p = e.getPlayer();

            final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
            if (t.getLocation().distance(p.getLocation()) >= 50) {
            	 removeGlad(p);
                 	p.setHealth(0.0);
                 }
            }	
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeftt(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        removeGlad(p);
        if (Gladiator.lutando.containsKey(e.getPlayer().getName())) {
        	p.setHealth(0.0);
        	final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
        	t.sendMessage("§7Your oponnent disconnected");
        }
        }

        /**if (Gladiator.lutando.containsKey(p.getName())) {
         final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
         Gladiator.lutando.remove(t.getName());
         Gladiator.lutando.remove(p.getName());
         Gladiator.gladgladiator.remove(p.getName());
         Gladiator.gladgladiator.remove(t.getName());
         final Location old = this.oldl.get(t.getName());
         t.teleport(old);
         t.sendMessage(String.valueOf(API.NomeServer) + "Â§cO jogador " + p.getName() + " deslogou");
         Bukkit.getScheduler().cancelTask(this.id1);
         Bukkit.getScheduler().cancelTask(this.id2);
         t.removePotionEffect(PotionEffectType.WITHER);
         final Location loc = this.localizacao.get(p);
         final List<Location> cuboid = new ArrayList<Location>();
         for (int bX = -8; bX <= 8; ++bX) {
         for (int bZ = -8; bZ <= 8; ++bZ) {
         for (int bY = -1; bY <= 4; ++bY) {
         if (bY == 4) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         else if (bY == -1) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
         cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         }
         }
         }
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         for (final Location loc2 : cuboid) {
         loc2.getBlock().setType(Material.AIR);
         this.bloco.get(loc2).setType(Material.AIR);
         }
         }*/
    public static final Object newGladiatorArena(final Player p1, final Player p2, final Location loc) {
        if (Gladiator.id > 15) {
            Gladiator.id = 0;
        }
        ++Gladiator.id;
        double x = loc.getX();
        final Random random = new Random();
        final double y = 75 + random.nextInt(100);
        double z = loc.getZ();
        double x1 = x + random.nextInt(400);
        double z1 = z + random.nextInt(589);
        final Location loc2 = new Location(p1.getWorld(), x1, y + 30, z1);
        final Location loc3 = new Location(p1.getWorld(), x1, y + 30.0, z1 + 8.0);
        final Location loc4 = new Location(p1.getWorld(), x1 - 8.0, y + 30.0, z1 - 8.0);
        loc2.getWorld().refreshChunk(loc2.getChunk().getX(), loc2.getChunk().getZ());
        final List<Location> location = new ArrayList<Location>();
        location.clear();
        for (int blockX = -10; blockX <= 10; ++blockX) {
            for (int blockZ = -10; blockZ <= 10; ++blockZ) {
                for (int blockY = -1; blockY <= 10; ++blockY) {
                    final Block b = loc2.clone().add((double)blockX, (double)blockY, (double)blockZ).getBlock();
                    if (!b.isEmpty()) {
                        x = random.nextInt(-550);
                        z = random.nextInt(994);
                        final Location newLoc = new Location(p1.getWorld(), loc2.getBlockX() + x, 50.0, loc2.getBlockZ() + z);
                        return newGladiatorArena(p1, p2, newLoc);
                    }
                    if (blockY == 10) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockY == -1) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockX == -10 || blockZ == -10 || blockX == 10 || blockZ == 10) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                }
            }
        }
        for (final Location arena : location) {
            arena.getBlock().setTypeIdAndData(95, (byte)Gladiator.id, true);
        }
        Gladiator.oldLocation.put(p1.getName(), p1.getLocation());
        Gladiator.oldLocation.put(p2.getName(), p2.getLocation());
        Gladiator.blocks.put(p1.getName(), location);
        Gladiator.blocks.put(p2.getName(), location);
        p1.teleport(new Location(p1.getWorld(), loc3.getX() + 7.5, loc3.getY() + 4.0, loc3.getZ(), 140.0f, 0.0f));
        p2.teleport(new Location(p2.getWorld(), loc4.getX() + 0.5, loc4.getY() + 4.0, loc2.getZ() - 7.5, -40.0f, 0.0f));
        p1.sendMessage(String.valueOf(Gladiator.prefix) + "§fYou challenged §e" + p2.getName() + " §fto gladiator!");
        p2.sendMessage(String.valueOf(Gladiator.prefix) + "§fYou gets challenged by §e" + p1.getName() + " §f!to gladiator");
        showPlayer(p1, p2);
        return null;
    }
    public static final void showPlayer(final Player one, final Player two) {
        one.showPlayer(two);
        two.showPlayer(one);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathGladiator(final PlayerDeathEvent e) {
        removeGlad(e.getEntity());
    }

    public void removeGlad(Player player) {
        for (final PotionEffect pot : player.getActivePotionEffects()) {
           player.removePotionEffect(pot.getType());
        }
        if (lutando.containsKey(player.getName())) {
        final Player t = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(player.getName()));
        for (final Location loc : blocks.get(player.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
  
       blocks.remove(player.getName());
       oldLocation.remove(player.getName());
        gladgladiator.remove(player.getName());
        localizacao.remove(player);
        player.removePotionEffect(PotionEffectType.WITHER);
        if (Gladiator.lutando.containsKey(player.getName())) {
            if (oldl.containsKey(t.getName())) {
                t.teleport(oldl.get(t.getName()));
            }
            if (oldl.containsKey(player.getName())) {
                player.teleport(oldl.get(player.getName()));
            }
            if (t != null) {
            for (int i = 1; i < 5; ++i) {
                t.teleport((Location)oldLocation.get(t.getName()));
            }
            }
            t.removePotionEffect(PotionEffectType.WITHER);
            Gladiator.lutando.remove(t.getName());
            Gladiator.lutando.remove(player.getName());
            gladgladiator.remove(t.getName());
            blocks.remove(t.getName());
            localizacao.remove(t);
            oldl.remove(t.getName());
        }
        oldl.remove(player.getName());

        /**
         *
         *         final Player p = e.getEntity();
         *         if (Gladiator.lutando.containsKey(p.getName())) {
         *             final Player k = Bukkit.getServer().getPlayer((String)Gladiator.lutando.get(p.getName()));
         *             final Location old = this.oldl.get(p.getName());
         *             k.teleport(old);
         *             k.sendMessage(String.valueOf(API.NomeServer) + "Â§7Voce ganhou a batalha contra " + p.getName());
         *             Bukkit.getScheduler().cancelTask(this.id1);
         *             Bukkit.getScheduler().cancelTask(this.id2);
         *             k.removePotionEffect(PotionEffectType.WITHER);
         *             k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
         *             Gladiator.lutando.remove(k.getName());
         *             Gladiator.lutando.remove(p.getName());
         *             Gladiator.gladgladiator.remove(p.getName());
         *             Gladiator.gladgladiator.remove(k.getName());
         *             final Location loc = this.localizacao.get(p);
         *             final List<Location> cuboid = new ArrayList<Location>();
         *             cuboid.clear();
         *             for (int bX = -8; bX <= 8; ++bX) {
         *                 for (int bZ = -8; bZ <= 8; ++bZ) {
         *                     for (int bY = -1; bY <= 4; ++bY) {
         *                         if (bY == 4) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                         else if (bY == -1) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                         else if (bX == -8 || bZ == -8 || bX == 8 || bZ == 8) {
         *                             cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
         *                         }
         *                     }
         *                 }
         *             }
         *             for (final Location loc2 : cuboid) {
         *                 loc2.getBlock().setType(Material.AIR);
         *                 if (this.bloco.containsKey(loc2)) {
         *                     this.bloco.get(loc2).setType(Material.AIR);
         *                 }
         *             }
         *         }
         *
         */
    }
    }
}
