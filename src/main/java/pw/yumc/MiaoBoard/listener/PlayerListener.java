package pw.yumc.MiaoBoard.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cn.citycraft.PluginHelper.kit.PKit;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 *
 * @since 2016年6月24日 下午3:29:39
 * @author 喵♂呜
 */
public class PlayerListener implements Listener {
    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, PKit.i());
    }

    public void onPlayerChangeWorld(final PlayerChangedWorldEvent e) {
        if (MiaoBoardConfig.DisableWorld.contains(e.getPlayer().getWorld().getName())) {
            ScoreBoardManager.remove(e.getPlayer());
        } else {
            ScoreBoardManager.add(e.getPlayer());
        }
    }

    public void onPlayerJoin(final PlayerJoinEvent e) {
        if (!MiaoBoardConfig.DisableWorld.contains(e.getPlayer().getWorld().getName())) {
            ScoreBoardManager.add(e.getPlayer());
        }
    }

    public void onPlayerQuit(final PlayerQuitEvent e) {
        ScoreBoardManager.remove(e.getPlayer());
    }
}
