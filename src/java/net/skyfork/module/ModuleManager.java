package net.skyfork.module;

import lombok.Getter;
import net.skyfork.Client;
import net.skyfork.module.impl.move.SprintMod;
import net.skyfork.module.impl.render.ModuleListMod;
import net.skyfork.module.impl.render.LogoMod;

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
        Client.getInstance().getEventManager().register(new ModuleHandler());
    }

    public void init() {
        addModule(new SprintMod());
        addModule(new LogoMod());
        addModule(new ModuleListMod());
    }

    private void addModule(Module module) {
        for (Module module1 : modules) {
            if (module == module1) return;
        }
        modules.add(module);
    }

    public List<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modulescategory = new ArrayList<>();
        for(Module module : getModules()) {
            if(module.getCategory() == category) modulescategory.add(module);
        }
        return modulescategory;
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


}
