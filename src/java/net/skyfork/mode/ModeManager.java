package net.skyfork.mode;

/**
 * @author LangYa
 * @since 2024/6/8 下午1:55
 */

public class ModeManager {
    private final ClientMode clientMode;
    private final ThemeMode themeMode;

    public ModeManager() {
        clientMode = ClientMode.Dev;
        themeMode = ThemeMode.Dark;
    }

    public boolean isDev() {
        return clientMode == ClientMode.Dev;
    }

    public boolean isDark() {
        return themeMode == ThemeMode.Dark;
    }

}
