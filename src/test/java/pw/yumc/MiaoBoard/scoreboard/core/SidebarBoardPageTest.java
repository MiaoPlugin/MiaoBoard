package pw.yumc.MiaoBoard.scoreboard.core;

import org.bukkit.ChatColor;
import org.junit.Test;

import pw.yumc.YumCore.kit.StrKit;

/**
 * @author 喵♂呜
 * @since 2017/6/6
 */

public class SidebarBoardPageTest {

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
        String suffix = "";
        if (value.length() > 16) {
            int splitIndex = value.charAt(15) == '§' ? 15 : 16;
            prefix = StrKit.substring(value, 0, splitIndex);
            suffix = value.substring(splitIndex, value.length());
            // 如果过suffix开头不是颜色符号就把prefix颜色转移到suffix
            if (suffix.charAt(0) != '§') suffix = ChatColor.getLastColors(prefix) + suffix;
            if (suffix.length() > 16) suffix = suffix.substring(16, suffix.length());
        }
        System.out.println("前缀: " + prefix); //设置前16个字符
        System.out.println("后缀: " + suffix);
        System.out.println("====================");
    }
}