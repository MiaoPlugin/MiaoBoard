package pw.yumc.MiaoBoard.model;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import cn.citycraft.PluginHelper.config.InjectConfigurationSection;

/**
 * 记分板数据模型类
 *
 * @since 2016年7月21日 下午6:58:58
 * @author 喵♂呜
 */
public class BoardModel extends InjectConfigurationSection {
    public int index;
    public String title;
    public String permission;
    public List<String> lines;

    /**
     * 自动载入配置
     *
     * @param config
     */
    public BoardModel(final ConfigurationSection config) {
        super(config);
    }
}
