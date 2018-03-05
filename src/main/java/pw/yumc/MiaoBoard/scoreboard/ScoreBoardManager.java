package pw.yumc.MiaoBoard.scoreboard;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.core.Condition;
import pw.yumc.MiaoBoard.scoreboard.core.SidebarBoard;
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
    private Status cot = new Status();
    private SidebarBoard sbd = new SidebarBoard(P.instance);
    private FileConfig config = MiaoBoardConfig.i().getConfig();
    private List<BoardModel> bms = new LinkedList<>();

    public ScoreBoardManager() {
        load();
    }

    public void addTarget(final Player player) {
        if (!MiaoBoardConfig.i().DisableWorld.contains(player.getWorld().getName())) {
            Bukkit.getScheduler().runTask(P.instance, () -> getSidebarBoard().addTarget(player));
        }
    }

    public List<BoardModel> getModels() {
        return bms;
    }

    public SidebarBoard getSidebarBoard() {
        return sbd;
    }

    private void load() {
        bms.clear();
        config.getConfigurationSection("Boards").getKeys(false).forEach(bmn -> bms.add(new BoardModel(bmn, config.getConfigurationSection("Boards." + bmn))));
        bms.sort(Comparator.comparing(o -> o.index));
    }

    public void reload() {
        sbd.cancel();
        MiaoBoardConfig.reInject();
        load();
        start();
    }

    public void removeTarget(final Player player) {
        sbd.removeTarget(player);
    }

    public void start() {
        sbd.update(cot.set(true), MiaoBoardConfig.i().UpdateTime);
        C.Player.getOnlinePlayers().forEach(this::addTarget);
    }

    private class Status implements Condition {
        private boolean status = true;

        @Override
        public boolean get() {
            return status;
        }

        Status set(final boolean status) {
            this.status = status;
            return this;
        }
    }
}
