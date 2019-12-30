package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import pw.yumc.MiaoBoard.misc.FakePlayer;
import pw.yumc.YumCore.kit.StrKit;

/**
 * @author 尘曲
 * @since 2016年7月4日 下午4:40:21
 */
public class SidebarBoardPage extends BoardPage {
    private static boolean newVer = true;

    static {
        try {
            Team.class.getDeclaredMethod("addEntry", String.class);
        } catch (NoSuchMethodException e) {
            newVer = false;
        }
    }

    private static final List<ChatColor> COLORS = Arrays.asList(ChatColor.values());
    private static final int BOARD_LINE_MAX_CHARS = 16;
    private static final int BOARD_LINE_MAX_CHARS_SUB1 = BOARD_LINE_MAX_CHARS - 1;
    private final Objective objective;
    private final List<BoardLine> boardLines = new ArrayList<>();
    private int maxLine;

    public SidebarBoardPage() {
        super();
        objective = getBoard().registerNewObjective("default", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0; i < COLORS.size(); i++) {
            final String name = COLORS.get(i) + "" + ChatColor.RESET;
            final Team team = getBoard().registerNewTeam("MiaoboardLine" + i);
            boardLines.add(new BoardLine(name, team));
        }
    }

    public Objective getObjective() {
        return objective;
    }

    public void setTitle(String title) {
        objective.setDisplayName(title);
    }

    public void setValue(int line, String value) {
        final BoardLine boardLine = getBoardLine(line);
        Validate.notNull(boardLine, "Unable to find BoardLine with index of " + line + ".");
        objective.getScore(boardLine.getName()).setScore(line);
        //分割字符串为前16个和后16个
        String prefix = value;
        String suffix = "";
        if (value.length() > BOARD_LINE_MAX_CHARS) {
            int splitIndex = value.charAt(BOARD_LINE_MAX_CHARS_SUB1) == ChatColor.COLOR_CHAR ? BOARD_LINE_MAX_CHARS_SUB1 : BOARD_LINE_MAX_CHARS;
            prefix = StrKit.substring(value, 0, splitIndex);
            suffix = value.substring(splitIndex);
            // 如果过suffix开头不是颜色符号就把prefix颜色转移到suffix
            if (suffix.charAt(0) != ChatColor.COLOR_CHAR) {
                suffix = ChatColor.getLastColors(prefix) + suffix;
            }
            if (suffix.length() > BOARD_LINE_MAX_CHARS) {
                suffix = StrKit.substring(suffix, 0, BOARD_LINE_MAX_CHARS);
            }
        }
        boardLine.getTeam().setPrefix(prefix);
        boardLine.getTeam().setSuffix(suffix);
        maxLine = line + 1;
    }

    //all 5  [0 1 2 3 4] maxLine = 5  all 3 [0 1 2] maxLine=4
    public void clear(int size) {
        if (maxLine > size) {
            for (int i = size; i < maxLine; i++) {
                removeLine(i);
            }
            maxLine = size;
        }
    }

    public void removeLine(int line) {
        final BoardLine boardLine = getBoardLine(line);
        Validate.notNull(boardLine, "Unable to find BoardLine with index of " + line + ".");
        getBoard().resetScores(boardLine.getName());
    }

    private BoardLine getBoardLine(int line) {
        return boardLines.get(line);
    }

    public void setBody(List<String> newContents) {
        for (int i = 0; i < newContents.size(); i++) {
            setValue(newContents.size() - i, newContents.get(i));
        }
        clear(newContents.size());
    }

    static class BoardLine {
        private String name;
        private Team team;
        private OfflinePlayer player;

        public BoardLine(String name, Team team) {
            this.name = name;
            this.team = team;
            this.player = new FakePlayer(name);
            addEntry();
        }

        public void addEntry() {
            if (newVer) {
                team.addEntry(name);
            } else {
                team.addPlayer(player);
            }
        }

        public String getName() {
            return name;
        }

        public Team getTeam() {
            return team;
        }
    }
}
