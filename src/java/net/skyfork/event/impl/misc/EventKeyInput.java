package net.skyfork.event.impl.misc;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa
 * @since 2024/6/8 下午2:10
 */

@Getter
@Setter
public class EventKeyInput {
    private int key;

    public EventKeyInput(int key) {
        this.key = key;
    }

}
