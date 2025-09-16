package me.RafaelAulerDeMeloAraujo.SpecialAbility;


import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.RafaelAulerDeMeloAraujo.main.Main;

public enum Position {
  SPAWN(getSpawnlocation());
  
  private Location location;
  
  Position(Location location) {
    this.location = location;
  }
  
  public Location toLocation() {
    return this.location;
  }


public static Location getSpawnlocation() {
    double x = Main.getInstance().getConfig().getInt("Spawn.X");
    double y =  Main.getInstance().getConfig().getInt("Spawn.Y");
    double z =  Main.getInstance().getConfig().getInt("Spawn.Z");
    float yaw =  Main.getInstance().getConfig().getInt("Spawn.Yaw");
    float pitch =  Main.getInstance().getConfig().getInt("Spawn.Pitch");
    String world =  Main.getInstance().getConfig().getString("Spawn.World");
    Location location = new Location(Bukkit.getWorld(world), x, y, z);
    location.setYaw(yaw);
    location.setPitch(pitch);
    return location;
  }
}