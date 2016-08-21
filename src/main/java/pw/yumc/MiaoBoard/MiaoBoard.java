package pw.yumc.MiaoBoard;

import org.bukkit.plugin.java.JavaPlugin;

import pw.yumc.MiaoBoard.listener.PlayerListener;
import pw.yumc.MiaoBoard.misc.Checker;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;
import pw.yumc.YumCore.commands.CommandArgument;
import pw.yumc.YumCore.commands.CommandExecutor;
import pw.yumc.YumCore.commands.CommandManager;
import pw.yumc.YumCore.commands.annotation.Cmd;
import pw.yumc.YumCore.commands.annotation.Help;
import pw.yumc.YumCore.commands.annotation.Sort;
import pw.yumc.YumCore.statistic.Statistics;
import pw.yumc.YumCore.update.SubscribeTask;

/**
 * 喵式记分板主类
 *
 * @since 2016年6月4日 上午9:08:13
 * @author 喵♂呜
 */
public class MiaoBoard extends JavaPlugin implements CommandExecutor {

    @Cmd()
    @Help("关闭记分板")
    @Sort(2)
    public void off(final CommandArgument e) {
        Checker.offList.add(e.getSender().getName());
        e.getSender().sendMessage("§c记分板已关闭!");
    }

    @Cmd()
    @Help("打开记分板")
    @Sort(1)
    public void on(final CommandArgument e) {
        Checker.offList.remove(e.getSender().getName());
        e.getSender().sendMessage("§a记分板已打开!");
    }

    @Override
    public void onDisable() {
        ScoreBoardManager.getSidebarBoard().cancel();
    }

    @Override
    public void onEnable() {
        ScoreBoardManager.start();
        new CommandManager("mb").register(this);
        new PlayerListener();
        new Statistics();
        new SubscribeTask(true, true);
    }

    @Override
    public void onLoad() {
        ScoreBoardManager.load();
    }

    @Cmd(permission = "mb.reload")
    @Help("重新载入配置文件")
    public void reload(final CommandArgument e) {
        ScoreBoardManager.reload();
        e.getSender().sendMessage("§a配置重载完毕!");
    }
}
