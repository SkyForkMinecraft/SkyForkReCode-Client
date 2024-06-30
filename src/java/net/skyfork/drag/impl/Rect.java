package net.skyfork.drag.impl;

import net.skyfork.drag.Drag;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.util.render.RenderUtil;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:49
 */

public class Rect extends Drag {

    public Rect() {
        setX(50);
        setY(50);
        setWidth(50);
        setHeight(50);
    }

    @Override
    public void render(EventRender2D event) {
        RenderUtil.drawRect(0,0,50,50,-1);
    }

}
