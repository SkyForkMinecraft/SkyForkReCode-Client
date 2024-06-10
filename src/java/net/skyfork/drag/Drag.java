package net.skyfork.drag;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Wrapper;
import net.skyfork.event.impl.render.EventRender2D;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:33
 */

@Getter
@Setter
public class Drag implements Wrapper {
    private float x, y, width, height;
    private boolean dragging;

    public void render(EventRender2D event) {}
}
