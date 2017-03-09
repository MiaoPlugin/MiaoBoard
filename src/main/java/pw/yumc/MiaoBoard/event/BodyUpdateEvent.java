package pw.yumc.MiaoBoard.event;

import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 记分板更新事件
 * Created by 蒋天蓓 on 2017/3/7 0007.
 */
public class BodyUpdateEvent extends Event {
    private Player player;
    private List<String> body = Collections.emptyList();

    public BodyUpdateEvent(Player player) {
        this.player = player;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    public Player getPlayer() {

        return player;
    }

    public List<String> getBody() {
        return body;
    }

    private static HandlerList handlerList = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
