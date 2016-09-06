package pw.yumc.MiaoBoard.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pw.yumc.MiaoBoard.MiaoBoard;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.statistic.Statistics;
import pw.yumc.YumCore.update.SubscribeTask;

/**
 * 玩家监听
 *
 * @since 2016年6月24日 下午3:29:39
 * @author 喵♂呜
 */
public class PlayerListener implements Listener {
    MiaoBoard plugin = P.getPlugin();

    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        new Statistics();
        new SubscribeTask(true, true);
    }

    @EventHandler
    public void onPlayerChangeWorld(final PlayerChangedWorldEvent e) {
        if (MiaoBoardConfig.i().getDisableWorld().contains(e.getPlayer().getWorld().getName())) {
            plugin.getScoreBoardManager().getSidebarBoard().removeTarget(e.getPlayer());
        } else {
            plugin.getScoreBoardManager().getSidebarBoard().addTarget(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        plugin.getScoreBoardManager().addTarget(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        plugin.getScoreBoardManager().getSidebarBoard().removeTarget(e.getPlayer());
    }
}
