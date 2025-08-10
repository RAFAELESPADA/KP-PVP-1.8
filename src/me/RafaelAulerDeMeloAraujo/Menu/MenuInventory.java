package me.RafaelAulerDeMeloAraujo.Menu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuInventory {
    private HashMap<Integer, MenuItem> slotItem;

    private int rows;

    private String title;

    private Inventory inv;

    private boolean onePerPlayer;

    private MenuUpdateHandler updateHandler;

    public MenuUpdateHandler getUpdateHandler() {
        return this.updateHandler;
    }

    public void setUpdateHandler(MenuUpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    public MenuInventory(String title, int rows) {
        this(title, rows, false);
    }

    public MenuInventory(String title, int rows, boolean onePerPlayer) {
        this.slotItem = new HashMap<>();
        this.rows = rows;
        this.title = title;
        this.onePerPlayer = onePerPlayer;
        if (!onePerPlayer)
            this.inv = Bukkit.createInventory(new MenuHolder(this), rows * 9, this.title);
    }

    public void addItem(MenuItem item) {
        setItem(firstEmpty(), item);
    }

    public void addItem(ItemStack item) {
        setItem(firstEmpty(), item);
    }

    public void setItem(ItemStack item, int slot) {
        setItem(slot, new MenuItem(item));
    }

    public void setItem(int slot, ItemStack item) {
        setItem(slot, new MenuItem(item));
    }

    public void setItem(int slot, ItemStack item, MenuClickHandler handler) {
        setItem(slot, new MenuItem(item, handler));
    }

    public void setItem(MenuItem item, int slot) {
        setItem(slot, item);
    }

    public void setItem(int slot, MenuItem item) {
        this.slotItem.put(Integer.valueOf(slot), item);
        if (!this.onePerPlayer)
            this.inv.setItem(slot, item.getStack());
    }

    public int firstEmpty() {
        if (!this.onePerPlayer)
            return this.inv.firstEmpty();
        for (int i = 0; i < this.rows * 9; i++) {
            if (!this.slotItem.containsKey(Integer.valueOf(i)))
                return i;
        }
        return -1;
    }

    public boolean hasItem(int slot) {
        return this.slotItem.containsKey(Integer.valueOf(slot));
    }

    public MenuItem getItem(int slot) {
        return this.slotItem.get(Integer.valueOf(slot));
    }

    public void clear() {
        this.slotItem.clear();
        if (!this.onePerPlayer)
            this.inv.clear();
    }

    public void open(Player p) {
        if (!this.onePerPlayer) {
            p.openInventory(this.inv);
        } else {
            if (p.getOpenInventory() == null || p
                    .getOpenInventory().getTopInventory().getType() != InventoryType.CHEST || p
                    .getOpenInventory().getTopInventory().getSize() != this.rows * 9 || p
                    .getOpenInventory().getTopInventory().getHolder() == null ||
                    !(p.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) ||
                    !((MenuHolder)p.getOpenInventory().getTopInventory().getHolder()).isOnePerPlayer()) {
                createAndOpenInventory(p);
            } else {
                for (int i = 0; i < this.rows * 9; i++) {
                    if (this.slotItem.containsKey(Integer.valueOf(i))) {
                        p.getOpenInventory().getTopInventory().setItem(i, ((MenuItem)this.slotItem.get(Integer.valueOf(i))).getStack());
                    } else {
                        p.getOpenInventory().getTopInventory().setItem(i, null);
                    }
                }
                p.updateInventory();
            }
            ((MenuHolder)p.getOpenInventory().getTopInventory().getHolder()).setMenu(this);
        }
        p = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createAndOpenInventory(Player p) {
        Inventory playerInventory = Bukkit.createInventory(new MenuHolder(this), this.rows * 9, this.title);
        for (Map.Entry<Integer, MenuItem> entry : this.slotItem.entrySet())
            playerInventory.setItem(((Integer)entry.getKey()).intValue(), ((MenuItem)entry.getValue()).getStack());
        p.openInventory(playerInventory);
        p = null;
    }

    public void close(Player p) {
        if (this.onePerPlayer) {
            destroy(p);
            p = null;
        }
    }

    public void destroy(Player p) {
        if (p.getOpenInventory().getTopInventory().getHolder() != null && p
                .getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder)
            ((MenuHolder)p.getOpenInventory().getTopInventory().getHolder()).destroy();
    }

    public boolean isOnePerPlayer() {
        return this.onePerPlayer;
    }

    public Inventory getInventory() {
        return this.inv;
    }
}
