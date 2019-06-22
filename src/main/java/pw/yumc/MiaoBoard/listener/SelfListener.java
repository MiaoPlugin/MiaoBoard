package pw.yumc.MiaoBoard.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 *
 * @author MiaoWoo
 * @date 2017/3/7 0007
 */
public class SelfListener implements Listener {
    private MiaoBoard plugin = P.getPlugin();

    public SelfListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBodyUpdate(BodyUpdateEvent event) {
        for (BoardModel model : plugin.getScoreBoardManager().getModels()) {
            if (Checker.$(event.getPlayer(), model)) {
                List<String> lines = Replace.$(event.getPlayer(), model.lines);
                List<String> temp = new ArrayList<>();
                lines.forEach(s -> temp.addAll(Arrays.asList(s.split("\n"))));
                event.setBody(temp);
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onTitleUpdate(TitleUpdateEvent event) {
        for (BoardModel model : plugin.getScoreBoardManager().getModels()) {
            if (Checker.$(event.getPlayer(), model)) {
                event.setTitle(Replace.$(event.getPlayer(), model.title));
                break;
            }
        }
    }
}
