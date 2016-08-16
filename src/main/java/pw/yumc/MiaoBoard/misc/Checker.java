package pw.yumc.MiaoBoard.misc;

import org.bukkit.entity.Player;

import pw.yumc.MiaoBoard.model.BoardModel;

public class Checker {
    public static boolean $(final Player player, final BoardModel model) {
        return player.hasPermission(model.permission);
    }
}
