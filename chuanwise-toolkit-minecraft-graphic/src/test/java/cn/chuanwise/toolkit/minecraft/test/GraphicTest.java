package cn.chuanwise.toolkit.minecraft.test;

import cn.chuanwise.toolkit.minecraft.graphic.icon.SimpleIcon;
import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import cn.chuanwise.toolkit.minecraft.graphic.menu.SimpleMenu;
import cn.chuanwise.toolkit.minecraft.graphic.menu.view.MenuView;
import cn.chuanwise.toolkit.minecraft.graphic.menu.view.SimpleMenuView;

public class GraphicTest {
    public static void main(String[] args) {
        Menu menu = new SimpleMenu(null, "title", 8);
        MenuView menuView = new SimpleMenuView(menu, null);

        final SimpleIcon icon = new SimpleIcon(null);
        menu.setIcon(2, 1, icon);
    }
}
