package pw.yumc.MiaoBoard.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.config.FileConfig;
import cn.citycraft.PluginHelper.kit.PKit;
import cn.citycraft.PluginHelper.scoreboard.BoardUpdateFunction;
import cn.citycraft.PluginHelper.scoreboard.Condition;
import cn.citycraft.PluginHelper.scoreboard.SidebarBoard;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.updater.BodyUpdater;
import pw.yumc.MiaoBoard.scoreboard.updater.TitleUpdater;

/**
 *
 * @since 2016年6月24日 下午3:31:31
 * @author 喵♂呜
 */
public class ScoreBoardManager implements Condition {
    public static Condition cot;
    public static boolean status;
    public static SidebarBoard sbd;
    public static FileConfig config;
    public static List<BoardModel> bms = new ArrayList<>();

    public ScoreBoardManager() {
        status = true;
        cot = this;
        config = MiaoBoardConfig.i().getConfig();
        sbd = new SidebarBoard(PKit.i(), new BoardUpdateFunction(new TitleUpdater(), new BodyUpdater()));
    }

    public static void add(final Player player) {
        sbd.addTarget(player);
    }

    public static List<BoardModel> getModels() {
        return bms;
    }

    public static void load() {
        bms.clear();
        for (final String bmn : config.getConfigurationSection("Boards").getKeys(false)) {
            bms.add(new BoardModel(config.getConfigurationSection("Boards." + bmn)));
        }
    }

    public static void reload() {
        MiaoBoardConfig.reInject();
        load();
    }

    public static void remove(final Player player) {
        sbd.removeTarget(player);
    }

    @Override
    public boolean get() {
        return status;
    }

    public void start() {
        sbd.update(cot, MiaoBoardConfig.UpdateTime);
    }
}
