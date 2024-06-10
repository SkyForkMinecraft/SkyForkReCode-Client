package net.skyfork.util.misc;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:43
 */

public class MouseUtil {

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }

}
