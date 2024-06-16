package net.skyfork.combat.impl.combat;

import net.minecraft.entity.Entity;
import net.skyfork.Wrapper;

/**
 * @author LangYa
 * @since 2024/06/16/下午6:02
 */
public class WorldTargetCheck implements Wrapper {

    public static Entity target;

    public static Entity getSingleTarget(float range) {
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (!entity.isDead && entity.getDistanceToEntity(mc.thePlayer) <= range) {
                target = entity;
            }
        }
        return target;
    }

}
