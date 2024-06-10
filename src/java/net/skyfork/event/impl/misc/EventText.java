package net.skyfork.event.impl.misc;

import net.skyfork.event.impl.Cancellable;

/**
 * @author LangYa
 * @since 2024/6/7 下午12:26
 */

public class EventText implements Cancellable {
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

