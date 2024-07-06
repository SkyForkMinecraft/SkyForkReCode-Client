package net.skyfork;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.combat.WorldManager;
import net.skyfork.event.EventManager;

import net.skyfork.event.EventTarget;
import net.minecraft.util.ResourceLocation;
import net.skyfork.command.CommandManager;
import net.skyfork.drag.DragManager;
import net.skyfork.event.impl.render.EventRender2D;
import net.skyfork.font.FontDrawer;
import net.skyfork.i18n.I18n;
import net.skyfork.i18n.I18nManager;
import net.skyfork.mode.ModeManager;
import net.skyfork.mode.ThemeMode;
import net.skyfork.module.Module;
import net.skyfork.module.ModuleManager;
import net.skyfork.font.FontManager;
import net.skyfork.user.RankManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.File;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:43
 */

public class Client implements Wrapper {
    // info
    public static String name = "SkyFork";
    public final static String color_name = "§r[§b" + name + "§r]";
    public static final String version = "1.0";
    public static final String web = "https://github.com/SkyForkMinecraft";
    public static final File dir = new File(mc.mcDataDir, name);

    // logger
    public static Logger logManager;

    // managers
    @Getter
    @Setter
    private I18nManager i18nManager;
    @Getter
    private ModeManager modeManager;
    @Getter
    private EventManager eventManager;
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private CommandManager commandManager;
    @Getter
    private DragManager dragManager;
    @Getter
    private WorldManager worldManager;
    @Getter
    private RankManager rankManager;

    @Getter
    private static Client instance = new Client();
    public boolean loaded;

    public static ResourceLocation getLocation(String location) {
        return new ResourceLocation("skyfork/" + location);
    }

    public void initClient() {
        instance = this;
        eventManager = new EventManager();
        i18nManager = new I18nManager();
        name = I18n.format("client.name");
        Display.setTitle(String.format("%s - %s | 1.8.9", name, version));
        modeManager = new ModeManager();
        commandManager = new CommandManager();
        dragManager = new DragManager();
        dragManager.loadDragData();
        moduleManager = new ModuleManager();
        moduleManager.init();
        worldManager = new WorldManager();
        rankManager = new RankManager();
        loaded = true;
    }


    public void stopClient() {
        dragManager.saveDragData();
    }

}
