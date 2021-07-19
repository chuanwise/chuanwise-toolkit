package cn.chuanwise.toolkit.minecraft.graphic.icon;

import cn.chuanwise.toolkit.minecraft.graphic.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class SimpleIcon implements Icon {
    ClickHandler onClick;
    ItemStack itemStack;
    Menu menu;

    public SimpleIcon(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public SimpleIcon(ItemStack itemStack, ClickHandler onClick) {
        this.itemStack = itemStack;
        this.onClick = onClick;
    }

    @Override
    public ItemStack render(Player player) {
        return itemStack;
    }
}