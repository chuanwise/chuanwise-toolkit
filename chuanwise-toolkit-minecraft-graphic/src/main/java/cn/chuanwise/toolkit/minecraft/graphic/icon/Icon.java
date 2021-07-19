/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package cn.chuanwise.toolkit.minecraft.graphic.icon;

import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import cn.chuanwise.toolkit.minecraft.graphic.menu.view.MenuView;
import cn.chuanwise.utility.CheckUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public interface Icon {
    ItemStack render(Player viewer);

    ClickHandler getOnClick();

    void setOnClick(ClickHandler onClick);

    default void onClick(ClickType type, MenuView menuView, Player player) {
        CheckUtility.nonNull(menuView, "menu view");

        if (Objects.nonNull(getOnClick())) {
            getOnClick().onClick(menuView, type, player);
        }
    }

    void setMenu(Menu menu);

    Menu getMenu();
}