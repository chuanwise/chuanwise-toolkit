package cn.chuanwise.toolkit.minecraft.graphic.menu.view;

import cn.chuanwise.toolkit.minecraft.graphic.grid.InventoryGrid;
import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Objects;
import java.util.function.Consumer;

@Getter
@Setter
public class SimpleMenuView implements MenuView {
    final Menu menu;
    final Player player;
    final InventoryGrid inventoryGrid;

    Consumer<MenuView> onOpen, onClose;

    public SimpleMenuView(Menu menu, Player player) {
        this.menu = menu;
        this.player = player;
        this.inventoryGrid = new InventoryGrid(menu.getRowNumber(), menu.getTitle());
    }

    @Override
    public Inventory getInventory() {
        return inventoryGrid.getInventory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleMenuView)) {
            return false;
        }
        if (hashCode() == o.hashCode()) {
            SimpleMenuView that = (SimpleMenuView) o;
            return Objects.equals(menu, that.menu) &&
                    Objects.equals(player, that.player);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, player);
    }
}