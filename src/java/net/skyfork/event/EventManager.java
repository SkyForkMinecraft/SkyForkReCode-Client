package net.skyfork.event;

import net.skyfork.event.annotations.EventTarget;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author LangYa
 * @since 2024/6/5 下午8:34
 */

public class EventManager {
    private final Map<Integer, List<Method>> eventMap;

    public EventManager() {
        this.eventMap = new HashMap<>();
    }

    public void register(Object target) {
        Method[] methods = target.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(EventTarget.class)) {
                EventTarget priority = method.getAnnotation(EventTarget.class);
                int value = priority.value();
                eventMap.computeIfAbsent(value, k -> new ArrayList<>()).add(method);
            }
        }
    }

    public void unregister(Object target) {
        eventMap.remove(target);
    }

    private int getMethodPriority(Method method) {
        EventTarget annotation = method.getAnnotation(EventTarget.class);
        return annotation != null ? annotation.value() : 0;
    }

    public void call(Object event) {
        List<Method> methods = new ArrayList<>();
        for (List<Method> methodList : eventMap.values()) {
            methods.addAll(methodList);
        }
        methods.sort(Comparator.comparingInt(this::getMethodPriority));

        for (Method method : methods) {
            try {
                // 设置私有方法可访问
                method.setAccessible(true);
                method.invoke(method.getDeclaringClass().newInstance(), event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
