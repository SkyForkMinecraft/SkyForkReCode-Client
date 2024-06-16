package net.skyfork.drag;

import net.skyfork.event.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.util.render.GLUtils;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:52
 */

public class DragHandler {

    @EventTarget
    private void onRender2D(EventRender2D event) {
        GLUtils.pushMatrix();
        Client.getInstance().getDragManager().getDragList().forEach(drag -> {
            // GLUtils.translated(drag.getX(),drag.getY(),0);
            drag.render(event);
        });
        GLUtils.popMatrix();
    }

}
