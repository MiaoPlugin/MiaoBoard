package pw.yumc.MiaoBoard.config;

import java.util.ArrayList;
import java.util.List;

import cn.citycraft.PluginHelper.config.FileConfig;
import cn.citycraft.PluginHelper.config.InjectConfig;
import cn.citycraft.PluginHelper.kit.PKit;
import pw.yumc.MiaoBoard.MiaoBoard;

public class MiaoBoardConfig extends InjectConfig<MiaoBoard> {
    public static Integer UpdateTime;
    public static List<String> DisableWorld = new ArrayList<>();
    public transient static MiaoBoardConfig instance = new MiaoBoardConfig();;

    public MiaoBoardConfig() {
        super((MiaoBoard) PKit.i());
    }

    public static MiaoBoardConfig i() {
        return instance;
    }

    public static void reInject() {
        instance.reload();
    }

    public FileConfig getConfig() {
        return config;
    }
}
