package net.skyfork.util.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_QUADS;

/**
 * @author LangYa
 * @since 2024/6/7 下午7:23
 */

public class RenderUtil {

    public static void drawTexture(int texture, float x, float y, float width, float height, float u, float v, int textureWidth, int textureHeight) {
        float xTemel = 1.0F / textureWidth;
        float yTemel = 1.0F / textureHeight;

        GlStateManager.bindTexture(texture);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0).tex(u * xTemel, (v + height) * yTemel).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + width) * xTemel, (v + height) * yTemel).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + width) * xTemel, v * yTemel).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * xTemel, v * yTemel).endVertex();
        tessellator.draw();
    }

    public static void drawRect(double x, float y, float width, float height, Color color) {
        drawRect(x, y, width, height, color.getRGB());
    }

    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }

        final float f3 = (float) (color >> 24 & 255) / 255.0F;
        final float f = (float) (color >> 16 & 255) / 255.0F;
        final float f1 = (float) (color >> 8 & 255) / 255.0F;
        final float f2 = (float) (color & 255) / 255.0F;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }


}
