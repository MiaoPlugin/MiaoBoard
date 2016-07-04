package pw.yumc.MiaoBoard.scoreboard.updater;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.callback.CallBackReturn;
import cn.citycraft.PluginHelper.pluginapi.PluginAPI;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 *
 * @since 2016年7月4日 下午4:47:17
 * @author 喵♂呜
 */
public class BodyUpdater extends CallBackReturn.One<Player, List<String>> {

    @Override
    public List<String> run(final Player param) {
        final List<String> temp = new LinkedList<>();
        for (final String line : ScoreBoardManager.bm.lines) {
            temp.add(PluginAPI.PlaceholderAPI(param, line));
        }
        return temp;
    }

}
