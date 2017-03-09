package pw.yumc.MiaoBoard.scoreboard.updater;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.MiaoBoard;
import pw.yumc.MiaoBoard.event.BodyUpdateEvent;
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
        BodyUpdateEvent event = new BodyUpdateEvent(param);
        Bukkit.getPluginManager().callEvent(event);
        return event.getBody();
    }

}
