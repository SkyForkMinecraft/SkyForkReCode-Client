package net.skyfork.mode;

/**
 * @author LangYa
 * @since 2024/6/8 下午1:55
 */

public class ModeManager {
    private final ClientMode clientMode;
    private final ThemeMode themeMode;

    public ModeManager() {
        clientMode = ClientMode.Hack;
        themeMode = ThemeMode.Dark;
    }

    public boolean isHack() {
        return clientMode == ClientMode.Hack;
    }
    public boolean isDark() {
        return themeMode == ThemeMode.Dark;
    }

}
