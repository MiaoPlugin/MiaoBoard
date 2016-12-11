package pw.yumc.MiaoBoard.scoreboard.core;

import java.util.List;

import org.bukkit.entity.Player;

import pw.yumc.YumCore.callback.CallBackReturn;

/**
 *
 * @since 2016年7月4日 下午4:40:21
 * @author 尘曲
 */
public class BoardUpdateFunction {

    private CallBackReturn.One<Player, String> titleFunction;
    private CallBackReturn.One<Player, List<String>> bodyFunction;

    public BoardUpdateFunction(final CallBackReturn.One<Player, String> titleFunction, final CallBackReturn.One<Player, List<String>> bodyFunction) {
        this.titleFunction = titleFunction;
        this.bodyFunction = bodyFunction;
    }

    public CallBackReturn.One<Player, List<String>> getBodyFunction() {
        return bodyFunction;
    }

    public CallBackReturn.One<Player, String> getTitleFunction() {
        return titleFunction;
    }

    public void setBodyFunction(final CallBackReturn.One<Player, List<String>> bodyFunction) {
        this.bodyFunction = bodyFunction;
    }

    public void setTitleFunction(final CallBackReturn.One<Player, String> titleFunction) {
        this.titleFunction = titleFunction;
    }
}
