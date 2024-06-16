package net.skyfork.drag;

import lombok.Getter;
import net.skyfork.drag.impl.Rect;
import net.skyfork.event.EventManager;

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
        EventManager.register(new DragHandler());
    }

}
