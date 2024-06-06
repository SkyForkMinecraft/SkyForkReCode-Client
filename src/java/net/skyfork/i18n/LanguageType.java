package net.skyfork.i18n;

import lombok.Getter;

/**
 * @author LangYa
 * @since 2024/6/6 下午7:53
 */

@Getter
public enum  LanguageType {
    English("English", "en_US"),
    Chinese("Chinese", "zh_CN");

    public final String name, resource;
    LanguageType(String name, String resource) {
        this.name = name;
        this.resource = resource;
    }

}
