package net.skyfork.module.impl.render;

import net.skyfork.Client;
import net.skyfork.drag.Dragging;
import net.skyfork.event.EventTarget;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.font.FontManager;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Category;
import net.skyfork.module.Module;

/**
 * @author LangYa
 * @since 2024/07/01/下午2:56
 */
public class LogoMod extends Module {

    private final Dragging pos = Client.getInstance().getDragManager().createDrag(this, "logo", 5, 5);
    private final int textColor = Client.getInstance().getModeManager().isDark() ? 1 : 0;
    private final String renderText = I18n.format("client.name");

    public LogoMod() {
        super(I18n.format("module.render.logo.name"), "Show the logo of the game", Category.Render);
        pos.setWidth(FontManager.S30.getStringWidth(renderText));
        pos.setHeight(FontManager.S30.getHeight());
    }

    @EventTarget
    private void onRender2D(EventRender2D event) {
        FontManager.S30.drawStringWithShadow(renderText, pos.getXPos(), pos.getYPos(),textColor);
    }
}
