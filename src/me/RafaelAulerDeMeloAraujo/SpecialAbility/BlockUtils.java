package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.World;

public class BlockUtils {
  public static Set<Location> sphere(Location location, int radius, boolean hollow) {
    Set<Location> blocks = new HashSet<>();
    World world = location.getWorld();
    int X = location.getBlockX();
    int Y = location.getBlockY();
    int Z = location.getBlockZ();
    int radiusSquared = radius * radius;
    if (hollow) {
      for (int i = X - radius; i <= X + radius; i++) {
        for (int y = Y - radius; y <= Y + radius; y++) {
          for (int z = Z - radius; z <= Z + radius; z++) {
            if ((X - i) * (X - i) + (Y - y) * (Y - y) + (Z - z) * (Z - z) <= radiusSquared) {
              Location block = new Location(world, i, y, z);
              blocks.add(block);
            } 
          } 
        } 
      } 
      return makeHollow(blocks, true);
    } 
    for (int x = X - radius; x <= X + radius; x++) {
      for (int y = Y - radius; y <= Y + radius; y++) {
        for (int z = Z - radius; z <= Z + radius; z++) {
          if ((X - x) * (X - x) + (Y - y) * (Y - y) + (Z - z) * (Z - z) <= radiusSquared) {
            Location block = new Location(world, x, y, z);
            blocks.add(block);
          } 
        } 
      } 
    } 
    return blocks;
  }
  
  public static Set<Location> circle(Location location, int radius, boolean hollow) {
    Set<Location> blocks = new HashSet<>();
    World world = location.getWorld();
    int X = location.getBlockX();
    int Y = location.getBlockY();
    int Z = location.getBlockZ();
    int radiusSquared = radius * radius;
    if (hollow) {
      for (int i = X - radius; i <= X + radius; i++) {
        for (int z = Z - radius; z <= Z + radius; z++) {
          if ((X - i) * (X - i) + (Z - z) * (Z - z) <= radiusSquared) {
            Location block = new Location(world, i, Y, z);
            blocks.add(block);
          } 
        } 
      } 
      return makeHollow(blocks, false);
    } 
    for (int x = X - radius; x <= X + radius; x++) {
      for (int z = Z - radius; z <= Z + radius; z++) {
        if ((X - x) * (X - x) + (Z - z) * (Z - z) <= radiusSquared) {
          Location block = new Location(world, x, Y, z);
          blocks.add(block);
        } 
      } 
    } 
    return blocks;
  }
  
  private static Set<Location> makeHollow(Set<Location> blocks, boolean sphere) {
    Set<Location> edge = new HashSet<>();
    if (!sphere) {
      for (Location l : blocks) {
        World w = l.getWorld();
        int X = l.getBlockX();
        int Y = l.getBlockY();
        int Z = l.getBlockZ();
        Location front = new Location(w, (X + 1), Y, Z);
        Location back = new Location(w, (X - 1), Y, Z);
        Location left = new Location(w, X, Y, (Z + 1));
        Location right = new Location(w, X, Y, (Z - 1));
        if (!blocks.contains(front) || !blocks.contains(back) || !blocks.contains(left) || !blocks.contains(right))
          edge.add(l); 
      } 
      return edge;
    } 
    for (Location l : blocks) {
      World w = l.getWorld();
      int X = l.getBlockX();
      int Y = l.getBlockY();
      int Z = l.getBlockZ();
      Location front = new Location(w, (X + 1), Y, Z);
      Location back = new Location(w, (X - 1), Y, Z);
      Location left = new Location(w, X, Y, (Z + 1));
      Location right = new Location(w, X, Y, (Z - 1));
      Location top = new Location(w, X, (Y + 1), Z);
      Location bottom = new Location(w, X, (Y - 1), Z);
      if (!blocks.contains(front) || !blocks.contains(back) || !blocks.contains(left) || !blocks.contains(right) || !blocks.contains(top) || !blocks.contains(bottom))
        edge.add(l); 
    } 
    return edge;
  }
}

