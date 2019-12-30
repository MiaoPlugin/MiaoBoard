package pw.yumc.MiaoBoard.config;

import java.util.Collections;
import java.util.List;

import pw.yumc.YumCore.config.inject.InjectConfig;

/**
 * 记分板配置文件类
 *
 * @author MiaoWoo
 * @since 2016年7月21日 下午5:21:13
 */
public class MiaoBoardConfig extends InjectConfig {
    public transient static MiaoBoardConfig instance = new MiaoBoardConfig();
    public Integer UpdateTime;
    public List<String> DisableWorld = Collections.emptyList();

    public static MiaoBoardConfig i() {
        return instance;
    }

    public static void reInject() {
        instance.reload();
    }
}
