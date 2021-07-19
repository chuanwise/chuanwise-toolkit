package cn.chuanwise.toolkit.minecraft.graphic.menu;

import cn.chuanwise.toolkit.grid.ArrayGrid;
import cn.chuanwise.toolkit.grid.Grid;
import cn.chuanwise.toolkit.minecraft.graphic.icon.Icon;
import cn.chuanwise.utility.CheckUtility;
import lombok.Getter;

@Getter
public abstract class BaseMenu implements Menu {
    String title;
    final Grid<Icon> icons;

    public BaseMenu(String title, int rows) {
        CheckUtility.nonNull(title, "title");
        CheckUtility.checkArgument(rows > 0, "rows must be bigger than 0");

        this.title = title;
        this.icons = new ArrayGrid<>(rows, COLUMN_NUMBER);
    }
}
