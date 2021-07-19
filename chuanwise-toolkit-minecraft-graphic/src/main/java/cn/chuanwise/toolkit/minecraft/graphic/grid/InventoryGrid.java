package cn.chuanwise.toolkit.minecraft.graphic.grid;

import cn.chuanwise.toolkit.grid.Grid;
import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import cn.chuanwise.utility.CheckUtility;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGrid implements Grid<ItemStack> {
    private final Inventory inventory;

    public InventoryGrid(int rows, String title) {
        this.inventory = Bukkit.createInventory(null, rows * Menu.COLUMN_NUMBER, title);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getColumnNumber() {
        return Menu.COLUMN_NUMBER;
    }

    @Override
    public int getRowNumber() {
        return inventory.getSize() / Menu.COLUMN_NUMBER;
    }

    @Override
    public void set(int index, ItemStack itemStack) {
        CheckUtility.checkIndex(index, inventory.getSize(), "originalIndex");
        inventory.setItem(index, itemStack);
    }

    @Override
    public ItemStack get(int index) {
        CheckUtility.checkIndex(index, inventory.getSize(), "originalIndex");
        return inventory.getItem(index);
    }
}
