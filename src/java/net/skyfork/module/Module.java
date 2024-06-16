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
    private final String name;
    private final String spacedName;
    private final String describe;
    private final Category category;
    private boolean state;
    private int key;
    private final List<AbstractValue<?>> values = new ArrayList<>();

    public Module(String spacedName, String describe, Category category) {
        this.name = spacedName.replaceAll(" ", "");
        this.spacedName = spacedName;
        this.describe = describe;
        this.category = category;
    }

    public void onEnable() {}
    public void onDisable() {}

    public void setState(boolean state) {
        this.state = state;
        if (state) {
            onEnable();
            Client.eventManager.register(this);
        } else {
            Client.eventManager.unregister(this);
            onDisable();
        }
    }

    public void toggle() {
        this.state = !state;
    }

}
