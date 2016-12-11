package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class SidebarBoard extends Board {
    public SidebarBoard(final Plugin plugin, final BoardUpdateFunction updateFunction) {
        super(plugin, updateFunction);
    }

    @Override
    public SiderbarBoardPage getBoardPage(final Player player) {
        return (SiderbarBoardPage) super.getBoardPage(player);
    }

    @Override
    public SiderbarBoardPage newPage() {
        return new SiderbarBoardPage();
    }

    @Override
    public void update(final Player player) {
        final SiderbarBoardPage boardPage = this.getBoardPage(player);
        if (boardPage == null) { return; }
        String title = null;
        if (this.getUpdateFunction().getTitleFunction() != null) {
            title = this.getUpdateFunction().getTitleFunction().run(player);
        }
        if (title == null) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            return;
        }
        boardPage.setTitle(title);
        boardPage.setBody(getUpdateFunction().getBodyFunction().run(player));
        player.setScoreboard(boardPage.getBoard());
    }
}
