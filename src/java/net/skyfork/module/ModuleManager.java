package net.skyfork.module;

import lombok.Getter;
import net.skyfork.Client;
import net.skyfork.module.impl.boost.SprintMod;
import net.skyfork.module.impl.render.LogoMod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:50
 */
@Getter
public class ModuleManager {
    private final HashMap<Class<? extends Module>,Module> modules;

    public ModuleManager() {
        modules = new HashMap<>();
        Client.getInstance().getEventManager().register(new ModuleHandler());
    }

    public void init() {
        modules.put(SprintMod.class,new SprintMod());
        modules.put(LogoMod.class,new LogoMod());
      //  modules.put(ModuleListMod.class,new ModuleListMod());
    }

    public List<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modulescategory = new ArrayList<>();
        for(Module module : modules.values()) {
            if(module.getCategory() == category) modulescategory.add(module);
        }
        return modulescategory;
    }

    public Module getModule(String moduleName) {
        modules.get(moduleName);
        for (Module module2 : modules.values()) {
            if (module2.getName() == moduleName) {
                return module2;
            }
        }
        return null;
    }


}
