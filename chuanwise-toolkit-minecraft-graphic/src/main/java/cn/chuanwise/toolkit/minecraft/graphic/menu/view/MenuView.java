package cn.chuanwise.toolkit.minecraft.graphic.menu.view;

import cn.chuanwise.toolkit.minecraft.graphic.listener.GraphicListener;
import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public interface MenuView {
    default void refresh() {
        getMenu().refresh();
    }

    default void close() {
        if (getPlayer().isOnline()) {
            getPlayer().closeInventory();
        }
    }

    default void open() {
        if (getPlayer().isOnline()) {
            GraphicListener.setOpened(this, getPlayer());
            getPlayer().openInventory(getInventory());
        }
    }

    Player getPlayer();

    Menu getMenu();

    Inventory getInventory();

    Consumer<MenuView> getOnClose();

    Consumer<MenuView> getOnOpen();

    void setOnClose(Consumer<MenuView> onClose);

    void setOnOpen(Consumer<MenuView> onClose);
}
