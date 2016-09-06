package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public abstract class Board implements Iterable<Player> {

    private final Plugin plugin;
    private int taskId;

    private final HashMap<Player, BoardPage> targets = new HashMap<>();
    private final Set<Player> removeQueue = new HashSet<>();

    private final BoardUpdateFunction updateFunction;

    public Board(final Plugin plugin, final BoardUpdateFunction updateFunction) {
        this.updateFunction = updateFunction;
        this.plugin = plugin;
    }

    public boolean addTarget(final Player player) {
        if (!this.isTarget(player)) {
            final BoardPage boardPage = this.newPage();
            this.targets.put(player, boardPage);
            player.setScoreboard(boardPage.getBoard());
            this.update(player);
            return true;
        }
        return false;
    }

    public void cancel() {
        if (taskId != 0) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = 0;
            for (final Player player : this.targets.keySet()) {
                player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
            this.targets.clear();
            this.removeQueue.clear();
        }
    }

    public BoardPage getBoardPage(final Player player) {
        return this.targets.get(player);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Set<Player> getTargets() {
        return targets.keySet();
    }

    public BoardUpdateFunction getUpdateFunction() {
        return updateFunction;
    }

    public boolean isRunning() {
        return this.taskId != 0;
    }

    public boolean isTarget(final Player player) {
        return this.targets.containsKey(player);
    }

    @Override
    public Iterator<Player> iterator() {
        return this.targets.keySet().iterator();
    }

    public abstract BoardPage newPage();

    public boolean removeTarget(final Player player) {
        if (this.isTarget(player)) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return this.removeQueue.add(player);
        }
        return false;
    }

    public void update(final Condition condition, final int interval) {
        taskId = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

            @Override
            public void run() {
                if (condition.get()) {
                    final Iterator<Player> iterator = iterator();
                    while (iterator.hasNext()) {
                        final Player next = iterator.next();
                        if (shouldRemove(next)) {
                            iterator.remove();
                        } else {
                            update(next);
                        }
                    }
                } else {
                    cancel();
                }
            }
        }, 0, interval).getTaskId();
    }

    public abstract void update(Player p);

    private boolean shouldRemove(final Player player) {
        return this.removeQueue.remove(player);
    }

}
