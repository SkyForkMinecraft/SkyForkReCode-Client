package net.skyfork.drag;

import lombok.Getter;
import net.skyfork.Client;
import net.skyfork.drag.impl.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @since 2024/6/9 下午7:34
 */

@Getter
public class DragManager {
    private final List<Drag> dragList;

    public DragManager() {
        dragList = new ArrayList<>();
        dragList.add(new Rect());
        Client.eventManager.register(new DragHandler());
    }

}
