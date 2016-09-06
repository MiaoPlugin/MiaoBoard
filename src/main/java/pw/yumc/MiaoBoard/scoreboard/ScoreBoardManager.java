package pw.yumc.MiaoBoard.scoreboard;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.core.BoardUpdateFunction;
import pw.yumc.MiaoBoard.scoreboard.core.Condition;
import pw.yumc.MiaoBoard.scoreboard.core.SidebarBoard;
import pw.yumc.MiaoBoard.scoreboard.updater.BodyUpdater;
import pw.yumc.MiaoBoard.scoreboard.updater.TitleUpdater;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.bukkit.compatible.C;
import pw.yumc.YumCore.config.FileConfig;

/**
 * 记分板管理类
 *
 * @since 2016年6月24日 下午3:31:31
 * @author 喵♂呜
 */
public class ScoreBoardManager {
    public Status cot = new Status();
    public SidebarBoard sbd = new SidebarBoard(P.instance, new BoardUpdateFunction(new TitleUpdater(), new BodyUpdater()));
    public FileConfig config = MiaoBoardConfig.i().getConfig();
    public List<BoardModel> bms = new LinkedList<>();

    public ScoreBoardManager() {
        load();
    }

    public void addTarget(final Player player) {
        if (!MiaoBoardConfig.i().getDisableWorld().contains(player.getWorld().getName())) {
            getSidebarBoard().addTarget(player);
        }
    }

    public List<BoardModel> getModels() {
        return bms;
    }

    public SidebarBoard getSidebarBoard() {
        return sbd;
    }

    public void load() {
        bms.clear();
        for (final String bmn : config.getConfigurationSection("Boards").getKeys(false)) {
            bms.add(new BoardModel(config.getConfigurationSection("Boards." + bmn)).setName(bmn));
        }
        Collections.sort(bms, new BoardComparator());
    }

    public void reload() {
        sbd.cancel();
        MiaoBoardConfig.reInject();
        load();
        start();
    }

    public void start() {
        sbd.update(cot.set(true), MiaoBoardConfig.i().getUpdateTime());
        for (final Player player : C.Player.getOnlinePlayers()) {
            addTarget(player);
        }
    }

    private class BoardComparator implements Comparator<BoardModel> {
        @Override
        public int compare(final BoardModel o1, final BoardModel o2) {
            return o1.index.compareTo(o2.index);
        }
    }

    private class Status implements Condition {
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
