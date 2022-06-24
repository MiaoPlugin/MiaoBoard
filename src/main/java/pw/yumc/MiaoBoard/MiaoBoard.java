package pw.yumc.MiaoBoard;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pw.yumc.MiaoBoard.listener.PlayerListener;
import pw.yumc.MiaoBoard.listener.SelfListener;
import pw.yumc.MiaoBoard.misc.Checker;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;
import pw.yumc.YumCore.bukkit.Log;
import pw.yumc.YumCore.commands.CommandSub;
import pw.yumc.YumCore.commands.annotation.Cmd;
import pw.yumc.YumCore.commands.annotation.Help;
import pw.yumc.YumCore.commands.annotation.Sort;
import pw.yumc.YumCore.commands.interfaces.Executor;

/**
 * 喵式记分板主类
 *
 * @author MiaoWoo
 * @since 2016年6月4日 上午9:08:13
 */
public class MiaoBoard extends JavaPlugin implements Executor {
    private ScoreBoardManager scoreBoardManager;

    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }

    @Cmd(permission = "mb.toggle", executor = Cmd.Executor.PLAYER)
    @Help("关闭记分板")
    @Sort(2)
    public void off(Player player) {
        Checker.offList.add(player.getName());
        Log.sender(player, "§c记分板已关闭!");
    }

    @Cmd(permission = "mb.toggle", executor = Cmd.Executor.PLAYER)
    @Help("打开记分板")
    @Sort(1)
    public void on(Player player) {
        Checker.offList.remove(player.getName());
        Log.sender(player, "§a记分板已打开!");
    }

    @Override
    public void onDisable() {
        scoreBoardManager.getSidebarBoard().cancel();
    }

    @Override
    public void onEnable() {
        scoreBoardManager.start();
        new CommandSub("mb", this);
        new PlayerListener();
        new SelfListener();
    }

    @Override
    public void onLoad() {
        scoreBoardManager = new ScoreBoardManager();
    }

    @Cmd(permission = "mb.reload")
    @Help("重新载入配置文件")
    public void reload(CommandSender sender) {
        scoreBoardManager.reload();
        Log.sender(sender, "§a配置重载完毕!");
    }
}
