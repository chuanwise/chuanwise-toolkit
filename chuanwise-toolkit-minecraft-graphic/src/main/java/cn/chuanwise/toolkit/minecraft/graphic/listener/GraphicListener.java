package cn.chuanwise.toolkit.minecraft.graphic.listener;

import cn.chuanwise.toolkit.minecraft.graphic.icon.Icon;
import cn.chuanwise.toolkit.minecraft.graphic.menu.view.MenuView;
import cn.chuanwise.utility.FunctionalUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicListener implements Listener {
    /** 打开的菜单的记录器 */
    protected static final Map<String, MenuView> openedMapView = new ConcurrentHashMap<>();

    public static void setOpened(MenuView menuView, Player player) {
        openedMapView.put(player.getName(), menuView);
    }

    public static void setClosed(Player player) {
        openedMapView.remove(player.getName());
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        final Player player = (Player) event.getPlayer();
        final String clickerName = player.getName();
        final MenuView menuView = openedMapView.get(clickerName);
        if (Objects.isNull(menuView)) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(menuView.getMenu().getPlugin(),
                () -> FunctionalUtility.runIfNonNull(menuView.getOnOpen(), menuView));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final String clickerName = event.getWhoClicked().getName();
        final MenuView menuView = openedMapView.get(clickerName);
        if (Objects.isNull(menuView)) {
            return;
        }

        final Icon icon = menuView.getMenu().getIcon(event.getRawSlot());
        if (Objects.isNull(icon)) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(menuView.getMenu().getPlugin(),
                () -> icon.onClick(event.getClick(), menuView, ((Player) event.getWhoClicked())));

        // 避免窗口被关闭
        event.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        final MenuView menuView = openedMapView.get(player.getName());
        if (Objects.isNull(menuView)) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(menuView.getMenu().getPlugin(),
                () -> FunctionalUtility.runIfNonNull(menuView.getOnClose(), menuView));
    }
}
