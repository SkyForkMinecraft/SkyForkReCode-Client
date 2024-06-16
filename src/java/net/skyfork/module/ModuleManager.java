package net.skyfork.module;

import lombok.Getter;
import net.skyfork.Client;
import net.skyfork.module.impl.movement.SprintMod;

import java.util.*;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:50
 */
public class ModuleManager {
    private final Map<Class<? extends Module>, Module> moduleMap;

    public List<Module> getModules() {
        return new ArrayList<>(moduleMap.values());
    }

    public ModuleManager() {
        moduleMap = new HashMap<>();
        Client.eventManager.register(new ModuleHandler());
    }

    public Module getModule(Class<? extends Module> module) {
        return moduleMap.get(module);
    }

    public Module getModule(String moduleName) {
        for (Module module : getModules()) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }
        return null;
    }

    public void register() {
        register(
                new SprintMod()
        );
    }

    private void register(Module ... modules) {
        Arrays.asList(modules).forEach(module -> moduleMap.put(module.getClass(), module));
    }
}
