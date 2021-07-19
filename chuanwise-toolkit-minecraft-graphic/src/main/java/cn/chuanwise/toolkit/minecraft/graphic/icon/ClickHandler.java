package cn.chuanwise.toolkit.minecraft.graphic.icon;

import cn.chuanwise.toolkit.minecraft.graphic.menu.view.MenuView;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

@FunctionalInterface
public interface ClickHandler {
    void onClick(MenuView menuView, ClickType type, Player clicker);
}
