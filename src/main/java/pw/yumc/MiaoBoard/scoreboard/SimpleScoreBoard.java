package pw.yumc.MiaoBoard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

/**
 * 简易记分板
 * 
 * @since 2016年6月13日 下午5:06:46
 * @author 喵♂呜
 */
public class SimpleScoreBoard implements IScoreBoard {
    private final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

}
