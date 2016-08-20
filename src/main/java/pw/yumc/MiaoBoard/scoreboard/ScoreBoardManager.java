package pw.yumc.MiaoBoard.scoreboard;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.config.FileConfig;
import cn.citycraft.PluginHelper.scoreboard.BoardUpdateFunction;
import cn.citycraft.PluginHelper.scoreboard.Condition;
import cn.citycraft.PluginHelper.scoreboard.SidebarBoard;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.updater.BodyUpdater;
import pw.yumc.MiaoBoard.scoreboard.updater.TitleUpdater;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.bukkit.compatible.C;

/**
 * 记分板管理类
 *
 * @since 2016年6月24日 下午3:31:31
 * @author 喵♂呜
 */
public class ScoreBoardManager {
    public static Status cot = new Status();
    public static SidebarBoard sbd = new SidebarBoard(P.instance, new BoardUpdateFunction(new TitleUpdater(), new BodyUpdater()));
    public static FileConfig config = MiaoBoardConfig.i().getConfig();;
    public static List<BoardModel> bms = new LinkedList<>();

    public static List<BoardModel> getModels() {
        return bms;
    }

    public static SidebarBoard getSidebarBoard() {
        return sbd;
    }

    public static void load() {
        bms.clear();
        for (final String bmn : config.getConfigurationSection("Boards").getKeys(false)) {
            bms.add(new BoardModel(config.getConfigurationSection("Boards." + bmn)).setName(bmn));
        }
        Collections.sort(bms, new BoardComparator());
    }

    public static void reload() {
        sbd.cancel();
        MiaoBoardConfig.reInject();
        load();
        start();
    }

    public static void start() {
        sbd.update(cot.set(true), MiaoBoardConfig.UpdateTime);
        for (final Player player : C.Player.getOnlinePlayers()) {
            sbd.addTarget(player);
        }
    }

    private static class BoardComparator implements Comparator<BoardModel> {
        @Override
        public int compare(final BoardModel o1, final BoardModel o2) {
            return o1.index.compareTo(o2.index);
        }
    }

    private static class Status implements Condition {
        private boolean status = true;

        @Override
        public boolean get() {
            return status;
        }

        public Status set(final boolean status) {
            this.status = status;
            return this;
        }
    }
}
