package pw.yumc.MiaoBoard;

import java.net.MalformedURLException;

import org.bukkit.plugin.java.JavaPlugin;

import cn.citycraft.PluginHelper.commands.HandlerCommand;
import cn.citycraft.PluginHelper.commands.HandlerCommands;
import cn.citycraft.PluginHelper.commands.InvokeCommandEvent;
import cn.citycraft.PluginHelper.commands.InvokeSubCommand;
import cn.citycraft.PluginHelper.kit.PluginKit;
import cn.citycraft.PluginHelper.utils.VersionChecker;
import pw.yumc.MiaoBoard.listener.PlayerListener;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

/**
 * 喵式记分板主类
 *
 * @since 2016年6月4日 上午9:08:13
 * @author 喵♂呜
 */
public class MiaoBoard extends JavaPlugin implements HandlerCommands {

    @Override
    public void onDisable() {
        ScoreBoardManager.getSidebarBoard().cancel();
    }

    @Override
    public void onEnable() {
        new InvokeSubCommand(this, "mb").registerCommands(this);
        new PlayerListener();
        try {
            new VersionChecker(this, "aHR0cDovL2NpLnl1bWMucHcvam9iL01pYW9Cb2FyZC9sYXN0U3VjY2Vzc2Z1bEJ1aWxkL2FydGlmYWN0L3BvbS54bWw=".getBytes());
            ScoreBoardManager.start();
        } catch (final MalformedURLException e) {
            PluginKit.disable("DON'T TRY TO CHANGE THE URL...");
            throw new IllegalAccessError("Access Check Failed...");
        }
    }

    @Override
    public void onLoad() {
        ScoreBoardManager.load();
    }

    @HandlerCommand(name = "reload", description = "重新载入配置文件")
    public void reload(final InvokeCommandEvent e) {
        ScoreBoardManager.reload();
        e.getSender().sendMessage("§a配置重载完毕!");
    }
}
