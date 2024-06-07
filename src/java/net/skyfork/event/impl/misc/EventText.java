package net.skyfork.event.impl.misc;

import com.cubk.event.impl.Cancellable;
import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @since 2024/6/7 下午12:26
 */

public class EventText implements Cancellable, Event {
    private boolean cancel;
    public String text;

    public EventText(String text) {
        this.text = text;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean state) {
        this.cancel = state;
    }
}

