package net.skyfork.module;

import com.cubk.event.annotations.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.EventTick;

import java.util.Map;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:51
 */

public class ModuleHandler {

    @EventTarget
    public void onTick(EventTick event) {
        for (Module module : Client.moduleManager.getModules()) {
            if (module.isState()) {
                module.onEnable();
                Client.eventManager.register(module);
            } else {
                module.onDisable();
                Client.eventManager.unregister(module);
            }
        }
    }

}
