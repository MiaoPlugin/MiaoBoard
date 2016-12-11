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
 * @since 2016年7月21日 下午6:58:58
 * @author 喵♂呜
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
     * @param config
     */
    public BoardModel(final ConfigurationSection config) {
        super(config);
    }

    /**
     * 设置模型名称
     *
     * @param name
     *            名称
     * @return {@link BoardModel}
     */
    public BoardModel setName(final String name) {
        this.name = name;
        return this;
    }
}
