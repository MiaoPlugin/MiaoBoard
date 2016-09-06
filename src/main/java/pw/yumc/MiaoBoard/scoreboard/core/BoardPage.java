package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public abstract class BoardPage {

    private final Scoreboard board;

    public BoardPage() {
        board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    }

    public Scoreboard getBoard() {
        return board;
    }

}
