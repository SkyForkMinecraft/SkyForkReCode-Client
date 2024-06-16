package net.skyfork.font;

import lombok.Getter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventText;
import net.skyfork.util.misc.ColorUtil;
import net.skyfork.util.render.GLUtils;
import net.skyfork.util.render.RenderUtil;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class FontDrawer {
    public static boolean SecondaryFontAntiAliasing = true;

    public String trimStringToWidth(String text, int width) {
        return this.trimStringToWidth(text, width, false);
    }

    /**
     * Trims a string to a specified width, and will reverse it if par3 is set.
     */
    public String trimStringToWidth(String text, int width, boolean reverse) {
        StringBuilder stringbuilder = new StringBuilder();
        float f = 0.0F;
        int i = reverse ? text.length() - 1 : 0;
        int j = reverse ? -1 : 1;
        boolean flag = false;
        boolean flag1 = false;

        for (int k = i; k >= 0 && k < text.length() && f < width; k += j) {
            char c0 = text.charAt(k);
            float f1 = this.getCharWidth(c0);

            if (flag) {
                flag = false;

                if (c0 != 108 && c0 != 76) {
                    if (c0 == 114 || c0 == 82) {
                        flag1 = false;
                    }
                } else {
                    flag1 = true;
                }
            } else if (f1 < 0.0F) {
                flag = true;
            } else {
                f += f1;

                if (flag1) {
                    ++f;
                }
            }

            if (f > width) {
                break;
            }

            if (reverse) {
                stringbuilder.insert(0, c0);
            } else {
                stringbuilder.append(c0);
            }
        }

        return stringbuilder.toString();
    }

    private static final HashMap<Integer, Font> SECONDARY_FONT_MAP = new HashMap<>();
    private static final int[] COLORS = new int[32];
    private static final int SHADOW_COLOR = ColorUtil.getRGB(0, 0, 0, 180);

    private final Glyph[] glyphs = new Glyph[65536];
    private final Font font;
    private final Font secondaryFont;
    private final int fontSize;
    private final int imageSize;
    @Getter
    private final int halfHeight;
    private final boolean antiAliasing;
    private final boolean fractionalMetrics;

    static {
        for (int i = 0; i < COLORS.length; i++) {
            int offset = (i >> 3 & 1) * 85;

            int red = (i >> 2 & 1) * 170 + offset;
            int green = (i >> 1 & 1) * 170 + offset;
            int blue = (i & 1) * 170 + offset;

            if (i == 6) {
                red += 85;
            }

            if (i >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }

            COLORS[i] = (red & 255) << 16 | (green & 255) << 8 | blue & 255;
        }
    }

    public FontDrawer(Font font, boolean antiAliasing, boolean fractionalMetrics) {
        this.font = font;
        this.fontSize = font.getSize();
        this.imageSize = font.getSize() + (font.getSize() / 4);
        this.halfHeight = font.getSize() / 2;
        this.antiAliasing = antiAliasing;
        this.fractionalMetrics = fractionalMetrics;
        this.secondaryFont = getSecondaryFont();
    }

    public int getInitializedGlyphCount() {
        int i = 0;

        for (Glyph glyph : glyphs)
            if (glyph != null)
                i++;

        return i;
    }

    private Font getSecondaryFont() {
        return SECONDARY_FONT_MAP.computeIfAbsent(fontSize, s -> new Font(Font.SANS_SERIF, Font.PLAIN, s));
    }

    public int getStringWidth(String s) {

        EventText textEvent = new EventText(s);
        if (Client.eventManager != null) {
            Client.eventManager.call(textEvent);
        }
        if (textEvent.isCancelled()) return 0;
        s  = textEvent.text;

        if (s != null && !s.isEmpty()) {
//            s = EnumChatFormatting.getTextWithoutFormattingCodes(s);
            int ret = 0;

            for (int i = 0; i < s.length(); ++i) {
                ret += this.getGlyph(s.charAt(i)).halfWidth;
            }

            return ret + 2;
        } else {
            return 0;
        }
    }


    public int getStringWidthDirectly(String s) {
        if (s == null || s.isEmpty()) return 0;

        int ret = 0;

        for (int i = 0; i < s.length(); i++) {
            ret += getGlyph(s.charAt(i)).width;
        }

        return ret / 2;
    }

    public int getCharWidth(char c) {
        return getGlyph(c).halfWidth;
    }

    public int getHeight() {
        return halfHeight;
    }

    public void drawCenteredStringWithShadow(String s, double x, double y, int color) {
        drawStringWithShadow(s, x - (getStringWidth(s) / 2.0), y, color);
    }

    public void drawCenteredString(String s, double x, double y, int color) {
        drawString(s, x - getStringWidth(s) / 2.0, y, color);
    }

    public void drawRightAlignedString(String s, double x, double y, int color) {
        drawString(s, x - getStringWidth(s), y, color);
    }

    public void drawRightAlignedStringWithShadow(String s, double x, double y, int color) {
        drawStringWithShadow(s, x - getStringWidth(s), y, color);
    }

    public void drawStringWithShadowDirectly(String s, double x, double y, int color) {
        int alpha = ColorUtil.getAlpha(color);

        drawStringDirectly(s, x + 0.5, y + 0.5, alpha < 200 ? ColorUtil.reAlpha(SHADOW_COLOR, alpha) : SHADOW_COLOR);
        drawStringDirectly(s, x, y, color);
    }

    public void drawStringDirectly(String s, double x, double y, int color) {
        if (s == null || s.isEmpty()) return;

        if ((color & -67108864) == 0) {
            color |= -16777216;
        }

        preDraw();
        GLUtils.color(color);

        x *= 2.0;
        y *= 2.0;

        for (int i = 0; i < s.length(); i++) {
            Glyph glyph = getGlyph(s.charAt(i));

            glyph.draw(x, y);

            x += glyph.width;
        }

        postDraw();
    }

    public void drawStringWithOutline(String s, double x, double y, int color) {
        drawString(s, x + 0.5, y, 0);
        drawString(s, x - 0.5, y, 0);
        drawString(s, x, y + 0.5, 0);
        drawString(s, x, y - 0.5, 0);
        drawString(s, x, y, color);
    }

    public void drawStringWithOutline(String s, double x, double y, int color, int outlineColor) {
        drawString(s, x + 0.5, y, outlineColor);
        drawString(s, x - 0.5, y, outlineColor);
        drawString(s, x, y + 0.5, outlineColor);
        drawString(s, x, y - 0.5, outlineColor);
        drawString(s, x, y, color);
    }

    public void drawStringWithShadow(String s, double x, double y, int color) {
        int alpha = ColorUtil.getAlpha(color);

        drawString(s, x + 0.5, y + 0.5, alpha < 200 ? ColorUtil.reAlpha(SHADOW_COLOR, alpha) : SHADOW_COLOR, true);
        drawString(s, x, y, color, false);
    }

    public void drawString(String s, double x, double y, int color) {
        drawString(s, x, y, color, false);
    }

    public void drawString(String s, double x, double y, int color, boolean shadow) {
        if (s == null || s.isEmpty()) return;

        EventText textEvent = new EventText(s);
        if (Client.eventManager != null) {
            Client.eventManager.call(textEvent);
        }
        if (textEvent.isCancelled()) return;
        s  = textEvent.text;

        if ((color & -67108864) == 0) {
            color |= -16777216;
        }

        preDraw();
        GLUtils.color(color);

        x *= 2.0;
        y *= 2.0;

        boolean bold = false;
        boolean italic = false;
        boolean strikethrough = false;
        boolean underline = false;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            Glyph glyph;
            if (c == 167) {
                ++i;
                if (i >= s.length()) {
                    glyph = this.getGlyph('\u00a7');
                    this.drawGlyph(glyph, x, y, bold, strikethrough, underline);
                    x += glyph.width;
                }

                if (!shadow) {
                    int colorIndex = 21;
                    if (i < s.length()) {
                        colorIndex = "0123456789abcdefklmnor".indexOf(s.charAt(i));
                    }

                    switch (colorIndex) {
                        case 17:
                            bold = true;
                            break;
                        case 18:
                            strikethrough = true;
                            break;
                        case 19:
                            underline = true;
                            break;
                        case 20:
                            italic = true;
                            break;
                        case 21:
                            bold = false;
                            italic = false;
                            underline = false;
                            strikethrough = false;
                            GLUtils.color(color);
                            break;
                        default:
                            if (colorIndex < 16) {
                                if (colorIndex == -1) {
                                    colorIndex = 15;
                                }

                                int finalColor = COLORS[colorIndex];
                                GLUtils.color(ColorUtil.getRed(finalColor), ColorUtil.getGreen(finalColor), ColorUtil.getBlue(finalColor), ColorUtil.getAlpha(color));
                            }
                    }
                }
            } else {

                Glyph glyph1 = getGlyph(c);

                if (shadow) {
                    drawGlyph(glyph1, x, y, bold, false, false);
                } else {
                    drawGlyph(glyph1, x, y, bold, strikethrough, underline);
                }

                x += glyph1.width;
            }

        }

        postDraw();
    }

    public void drawCharWithShadow(char c, double x, double y, int color) {
        int alpha = ColorUtil.getAlpha(color);

        drawChar(c, x + 0.5, y + 0.5, alpha < 200 ? ColorUtil.reAlpha(SHADOW_COLOR, alpha) : SHADOW_COLOR);
        drawChar(c, x, y, color);
    }

    public void drawChar(char c, double x, double y, int color) {
        preDraw();
        GLUtils.color(color);

        x *= 2.0;
        y *= 2.0;

        getGlyph(c).draw(x, y);

        postDraw();
    }

    private void drawGlyph(Glyph glyph, double x, double y, boolean bold, boolean strikethrough, boolean underline) {
        if (bold) {
            glyph.draw(x + 1, y);
        }

        glyph.draw(x, y);

        if (strikethrough) {
            double mid = y + halfHeight + 2;

            drawLine(x, mid - 1, x + glyph.width, mid + 1);
        }

        if (underline) {
            drawLine(x, y + fontSize + 1, x + glyph.width, y + fontSize + 2);
        }
    }

    private void preDraw() {
        GLUtils.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableTexture2D();
        GlStateManager.scale(0.5, 0.5, 0.5);
    }

    private void postDraw() {
        GlStateManager.disableBlend();
        GLUtils.popMatrix();
    }

    private Glyph getGlyph(char c) {
        Glyph glyph = glyphs[c];

        if (glyph == null) {
            glyphs[c] = glyph = createGlyph(c);
        }

        return glyph;
    }

    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255, 1);

    private Glyph createGlyph(char c) {
        String s = String.valueOf(c);
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, imageSize, imageSize);

        Font f = font;

        if (font.canDisplay(c)) {
            setRenderingHints(g, antiAliasing, fractionalMetrics);
        } else {
            setRenderingHints(g, SecondaryFontAntiAliasing, true);

            f = secondaryFont;
        }

        g.setFont(f);
        g.setColor(Color.WHITE);

        FontMetrics fontMetrics = g.getFontMetrics();
        Rectangle charBounds = fontMetrics.getStringBounds(s, g).getBounds();
        int charWidth = charBounds.width;
        int charHeight = charBounds.height;

        int u = imageSize / 2 - charWidth / 2;
        int v = imageSize / 2 - charHeight / 2;
        g.drawString(s, u, v + fontSize);

        g.dispose();

        return new Glyph(image, charWidth, charHeight, u, v);
    }

    private static void setRenderingHints(Graphics2D g, boolean antiAliasing, boolean fractionalMetrics) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAliasing ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !(codePoint == 0x0 || codePoint == 0x9 || codePoint == 0xA || codePoint == 0xD || codePoint >= 0x20 && codePoint <= 0xD7FF || codePoint >= 0xE000 && codePoint <= 0xFFFD);
    }

    private static void drawLine(double left, double top, double right, double bottom) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.disableTexture2D();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }

    public void drawString(String name, float x, float y, Color color) {
        drawString(name, x, y, color.getRGB(), false);
    }

    private final class Glyph {
        public final int textureID;
        public final int width;
        public final int height;
        public final int halfWidth;
        public final int u, v;

        public Glyph(BufferedImage image, int width, int height, int u, int v) {
            this.textureID = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), image, true, true);
            this.width = width;
            this.height = height;
            this.halfWidth = width / 2;
            this.u = u;
            this.v = v;
        }

        public void draw(double x, double y) {
            GlStateManager.bindTexture(textureID);

            y -= 2;

            RenderUtil.drawTexture(textureID, (float) x, (float) y, width, height, u, v, imageSize, imageSize);
        }
    }
}