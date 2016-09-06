package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.List;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class SiderbarBoardPage extends BoardPage {

    private final Objective objective;
    private List<Entry> lastEntries;

    public SiderbarBoardPage() {
        super();
        objective = getBoard().registerNewObjective("default", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public List<Entry> getLastEntries() {
        return lastEntries;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setLastEntries(final List<Entry> lastEntries) {
        this.lastEntries = lastEntries;
    }

}
