package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import pw.yumc.YumCore.kit.StrKit;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class SiderbarBoardPage extends BoardPage {

    private static final List<ChatColor> colors = Arrays.asList(ChatColor.values()); //所有颜色
    private final Objective objective;
    private final List<BoardLine> boardLines = new ArrayList<>();// "行"
    private int maxLine;//用于标注最大行数

    public SiderbarBoardPage() {
        super();
        objective = getBoard().registerNewObjective("default", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0; i < colors.size(); i++) { //循环所有的颜色
            final ChatColor color = colors.get(i);
            final Team team = getBoard().registerNewTeam("MiaoboardLine" + i); //为每个颜色注册一个队伍
            team.addEntry(color.toString()); //为队伍设置一个"行"
            boardLines.add(new BoardLine(color, team)); //将"行"添加至列表
        }
    }

    public Objective getObjective() {
        return objective;
    }

    public void setTitle(String title) {
        objective.setDisplayName(title);
    }

    public void setValue(int line, String value) {
        final BoardLine boardLine = getBoardLine(line); //得到我们的"行"
        Validate.notNull(boardLine, "Unable to find BoardLine with index of " + line + "."); //确认是否存在
        objective.getScore(boardLine.getColor().toString()).setScore(line); //设置"行"
        //分割字符串为前16个和后16个
        String prefix = StrKit.substring(value, 0, 16);
        String suffix = "";
        if (value.length() > 16) {
            suffix = value.substring(16, value.length());
            //处理前后的颜色
            String sufpre = ChatColor.getLastColors(prefix);
            if (value.charAt(15) == '§') {
                sufpre = "§";
            } else if (!suffix.isEmpty() && suffix.charAt(0) == '§') {
                sufpre = "";
            }
            suffix = StrKit.substring(sufpre + suffix, 0, 16);
        }
        boardLine.getTeam().setPrefix(prefix); //设置前16个字符
        boardLine.getTeam().setSuffix(suffix); //设置后16个字符
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
        Validate.notNull(boardLine, "Unable to find BoardLine with index of " + line + "."); //确认是否存在
        getBoard().resetScores(boardLine.getColor().toString()); //删除这个"行"
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

    class BoardLine {
        private final ChatColor color;
        private final Team team;

        public BoardLine(ChatColor color, Team team) {
            this.color = color;
            this.team = team;
        }

        public ChatColor getColor() {
            return color;
        }

        public Team getTeam() {
            return team;
        }

    }
}
