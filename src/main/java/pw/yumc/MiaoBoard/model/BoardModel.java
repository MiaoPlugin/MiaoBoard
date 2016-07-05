package pw.yumc.MiaoBoard.model;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import cn.citycraft.PluginHelper.config.InjectConfigurationSection;

public class BoardModel extends InjectConfigurationSection {
    public String title;
    public String permission;
    public List<String> lines = new LinkedList<>();

    /**
     * 自动载入配置
     * 
     * @param config
     */
    public BoardModel(final ConfigurationSection config) {
        super(config);
    }
}
