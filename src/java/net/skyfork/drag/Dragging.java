package net.skyfork.drag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import net.skyfork.Wrapper;
import net.skyfork.module.Module;
import net.skyfork.util.animations.Animation;
import net.skyfork.util.animations.Direction;
import net.skyfork.util.animations.impl.DecelerateAnimation;
import net.skyfork.util.misc.ColorUtil;
import net.skyfork.util.misc.MouseUtil;
import net.skyfork.util.render.RoundedUtil;

import java.awt.*;

@Getter
@Setter
public class Dragging implements Wrapper {
    @Expose
    @SerializedName("x")
    private float xPos;
    @Expose
    @SerializedName("y")
    private float yPos;

    public float initialXVal;
    public float initialYVal;

    private float startX, startY;
    private boolean dragging;

    private float width, height;

    @Expose
    @SerializedName("name")
    private String name;

    private final Module module;

    public Animation hoverAnimation = new DecelerateAnimation(250, 1, Direction.BACKWARDS);

    public Dragging(Module module, String name, float initialXVal, float initialYVal) {
        this.module = module;
        this.name = name;
        this.xPos = initialXVal;
        this.yPos = initialYVal;
        this.initialXVal = initialXVal;
        this.initialYVal = initialYVal;
    }


    private String longestModule;

    public final void onDraw(int mouseX, int mouseY) {
        boolean hovering = MouseUtil.isHovering(xPos, yPos, width, height, mouseX, mouseY);
        if (dragging) {
            xPos = mouseX - startX;
            yPos = mouseY - startY;
        }
        hoverAnimation.setDirection(hovering ? Direction.FORWARDS : Direction.BACKWARDS);
        if (!hoverAnimation.isDone() || hoverAnimation.finished(Direction.FORWARDS)) {
            RoundedUtil.drawRoundOutline(xPos - 4, yPos - 4, width + 8, height + 8, 10, 2,
                    ColorUtil.applyOpacity(Color.WHITE, 0), ColorUtil.applyOpacity(Color.WHITE, hoverAnimation.getOutput().floatValue()));
        }
    }

    public final void onClick(int mouseX, int mouseY, int button) {
        boolean canDrag = MouseUtil.isHovering(xPos, yPos, width, height, mouseX, mouseY);
        if (button == 0 && canDrag) {
            dragging = true;
            startX = (int) (mouseX - xPos);
            startY = (int) (mouseY - yPos);
        }
    }



    public final void onRelease(int button) {
        if (button == 0) dragging = false;
    }

}
