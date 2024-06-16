package net.skyfork.user;

import net.skyfork.event.EventManager;
import net.skyfork.event.EventTarget;
import net.skyfork.Client;
import net.skyfork.event.impl.misc.EventText;

/**
 * @author LangYa
 * @since 2024/6/5 下午9:25
 */

public class RankManager {

    public RankManager() {
        EventManager.register(this);
    }

    @EventTarget
    public void onT(EventText e) {

    }

}
