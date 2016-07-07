package pw.yumc.MiaoBoard.config;

import java.util.ArrayList;
import java.util.List;

import cn.citycraft.PluginHelper.config.InjectConfig;

public class MiaoBoardConfig extends InjectConfig {
    public static Integer UpdateTime;
    public static List<String> DisableWorld = new ArrayList<>();
    public transient static MiaoBoardConfig instance = new MiaoBoardConfig();;

    public static MiaoBoardConfig i() {
        return instance;
    }

    public static void reInject() {
        instance.reload();
    }
}
