package pw.yumc.MiaoBoard;

import org.bukkit.plugin.java.JavaPlugin;

import cn.citycraft.PluginHelper.config.FileConfig;

/**
 * 喵式记分板主类
 *
 * @since 2016年6月4日 上午9:08:13
 * @author 喵♂呜
 */
public class MiaoBoard extends JavaPlugin {
    private FileConfig config;

    @Override
    public FileConfig getConfig() {
        return config;
    }

    @Override
    public void onLoad() {
        config = new FileConfig(this);
    }
}
