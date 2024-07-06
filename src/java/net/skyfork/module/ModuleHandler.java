package net.skyfork.module;

import net.skyfork.Wrapper;
import net.skyfork.event.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventKeyInput;
import net.skyfork.ui.clickgui.ClickGuiScreen;
import net.skyfork.util.misc.ClientUtil;
import org.lwjgl.input.Keyboard;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:51
 */

public class ModuleHandler implements Wrapper {

    @EventTarget
    private void onKeyInput(EventKeyInput event) {
        Client.getInstance().getModuleManager().getModules().forEach(module ->  {
            if (module.getKey() == event.getKey()) {
                module.toggle();
                ClientUtil.chat(module.getName() + " is " + module.isState());
            }
        });

        if (event.getKey() == Keyboard.KEY_RSHIFT) {
            mc.displayGuiScreen(new ClickGuiScreen());
        }
    }

}
