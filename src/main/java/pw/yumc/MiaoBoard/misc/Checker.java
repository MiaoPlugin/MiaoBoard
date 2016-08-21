package pw.yumc.MiaoBoard.misc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.model.BoardModel;

public class Checker {
    public static List<String> offList = new ArrayList<>();

    public static boolean $(final Player player, final BoardModel model) {
        return player.hasPermission(model.permission) && dataCheck(model) && !offList.contains(player.getName());
    }

    public static boolean dataCheck(final BoardModel model) {
        final long now = System.currentTimeMillis();
        return model.time_start != null && model.time_start.getTime() <= now && model.time_end != null && now <= model.time_end.getTime();
    }
}
