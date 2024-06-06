package net.skyfork.module.impl.client;

import com.cubk.event.annotations.EventTarget;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Module;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:49
 */

public class HUD extends Module {
    @EventTarget
    public void onRender2D(EventRender2D event) {
        mc.fontRendererObj.drawString(I18n.format("client.name"), 10, 10, -1);
    }
}
