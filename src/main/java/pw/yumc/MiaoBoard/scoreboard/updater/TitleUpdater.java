package pw.yumc.MiaoBoard.scoreboard.updater;

import java.util.Iterator;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.callback.CallBackReturn;
import cn.citycraft.PluginHelper.kit.StrKit;
import cn.citycraft.PluginHelper.pluginapi.PluginAPI;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 * 记分板标题更新类
 *
 * @since 2016年7月4日 下午4:47:17
 * @author 喵♂呜
 */
public class TitleUpdater extends CallBackReturn.One<Player, String> {

    @Override
    public String run(final Player param) {
        final Iterator<BoardModel> iterator = ScoreBoardManager.getModels().iterator();
        while (iterator.hasNext()) {
            final BoardModel bmodel = iterator.next();
            if (param.hasPermission(bmodel.permission)) {
                return StrKit.substring(PluginAPI.PlaceholderAPI(param, bmodel.title), 0, 40);
            }
        }
        return "";
    }

}
