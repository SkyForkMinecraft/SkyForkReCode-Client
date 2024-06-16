package net.skyfork.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventHandler {
    public final Method method;
    public final Object present;
    public final Class<?> clazz;
    public final EventTarget eventTarget;
    private final boolean accessible;
    public EventHandler(Method method, Object present, Class<?> clazz, EventTarget eventTarget) {
        this.method = method;
        this.present = present;
        this.clazz = clazz;
        this.eventTarget = eventTarget;
        this.accessible = method.isAccessible();
    }

    public void fire(Object event) {
        try {
            method.setAccessible(true);
            method.invoke(present, event);
            method.setAccessible(accessible);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
