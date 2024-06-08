package net.skyfork.event.impl.misc;

import com.cubk.event.impl.Event;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa
 * @since 2024/6/8 下午2:10
 */

@Getter
@Setter
public class EventKeyInput implements Event {
    private int key;

    public EventKeyInput(int key) {
        this.key = key;
    }

}
