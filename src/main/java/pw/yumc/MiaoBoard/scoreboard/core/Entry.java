package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

public class Entry {
    private final SiderbarBoardPage boardPage;
    private Team team;
    private final String id;
    private String last;
    private final int index;

    public Entry(final SiderbarBoardPage boardPage, final int index) {
        this.boardPage = boardPage;
        this.id = String.valueOf(index);
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public Team getTeam() {
        return team;
    }

    public void unregister() {
        if (this.last != null) {
            this.boardPage.getBoard().resetScores(last);
        }
    }

    public void update(final String text, final int score) {
        this.unregister();
        final Objective objective = boardPage.getObjective();
        final Score scoreObject = objective.getScore(text);
        scoreObject.setScore(score);
        this.last = text;
    }
}