package net.skyfork.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public enum GLUtil {
    ;

    public static void startScissor(int x, int y, int width, int height) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = sr.getScaleFactor();

        glPushMatrix();
        glEnable(GL_SCISSOR_TEST);
        glScissor(x * scaleFactor, Minecraft.getMinecraft().displayHeight - (y + height) * scaleFactor, width * scaleFactor, height * scaleFactor);
    }

    public static void stopScissor() {
        glDisable(GL_SCISSOR_TEST);
        glPopMatrix();
    }

    public static void scale(double x, double y, double z) {
        glScaled(x, y, z);
    }

    public static void pushMatrix() {
        glPushMatrix();
    }

    public static void popMatrix() {
        glPopMatrix();
    }

    public static void enable(int cap) {
        glEnable(cap);
    }

    public static void disable(int cap) {
        glDisable(cap);
    }

    public static void blendFunc(int sFactor,int dFactor) {
        glBlendFunc(sFactor,dFactor);
    }

    public static void translated(double x,double y,double z) {
        glTranslated(x,y,z);
    }

    public static void rotated(double angle,double x,double y,double z) {
        glRotated(angle,x,y,z);
    }

    public static void depthMask(boolean flag) {
        glDepthMask(flag);
    }

    public static void color(int r,int g,int b) {
        color(r,g,b,255);
    }

    public static void color(int r,int g,int b,int a) {
        GlStateManager.color(r / 255.0f,g / 255.0f,b / 255.0f,a / 255.0f);
    }

    public static void color(int hex) {
        GlStateManager.color(
                (hex >> 16 & 0xFF) / 255.0f,
                (hex >> 8 & 0xFF) / 255.0f,
                (hex & 0xFF) / 255.0f,
                (hex >> 24 & 0xFF) / 255.0f);
    }

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static int[] enabledCaps = new int[32];

    public static void startBlend() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
    }

    public static void setupRendering(int mode, Runnable runnable) {
        GlStateManager.glBegin(mode);
        runnable.run();
        GlStateManager.glEnd();
    }

    public static void enableDepth() {
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
    }

    public static void disableCaps() {
        int[] var0 = enabledCaps;
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            int cap = var0[var2];
            GL11.glDisable(cap);
        }

    }

    public static void enableCaps(int... caps) {
        int[] var1 = caps;
        int var2 = caps.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            int cap = var1[var3];
            GL11.glEnable(cap);
        }

        enabledCaps = caps;
    }

    public static void enableTexture2D() {
        GL11.glEnable(3553);
    }

    public static void disableTexture2D() {
        GL11.glDisable(3553);
    }

    public static void enableBlending() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }

    public static void disableDepth() {
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
    }

    public static void disableBlending() {
        GL11.glDisable(3042);
    }

    public static void endBlend() {
        GlStateManager.disableBlend();
    }

    public static void render(int mode, Runnable render) {
        GlStateManager.glBegin(mode);
        render.run();
        GlStateManager.glEnd();
    }

    public static void setup2DRendering(Runnable f) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        f.run();
        GL11.glEnable(3553);
        GlStateManager.disableBlend();
    }

    public static void setup2DRendering() {
        setup2DRendering(true);
    }

    public static void setup2DRendering(boolean blend) {
        if (blend) {
            startBlend();
        }

        GlStateManager.disableTexture2D();
    }

    public static void end2DRendering() {
        GlStateManager.enableTexture2D();
        endBlend();
    }

    public static void rotate(float x, float y, float rotate, Runnable f) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0.0F);
        GlStateManager.rotate(rotate, 0.0F, 0.0F, -1.0F);
        GlStateManager.translate(-x, -y, 0.0F);
        f.run();
        GlStateManager.popMatrix();
    }
}