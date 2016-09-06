package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class SidebarBoard extends Board {
    private static final TreeMap<Integer, String> coloursById = new TreeMap<>();

    static {
        int i = 0;
        for (final ChatColor chatColor : ChatColor.values()) {
            coloursById.put(i++, chatColor.toString());
        }
    }

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
        if (boardPage == null) {
            return;
        }
        String title = null;
        if (this.getUpdateFunction().getTitleFunction() != null) {
            title = this.getUpdateFunction().getTitleFunction().run(player);
        } else {
            this.removeTarget(player);
            return;
        }
        final List<String> newContents = this.getUpdateFunction().getBodyFunction().run(player);
        this.formatBody(newContents);
        final Objective objective = boardPage.getObjective();
        objective.setDisplayName(title);
        for (int i = 0; i < newContents.size(); i++) {
            objective.getScore(newContents.get(i)).setScore(newContents.size() - i);
        }
        int index = 0;
        if (boardPage.getLastEntries() != null) {
            for (; index < boardPage.getLastEntries().size() && index < newContents.size(); index++) {
                final Entry entry = boardPage.getLastEntries().get(index);
                entry.update(newContents.get(index), newContents.size() - index - 1);
            }
            while (index < boardPage.getLastEntries().size()) {
                final Entry entry = boardPage.getLastEntries().remove(index);
                entry.unregister();
            }
        }
        if (boardPage.getLastEntries() == null) {
            boardPage.setLastEntries(new ArrayList<Entry>());
        }
        for (; index < newContents.size(); index++) {
            final String line = newContents.get(index);
            final Entry entry = new Entry(boardPage, index);
            entry.update(line, newContents.size() - index - 1);
            boardPage.getLastEntries().add(entry);
        }
    }

    private void formatBody(final List<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            final String line = texts.get(i);
            texts.set(i, line + coloursById.get(i));
        }
    }

}
