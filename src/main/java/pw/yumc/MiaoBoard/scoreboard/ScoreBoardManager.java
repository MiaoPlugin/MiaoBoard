package pw.yumc.MiaoBoard.scoreboard;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.kit.PKit;
import cn.citycraft.PluginHelper.scoreboard.BoardUpdateFunction;
import cn.citycraft.PluginHelper.scoreboard.SidebarBoard;
import pw.yumc.MiaoBoard.scoreboard.updater.BodyUpdater;
import pw.yumc.MiaoBoard.scoreboard.updater.TitleUpdater;

/**
 *
 * @since 2016年6月24日 下午3:31:31
 * @author 喵♂呜
 */
public class ScoreBoardManager {
    static SidebarBoard sbd;

    public ScoreBoardManager() {
        sbd = new SidebarBoard(PKit.i(), new BoardUpdateFunction(new TitleUpdater(), new BodyUpdater()));
    }

    public static void add(final Player player) {
        sbd.addTarget(player);
    }

    public static void remove(final Player player) {
        sbd.removeTarget(player);
    }
}
