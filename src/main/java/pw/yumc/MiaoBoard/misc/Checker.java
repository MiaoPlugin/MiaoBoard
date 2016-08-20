package pw.yumc.MiaoBoard.misc;

import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.model.BoardModel;

public class Checker {
    public static boolean $(final Player player, final BoardModel model) {
        return player.hasPermission(model.permission) && dataCheck(model);
    }

    public static boolean dataCheck(final BoardModel model) {
        final long now = System.currentTimeMillis();
        return model.time_start != null && model.time_start.getTime() <= now && model.time_end != null && now <= model.time_end.getTime();
    }
}
