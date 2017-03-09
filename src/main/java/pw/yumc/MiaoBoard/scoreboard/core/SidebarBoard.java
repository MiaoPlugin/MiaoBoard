package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pw.yumc.MiaoBoard.event.BodyUpdateEvent;
import pw.yumc.MiaoBoard.event.TitleUpdateEvent;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class SidebarBoard extends Board {
    public SidebarBoard(final Plugin plugin) {
        super(plugin);
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
        TitleUpdateEvent te = new TitleUpdateEvent(player);
        Bukkit.getPluginManager().callEvent(te);
        String title = te.getTitle();
        if (title == null) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            return;
        }
        boardPage.setTitle(title);
        BodyUpdateEvent be = new BodyUpdateEvent(player);
        Bukkit.getPluginManager().callEvent(be);
        boardPage.setBody(be.getBody());
        player.setScoreboard(boardPage.getBoard());
    }
}
