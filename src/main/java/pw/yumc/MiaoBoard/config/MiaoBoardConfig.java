package pw.yumc.MiaoBoard.config;

import java.util.ArrayList;
import java.util.List;

import cn.citycraft.PluginHelper.config.InjectConfig;

/**
 * 记分板配置文件类
 * 
 * @since 2016年7月21日 下午5:21:13
 * @author 喵♂呜
 */
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
