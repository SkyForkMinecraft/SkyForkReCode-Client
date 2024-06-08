package net.skyfork.module;

import lombok.Getter;
import net.skyfork.Client;

import java.util.ArrayList;
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
        Client.eventManager.register(new ModuleHandler());
    }

    private void register(Module module,String moduleName) {
        module.setName(moduleName);
        modules.add(module);
        // 后面写clickgui
        module.setState(true);
    }

}
