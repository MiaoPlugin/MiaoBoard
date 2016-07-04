package pw.yumc.MiaoBoard.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 *
 * @since 2016年6月24日 下午3:29:39
 * @author 喵♂呜
 */
public class PlayerListener implements Listener {
    public void onPlayerJoin(final PlayerJoinEvent e) {
        ScoreBoardManager.add(e.getPlayer());
    }

    public void onPlayerQuit(final PlayerQuitEvent e) {
        ScoreBoardManager.remove(e.getPlayer());
    }
}
