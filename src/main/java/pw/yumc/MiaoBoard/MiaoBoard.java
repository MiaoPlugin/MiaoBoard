package pw.yumc.MiaoBoard;

import org.bukkit.plugin.java.JavaPlugin;

import cn.citycraft.PluginHelper.commands.HandlerCommand;
import cn.citycraft.PluginHelper.commands.HandlerCommands;
import cn.citycraft.PluginHelper.config.FileConfig;

/**
 * 喵式记分板主类
 *
 * @since 2016年6月4日 上午9:08:13
 * @author 喵♂呜
 */
public class MiaoBoard extends JavaPlugin implements HandlerCommands {
    private FileConfig config;

    @Override
    public FileConfig getConfig() {
        return config;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onLoad() {
        config = new FileConfig(this);
    }

    @HandlerCommand(name = "reload", description = "重新载入配置文件")
    public void reload() {
        config.reload();
    }
}
