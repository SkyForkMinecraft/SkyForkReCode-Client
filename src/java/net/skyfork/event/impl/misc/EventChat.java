package net.skyfork.event.impl.misc;

import net.skyfork.event.impl.Cancellable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa
 * @since 2024/6/9 下午8:15
 */

@Getter
@Setter
public class EventChat implements Cancellable {
    private boolean cancel;
    private String message;

    public EventChat(String message) {
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

}
