package net.skyfork.module;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Client;
import net.skyfork.Wrapper;
import net.skyfork.value.AbstractValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:45
 */

@Getter
@Setter
public class Module implements Wrapper {
    private String name;
    private Category category;
    private boolean state;
    private int key;
    private final List<AbstractValue> values = new ArrayList<>();
    public void onEnable() {}
    public void onDisable() {}

    public void setState(boolean state) {
        this.state = state;
        if (state) {
            onEnable();
            Client.eventManager.register(this);
        } else {
            onDisable();
            Client.eventManager.unregister(this);
        }
    }

    public void toggle() {
        this.state = !state;
    }

}
