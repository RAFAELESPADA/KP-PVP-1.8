package me.RafaelAulerDeMeloAraujo.Menu;

import org.bukkit.entity.Player;

public interface MenuUpdateHandler {
	
    void onUpdate(Player player, MenuInventory menu);
}