package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.ChatColor;
import org.junit.Test;

import pw.yumc.YumCore.kit.StrKit;

/**
 * @author 喵♂呜
 * @since 2017/6/6
 */

public class SiderbarBoardPageTest {

    @Test
    public void testSubStr() {
        substr("§aaaaaaaaaaa§bcdepqrstuvwxyz");
        substr("§aaaaaaaaaaab§cdefqrstuvwxyz");
        substr("§aaaaaaaaaaabc§defqrstuvwxyz");
        substr("§aaaaaaaaaaabcd§efqrstuvwxyz");
        substr("§aaaaaaaaaaabcde§fqrstuvwxyz");
    }

    public void substr(String value) {
        System.out.println("变量: " + value);
        String prefix = StrKit.substring(value, 0, 16);
        System.out.println("前缀: " + prefix); //设置前16个字符
        String suffix = "";
        if (value.length() > 16) {
            suffix = value.substring(16, value.length());
            //处理前后的颜色
            String sufpre = ChatColor.getLastColors(prefix);
            if (value.charAt(15) == '§') {
                sufpre = "§";
            } else if (!suffix.isEmpty() && suffix.charAt(0) == '§') {
                sufpre = "";
            }
            suffix = StrKit.substring(sufpre + suffix, 0, 16);
        }
        System.out.println("后缀: " + suffix);
        System.out.println("====================");
    }
}