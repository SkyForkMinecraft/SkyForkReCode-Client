package net.skyfork.combat;

import net.skyfork.Client;
import net.skyfork.combat.impl.combat.WorldTargetCheck;
import net.skyfork.event.EventManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @since 2024/06/16/下午6:01
 */

public class WorldManager {
    public List<Object> worldChecks;

    public WorldManager() {
        worldChecks = new ArrayList<>();
        init();
    }

    private void init() {
        worldChecks.add(new WorldTargetCheck());

        worldChecks.forEach(EventManager::register);
    }

}

