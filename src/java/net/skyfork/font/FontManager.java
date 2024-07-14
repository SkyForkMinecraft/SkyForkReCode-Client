package net.skyfork.font;

import net.minecraft.client.Minecraft;
import net.skyfork.Client;

import java.awt.*;

/**
 * @author LangYa
 * @since 2024/6/7 下午7:26
 */

public class FontManager {
    public static FontDrawer S14 = getFont("misans", 14);
    public static FontDrawer S16 = getFont("misans", 16);
    public static FontDrawer S18 = getFont("misans", 18);
    public static FontDrawer S20 = getFont("misans", 20);
    public static FontDrawer S30 = getFont("misans", 30);
    public static FontDrawer S40 = getFont("misans", 40);
    public static FontDrawer S50 = getFont("misans", 50);

    public static FontDrawer getFont(String name, int size) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(Client.getLocation("font/" + name + ".ttf")).getInputStream()).deriveFont(Font.PLAIN, size);
        } catch (Exception exception) {
            font = new Font("default", Font.PLAIN, size);
        }
        return new FontDrawer(font, true,true);
    }

}