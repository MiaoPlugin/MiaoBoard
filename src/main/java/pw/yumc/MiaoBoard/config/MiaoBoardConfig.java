package pw.yumc.MiaoBoard.config;

import cn.citycraft.PluginHelper.config.FileConfig;
import cn.citycraft.PluginHelper.kit.PKit;
import pw.yumc.MiaoBoard.model.BoardModel;
import pw.yumc.MiaoBoard.scoreboard.ScoreBoardManager;

public class MiaoBoardConfig {
    public static FileConfig config;

    public MiaoBoardConfig() {
        config = new FileConfig(PKit.i());
    }

    public static BoardModel getModel(final String path) {
        return new BoardModel(config.getConfigurationSection(getRoot(path)));
    }

    public static String getRoot(final String path) {
        return "Boards." + path;
    }

    public static void reload() {
        config.reload();
        ScoreBoardManager.bm = getModel("default");
    }
}
