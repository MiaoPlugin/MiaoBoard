package pw.yumc.MiaoBoard.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import pw.yumc.MiaoBoard.MiaoBoard;
import pw.yumc.MiaoBoard.event.BodyUpdateEvent;
import pw.yumc.MiaoBoard.event.TitleUpdateEvent;
import pw.yumc.MiaoBoard.misc.Checker;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.text.Replace;

/**
 * 自身记分板监听类
 * Created by 蒋天蓓 on 2017/3/7 0007.
 */
public class SelfListener implements Listener {
    private MiaoBoard plugin = P.getPlugin();

    public SelfListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBodyUpdate(BodyUpdateEvent event) {
        for (BoardModel bmodel : plugin.getScoreBoardManager().getModels()) {
            if (Checker.$(event.getPlayer(), bmodel)) {
                event.setBody(Replace.$(event.getPlayer(), bmodel.lines));
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTitleUpdate(TitleUpdateEvent event) {
        for (BoardModel bmodel : plugin.getScoreBoardManager().getModels()) {
            if (Checker.$(event.getPlayer(), bmodel)) {
                event.setTitle(Replace.$(event.getPlayer(), bmodel.title));
                break;
            }
        }
    }
}
