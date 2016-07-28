package pw.yumc.MiaoBoard.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.config.FileConfig;
import cn.citycraft.PluginHelper.kit.P;
import cn.citycraft.PluginHelper.scoreboard.BoardUpdateFunction;
import cn.citycraft.PluginHelper.scoreboard.Condition;
import cn.citycraft.PluginHelper.scoreboard.SidebarBoard;
import cn.citycraft.PluginHelper.utils.CompatibleUtil;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.updater.BodyUpdater;
import pw.yumc.MiaoBoard.scoreboard.updater.TitleUpdater;

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
    public static List<BoardModel> bms = new ArrayList<>();

    public static List<BoardModel> getModels() {
        return bms;
    }

    public static SidebarBoard getSidebarBoard() {
        return sbd;
    }

    public static void load() {
        bms.clear();
        for (final String bmn : config.getConfigurationSection("Boards").getKeys(false)) {
            bms.add(new BoardModel(config.getConfigurationSection("Boards." + bmn)));
        }
    }

    public static void reload() {
        sbd.cancel();
        MiaoBoardConfig.reInject();
        load();
        start();
    }

    public static void start() {
        sbd.update(cot, MiaoBoardConfig.UpdateTime);
        for (final Player player : CompatibleUtil.getOnlinePlayers()) {
            sbd.addTarget(player);
        }
    }

    public static class Status implements Condition {
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
