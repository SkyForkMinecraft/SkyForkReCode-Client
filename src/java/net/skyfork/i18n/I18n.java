package net.skyfork.i18n;

import java.util.HashMap;

/**
 * @author LangYa
 * @since 2024/6/6 下午7:50
 */

public class I18n {
    public static HashMap<String, String> properties;

    public static String format(String key) {
        return properties.get(key);
    }

}
