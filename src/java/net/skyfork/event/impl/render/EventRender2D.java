package net.skyfork.event.impl.render;

import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;
import net.skyfork.event.impl.Event;

/**
 * @author LangYa
 * @since 2024/6/6 下午8:36
 */

@Getter
public class EventRender2D implements Event {
    private final ScaledResolution scaledResolution;
    private final float partialTicks;

    public EventRender2D(ScaledResolution sr, float partialTicks) {
        this.scaledResolution = sr;
        this.partialTicks = partialTicks;
    }

}
