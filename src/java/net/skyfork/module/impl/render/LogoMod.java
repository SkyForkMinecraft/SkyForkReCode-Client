package net.skyfork.module.impl.render;

import net.skyfork.Client;
import net.skyfork.drag.Dragging;
import net.skyfork.event.EventTarget;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.font.FontManager;
import net.skyfork.module.Category;
import net.skyfork.module.Module;

/**
 * @author LangYa
 * @since 2024/07/01/下午2:56
 */
public class LogoMod extends Module {

    private final Dragging pos = Client.getInstance().getDragManager().createDrag(this, "logo", 5, 5);

    public LogoMod() {
        super("module.render.logo.name", "Show the logo of the game", Category.Render);
    }

    @EventTarget
    private void onRender2D(EventRender2D event) {
        pos.setWidth(FontManager.S30.getStringWidth(Client.displayName));
        pos.setHeight(FontManager.S30.getHeight());
        FontManager.S30.drawStringWithShadow(Client.displayName, pos.getXPos(), pos.getYPos(),-1);
    }
}
