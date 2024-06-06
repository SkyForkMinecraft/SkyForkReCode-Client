package net.skyfork.module;

import lombok.Getter;
import lombok.Setter;
import net.skyfork.Wrapper;

/**
 * @author LangYa
 * @since 2024/6/6 下午6:45
 */

@Getter
@Setter
public class Module implements Wrapper {
    private String name;
    private Category category;
    private boolean state;
    private int key;
    public void onEnable() {}
    public void onDisable() {}
}
