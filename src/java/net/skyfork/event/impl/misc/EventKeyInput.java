package net.skyfork.event.impl.misc;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.event.impl.Event;

/**
 * @author LangYa
 * @since 2024/6/8 下午2:10
 */

@Getter
@Setter
public class EventKeyInput implements Event {
    private int key;
    private boolean keyState;

    public EventKeyInput(int key, final boolean keyState) {
        this.key = key;
        this.keyState = keyState;
    }
}
