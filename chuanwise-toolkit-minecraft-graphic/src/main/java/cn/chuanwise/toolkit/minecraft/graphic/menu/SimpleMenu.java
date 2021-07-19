package cn.chuanwise.toolkit.minecraft.graphic.menu;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

@Getter
public class SimpleMenu extends BaseMenu {
    final Plugin plugin;

    public SimpleMenu(Plugin plugin, String title, int rowNumber) {
        super(title, rowNumber);
        this.plugin = plugin;
    }
}
