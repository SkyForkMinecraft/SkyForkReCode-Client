package net.skyfork;

import net.skyfork.event.EventManager;

import net.skyfork.event.EventTarget;
import net.minecraft.util.ResourceLocation;
import net.skyfork.command.CommandManager;
import net.skyfork.drag.DragManager;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.i18n.I18n;
import net.skyfork.i18n.I18nManager;
import net.skyfork.mode.ModeManager;
import net.skyfork.module.Module;
import net.skyfork.module.ModuleManager;
import net.skyfork.font.FontManager;
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
    public static I18nManager i18nManager;
    public static EventManager eventManager;
    public static ModeManager modeManager;
    public static ModuleManager moduleManager;
    public static CommandManager commandManager;
    public static DragManager dragManager;
    public static RankManager rankManager;

    public static ResourceLocation getLocation(String location) {
        return new ResourceLocation(name.toLowerCase() + "/" + location);
    }

    public static void initClient() {
        i18nManager = new I18nManager();
        eventManager.register(new Client());
        logManager = LogManager.getLogger(Client.class);
        Display.setTitle(String.format("%s - %s | 1.8.9", I18n.format("client.name"), version));
        modeManager = new ModeManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        dragManager = new DragManager();
        rankManager = new RankManager();
    }

    public static void stopClient() {}

    @EventTarget
    private void onRender2D(EventRender2D event) {
        FontManager.M30.drawStringWithShadow(I18n.format("client.name"), 10, 10, -1);

        if (moduleManager != null) {
            int y = 0;
            for (Module module : moduleManager.getModules()) {
                FontManager.S16.drawRightAlignedStringWithShadow(module.getName(), event.getScaledResolution().getScaledWidth() - 10, y, -1);
                y += 16;
            }
        }
    }

}
