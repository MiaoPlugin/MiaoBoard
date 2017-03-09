package pw.yumc.MiaoBoard.scoreboard.updater;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.MiaoBoard;
import pw.yumc.MiaoBoard.event.TitleUpdateEvent;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.callback.CallBackReturn;

/**
 * 记分板标题更新类
 *
 * @since 2016年7月4日 下午4:47:17
 * @author 喵♂呜
 */
public class TitleUpdater extends CallBackReturn.One<Player, String> {
    MiaoBoard plugin = P.getPlugin();

    @Override
    public String run(final Player param) {
        TitleUpdateEvent event = new TitleUpdateEvent(param);
        Bukkit.getPluginManager().callEvent(event);
        return event.getTitle();
    }

}
