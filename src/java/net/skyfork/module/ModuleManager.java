package net.skyfork.module;

import lombok.Getter;
import net.skyfork.event.EventManager;
import net.skyfork.i18n.I18n;
import net.skyfork.module.hack.combat.KillAura;

import java.util.HashMap;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:50
 */
@Getter
public class ModuleManager {
    private final HashMap<String,Module> modules;

    public ModuleManager() {
        modules = new HashMap<>();
        EventManager.register(new ModuleHandler());
        init();
    }

    private void init() {
        register(new KillAura(), I18n.format(""));
    }

    public Module getModule(Module module) {
        for (Module module2 : modules.values()) {
            if (module == module2) {
                return module2;
            }
        }
        return null;
    }

    public Module getModule(String moduleName) {
        for (Module module2 : modules.values()) {
            if (module2.getName() == moduleName) {
                return module2;
            }
        }
        return null;
    }

    private void register(Module module,String moduleName) {
        modules.put(moduleName,module);
        module.setState(true);
    }

}
