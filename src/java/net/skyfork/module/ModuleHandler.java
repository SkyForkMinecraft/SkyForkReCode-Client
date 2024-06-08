package net.skyfork.module;

import com.cubk.event.annotations.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventKeyInput;
import net.skyfork.util.misc.ClientUtil;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:51
 */

public class ModuleHandler {

    @EventTarget
    public void onKeyInput(EventKeyInput event) {
        for (Module module : Client.moduleManager.getModules()) {
            if (module.getKey() == event.getKey()) {
                module.toggle();
                ClientUtil.chat(module.getName() + " is " + module.isState());
            }
        }
    }

}
