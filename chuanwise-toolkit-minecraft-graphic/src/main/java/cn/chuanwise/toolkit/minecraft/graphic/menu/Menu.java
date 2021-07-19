package cn.chuanwise.toolkit.minecraft.graphic.menu;

import cn.chuanwise.toolkit.grid.Grid;
import cn.chuanwise.toolkit.minecraft.graphic.icon.Icon;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface Menu {
    int COLUMN_NUMBER = 9;

    Grid<Icon> getIcons();

    default void setIcon(int index, Icon icon) {
        getIcons().set(index, icon);
        icon.setMenu(this);
    }

    default void setIcon(int row, int column, Icon icon) {
        getIcons().set(row, column, icon);
    }

    default Icon getIcon(int row, int column) {
        return getIcons().get(row, column);
    }

    default Icon getIcon(int index) {
        return getIcons().get(index);
    }

    default int getRowNumber() {
        return getIcons().getRowNumber();
    }

    default int getColumnNumber() {
        return getIcons().getColumnNumber();
    }

    String getTitle();

    Plugin getPlugin();

    default void refresh() {}

    default void render(Player player) {
        refresh();
    }
}