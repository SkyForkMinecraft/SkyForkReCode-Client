package net.skyfork.event;

import net.skyfork.event.impl.Event;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author LangYa、verym0re
 * @since 2024/6/5 下午8:34
 */

public class EventManager {
    private final Map<Object, Map<Integer, List<EventHandler>>> eventMap;

    public EventManager() {
        this.eventMap = new HashMap<>();
    }

    public void register(Object target) {
        Class<?> present = target.getClass();
        Method[] methods = present.getDeclaredMethods();
        for (Method method : methods) {
            final boolean accessible = method.isAccessible();
            method.setAccessible(true);
            if (method.isAnnotationPresent(EventTarget.class)) {
                final Type event = method.getParameterTypes()[0];
                EventTarget priority = method.getAnnotation(EventTarget.class);
                eventMap.computeIfAbsent(event, k -> new TreeMap<>()).computeIfAbsent(priority.value(), k -> new ArrayList<>()).add(new EventHandler(method, target, present, priority));
            }
            method.setAccessible(accessible);
        }
    }

    public void unregister(Object target) {
        eventMap.values().forEach(eventPriority -> eventPriority.values().forEach(callers -> callers.removeIf(handler -> handler.clazz.equals(target))));
    }

    public void call(Event event) {
        eventMap.computeIfAbsent(event.getClass(), k -> new TreeMap<>()).values().forEach(callers -> callers.forEach(handler -> handler.fire(event)));
    }
}
