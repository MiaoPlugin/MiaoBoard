package pw.yumc.MiaoBoard.scoreboard.updater;

import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.MiaoBoard;
import pw.yumc.MiaoBoard.misc.Checker;
import pw.yumc.MiaoBoard.misc.Replace;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.callback.CallBackReturn;

/**
 * 记分板行更新类
 *
 * @since 2016年7月4日 下午4:47:17
 * @author 喵♂呜
 */
public class BodyUpdater extends CallBackReturn.One<Player, List<String>> {
    MiaoBoard plugin = P.getPlugin();

    @Override
    public List<String> run(final Player param) {
        for (BoardModel bmodel : plugin.getScoreBoardManager().getModels()) {
            if (Checker.$(param, bmodel)) { return Replace.$(param, bmodel.lines); }
        }
        return Collections.emptyList();
    }

}
