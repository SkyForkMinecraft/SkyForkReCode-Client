package net.skyfork.module;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Client;
import net.skyfork.Wrapper;
import net.skyfork.i18n.I18n_Client;
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
    private final String describe;
    private final Category category;
    private boolean state;
    private boolean array;
    private int key;
    private final List<AbstractValue<?>> values = new ArrayList<>();

    public Module(String name, String describe, Category category) {
        this.name = name;
        this.describe = describe;
        this.category = category;
    }

    public Module(String name, Category category) {
        this.name = name;
        this.describe = null;
        this.category = category;
    }

    public String getName() {
        return I18n_Client.format(name);
    }

    public void onEnable() {}
    public void onDisable() {}

    public void setState(boolean state) {
        this.state = state;
        if (state) {
            onEnable();
            Client.getInstance().getEventManager().register(this);
        } else {
            Client.getInstance().getEventManager().unregister(this);
            onDisable();
        }
    }

    public void toggle() {
        setState(!state);
    }

}
