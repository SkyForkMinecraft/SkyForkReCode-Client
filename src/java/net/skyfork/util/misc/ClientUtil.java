package net.skyfork.util.misc;

import net.minecraft.util.ChatComponentText;
import net.skyfork.Client;
import net.skyfork.Wrapper;

/**
 * @author LangYa
 * @since 2024/6/8 下午5:04
 */

public class ClientUtil implements Wrapper {

    public static void chat(String text) {
        mc.thePlayer.addChatMessage(new ChatComponentText(text));
    }

    public static void chatPrefix(String text) {
        chat(Client.color_name + " " + text);
    }
}
