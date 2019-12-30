package pw.yumc.MiaoBoard.model;

import java.util.Date;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import pw.yumc.YumCore.config.annotation.ConfigNode;
import pw.yumc.YumCore.config.annotation.Nullable;
import pw.yumc.YumCore.config.inject.InjectConfigurationSection;

/**
 * 记分板数据模型类
 *
 * @author MiaoWoo
 * @since 2016年7月21日 下午6:58:58
 */
public class BoardModel extends InjectConfigurationSection {
    public transient String name;
    public Integer index;
    @Nullable
    @ConfigNode("time.start")
    public Date time_start;
    @Nullable
    @ConfigNode("time.end")
    public Date time_end;
    public String title;
    public String permission;
    public List<String> lines;

    /**
     * 自动载入配置
     *
     * @param name
     *         记分板名称
     * @param config
     *         记分板配置
     */
    public BoardModel(String name, final ConfigurationSection config) {
        super(config);
        this.name = name;
    }
}
