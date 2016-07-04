package pw.yumc.MiaoBoard.model;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class BoardModel {
    public String title;
    public String permission;
    public List<String> lines = new LinkedList<>();;

    public BoardModel(final ConfigurationSection config) {
        this(config.getString("title"), config.getString("permission"), config.getStringList("lines"));
    }

    public BoardModel(final String title, final String permission, final List<String> lines) {
        this.title = title;
        this.permission = permission;
        this.lines = lines;
    }
}
