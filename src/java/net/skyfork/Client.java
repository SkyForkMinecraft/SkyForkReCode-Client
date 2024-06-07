package net.skyfork;

import com.cubk.event.EventManager;

import net.minecraft.util.ResourceLocation;
import net.skyfork.i18n.I18n;
import net.skyfork.i18n.I18nManager;
import net.skyfork.module.ModuleManager;
import net.skyfork.user.RankManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:43
 */

public class Client implements Wrapper {
    // info
    public static final String name = "SkyFork";
    public static final String version = "1.0";
    public static final String web = "https://github.com/SkyForkMinecraft";

    // logger
    public static Logger logManager;

    // managers
    public static EventManager eventManager;
    public static ModuleManager moduleManager;
    public static I18nManager i18nManager;
    public static RankManager rankManager;

    public static ResourceLocation getLocation(String location) {
        return new ResourceLocation(name.toLowerCase() + "/" + location);
    }

    public static void initClient() {
        logManager = LogManager.getLogger(Client.class);
        i18nManager = new I18nManager();
        Display.setTitle(String.format("%s - %s | 1.8.9", I18n.format("client.name"), version));
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        rankManager = new RankManager();
    }

    public static void stopClient() {}

}
