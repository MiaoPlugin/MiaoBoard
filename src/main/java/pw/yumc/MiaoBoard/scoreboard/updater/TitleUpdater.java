package pw.yumc.MiaoBoard.scoreboard.updater;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.callback.CallBackReturn;
import cn.citycraft.PluginHelper.pluginapi.PluginAPI;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 *
 * @since 2016年7月4日 下午4:47:17
 * @author 喵♂呜
 */
public class TitleUpdater extends CallBackReturn.One<Player, String> {

    @Override
    public String run(final Player param) {
        return PluginAPI.PlaceholderAPI(param, ScoreBoardManager.bm.title);
    }

}
