package cn.chuanwise.toolkit.minecraft.graphic.holder;

import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuInventoryHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, Menu.COLUMN_NUMBER);
    }
}
