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

    public Module getModule(Module module) {
        for (Module module2 : modules) {
            if (module == module2) {
                return module2;
            }
        }
        return null;
    }

    public Module getModule(String moduleName) {
        for (Module module2 : modules) {
            if (module2.getName() == moduleName) {
                return module2;
            }
        }
        return null;
    }

    private void register(Module module,String moduleName) {
        module.setName(moduleName);
        modules.add(module);
        // 后面写clickgui
        module.setState(true);
    }

}
