package net.skyfork.drag;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Wrapper;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.util.misc.MouseUtil;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:33
 */

@Getter
@Setter
public class Drag implements Wrapper {
    private float x, y, width, height;
    private int mouseX, mouseY;
    private boolean dragging = MouseUtil.isHovering(x, y, width, height, mouseX, mouseY);

    public void render(EventRender2D event) {}

    public void update() {
        this.setX(this.mouseX - this.x);
        this.setY(this.mouseY - this.y);
    }

    public void updateMousePos(int mouseX, int mouseY) {
        if (dragging) {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

}
