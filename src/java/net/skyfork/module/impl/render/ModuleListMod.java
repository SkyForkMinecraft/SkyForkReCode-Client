package net.skyfork.module.impl.render;

import net.skyfork.Client;
import net.skyfork.drag.Dragging;
import net.skyfork.event.EventTarget;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.font.FontDrawer;
import net.skyfork.font.FontManager;
import net.skyfork.i18n.I18n;
import net.skyfork.module.Category;
import net.skyfork.module.Module;

/**
 * @author LangYa
 * @since 2024/07/04/下午5:43
 */
public class ModuleListMod extends Module {

    private final Dragging pos = Client.getInstance().getDragManager().createDrag(this, "modulelist", 20, 5);
    private final FontDrawer fontRenderer = FontManager.S16;
    private int y = 0;
    private float width = 0;

    public ModuleListMod() {
        super(I18n.format("module.render.modulelist.name"), "Show the module list of the game", Category.Render);
    }

    @EventTarget
    private void onRender2D(EventRender2D event) {
        for (Module module : Client.getInstance().getModuleManager().getModules().values()) {
            if (!module.isState()) continue;
            String moduleSpaceName = module.getSpacedName();
            fontRenderer.drawRightAlignedStringWithShadow(moduleSpaceName, pos.getXPos(), pos.getYPos() + y, -1);
            y += fontRenderer.getHeight();
            float tempWidth = fontRenderer.getStringWidth(moduleSpaceName);
            if (tempWidth > width) {
                width = tempWidth;
                pos.setWidth(width);
            }
            pos.setHeight(y / 2F);
        }
    }
}
