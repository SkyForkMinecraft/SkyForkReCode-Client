package net.skyfork.module;

import lombok.Getter;
import net.skyfork.Client;
import net.skyfork.i18n.I18n;
import net.skyfork.module.impl.client.HUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:50
 */
@Getter
public class ModuleManager {
    private final List<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
        register(new HUD(),I18n.format("module.hud"));
        Client.eventManager.register(new ModuleHandler());
    }

    private void register(Module module,String moduleName) {
        module.setName(moduleName);
        module.setState(true);
        modules.add(module);
    }

}
