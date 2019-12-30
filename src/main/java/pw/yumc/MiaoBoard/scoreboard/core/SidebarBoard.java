package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pw.yumc.MiaoBoard.event.BodyUpdateEvent;
import pw.yumc.MiaoBoard.event.TitleUpdateEvent;

/**
 * @author 尘曲
 * @since 2016年7月4日 下午4:40:21
 */
public class SidebarBoard extends Board {
    public SidebarBoard(final Plugin plugin) {
        super(plugin);
    }

    @Override
    public SidebarBoardPage getBoardPage(final Player player) {
        return (SidebarBoardPage) super.getBoardPage(player);
    }

    @Override
    public SidebarBoardPage newPage() {
        return new SidebarBoardPage();
    }

    @Override
    public void update(final Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
            final SidebarBoardPage boardPage = this.getBoardPage(player);
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
        });
    }
}
