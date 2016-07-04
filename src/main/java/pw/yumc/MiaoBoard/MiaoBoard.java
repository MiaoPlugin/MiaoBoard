package pw.yumc.MiaoBoard;

import org.bukkit.plugin.java.JavaPlugin;

import cn.citycraft.PluginHelper.commands.HandlerCommand;
import cn.citycraft.PluginHelper.commands.HandlerCommands;
import cn.citycraft.PluginHelper.commands.InvokeCommandEvent;
import cn.citycraft.PluginHelper.commands.InvokeSubCommand;
import pw.yumc.MiaoBoard.config.MiaoBoardConfig;
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
        ScoreBoardManager.status = false;
    }

    @Override
    public void onEnable() {
        new InvokeSubCommand(this, "mb").registerCommands(this);
        new ScoreBoardManager().start();
        new PlayerListener();
    }

    @Override
    public void onLoad() {
        new MiaoBoardConfig();
    }

    @HandlerCommand(name = "reload", description = "重新载入配置文件")
    public void reload(final InvokeCommandEvent e) {
        MiaoBoardConfig.reload();
        e.getSender().sendMessage("§a配置重载完毕!");
    }
}
