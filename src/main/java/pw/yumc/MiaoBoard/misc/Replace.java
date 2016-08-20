package pw.yumc.MiaoBoard.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;

import cn.citycraft.PluginHelper.kit.StrKit;
import cn.citycraft.PluginHelper.pluginapi.PluginAPI;
import pw.yumc.YumCore.bukkit.P;
import pw.yumc.YumCore.bukkit.compatible.C;

public class Replace {
    public static String $(final Player p, final String text) {
        return s(p(p, text));
    }

    private static String p(final Player p, final String text) {
        return SimpleRelpace.$(p, PluginAPI.PlaceholderAPI(p, text));
    }

    private static String s(final String text) {
        return StrKit.substring(text, 0, 38);
    }

    static class SimpleRelpace {
        private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[%]([^%]+)[%]");
        private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private static final String EMPTY = "";

        public static String $(final Player player, String text) {
            final Matcher m = PLACEHOLDER_PATTERN.matcher(text);
            while (m.find()) {
                final String format = m.group(1);
                if (format.contains("_")) {
                    final String[] ka = format.split("_", 2);
                    String value = format;
                    switch (ka[0]) {
                    case "player":
                        value = player(player, ka[1]);
                        break;
                    case "server":
                        value = server(player, ka[1]);
                        break;
                    case "plugin":
                        value = plugin(player, ka[1]);
                        break;
                    case "time":
                        value = time(player, ka[1]);
                        break;
                    }
                    text = text.replace("%" + format + "%", Matcher.quoteReplacement(value));
                }
            }
            return text;
        }

        private static String player(final Player player, final String key) {
            switch (key) {
            case "x":
                return String.valueOf(player.getLocation().getBlockX());
            case "y":
                return String.valueOf(player.getLocation().getBlockY());
            case "z":
                return String.valueOf(player.getLocation().getBlockZ());
            case "yaw":
                return String.valueOf(Math.round(player.getLocation().getYaw() * 100) / 100);
            case "pitch":
                return String.valueOf(Math.round(player.getLocation().getPitch() * 100) / 100);
            case "world":
                return player.getWorld().getName();
            case "name":
                return player.getName();
            case "displayname":
                return player.getDisplayName();
            case "health":
                return String.valueOf(player.getHealth());
            case "max_health":
                return String.valueOf(player.getMaxHealth());
            default:
                return EMPTY;
            }
        }

        private static String plugin(final Player player, final String key) {
            switch (key) {
            case "version":
                return P.getDescription().getVersion();
            case "name":
                return P.getName();
            case "author":
                return Arrays.toString(P.getDescription().getAuthors().toArray());
            default:
                return EMPTY;
            }
        }

        private static String server(final Player player, final String key) {
            final Runtime runtime = Runtime.getRuntime();
            switch (key) {
            case "online":
                return String.valueOf(C.Player.getOnlinePlayers().size());
            case "ram_used":
                return String.valueOf((runtime.totalMemory() - runtime.freeMemory()) / 1048576L);
            case "ram_free":
                return String.valueOf(runtime.freeMemory() / 1048576L);
            case "ram_total":
                return String.valueOf(runtime.totalMemory() / 1048576L);
            case "ram_max":
                return String.valueOf(runtime.maxMemory() / 1048576L);
            default:
                return EMPTY;
            }
        }

        private static String time(final Player player, final String key) {
            final Date date = new Date();
            if (key.startsWith("left") && key.contains("_")) {
                final String time = key.split("_")[1].replace("`", " ");
                String value = "解析错误";
                try {
                    final long left = df.parse(time).getTime() - System.currentTimeMillis();
                    value = String.valueOf(left / 1000);
                } catch (final ParseException e) {
                }
                return value;
            }
            switch (key) {
            case "now":
                return df.format(date);
            case "year":
                return String.valueOf(date.getYear() + 1900);
            case "month":
                return String.valueOf(date.getMonth() + 1);
            case "day":
                return String.valueOf(date.getDate());
            case "hour":
                return String.valueOf(date.getHours() + 1);
            case "minute":
                return String.valueOf(date.getMinutes());
            case "second":
                return String.valueOf(date.getSeconds());
            }
            return EMPTY;
        }
    }
}
