package io.github.minecraftchampions.dodoopenjava.event;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;


/**
 * 事件的相关方法（包含监听器注册等）
 */
public class EventManager {
    private HashMap<Class<? extends Event>, ArrayList<SimpleEntry<Method, Object>>> handlers = new HashMap<>();

    /**
     * 注册事件监听器
     *
     * @param listener 事件监听器
     */
    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!Modifier.isPublic(method.getModifiers()))
                continue;
            EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null)
                continue;
            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length > 1)
                continue;
            Class<?> checkClass = parameters[0];
            if (!Event.class.isAssignableFrom(checkClass)) {
                continue;
            }
            final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            if (!handlers.containsKey(eventClass))
                handlers.put(eventClass, new ArrayList<>());
            if (Modifier.isStatic(method.getModifiers())) {
                handlers.get(eventClass).add(new SimpleEntry<>(method, null));
            } else {
                handlers.get(eventClass).add(new SimpleEntry<>(method, listener));
            }
        }
    }

    /**
     * 注销所有事件监听器
     */
    public void unregisterAllListeners() {
        handlers.clear();
    }

    /**
     * 注销事件监听器
     *
     * @param listener listener
     */
    public void unregisterListeners(Listener listener) {
        Set<Class<? extends Event>> set = handlers.keySet();
        for (Class<? extends Event> clazz : set) {
            List<SimpleEntry<Method, Object>> list = handlers.get(clazz).stream().filter(e -> e.getKey().getDeclaringClass() == listener.getClass()).toList();
            handlers.get(clazz).removeAll(list);
        }
    }

    /**
     * 触发事件
     *
     * @param event 事件
     * @throws RuntimeException 当事件的 EventType 为 null 或 isEmpty 时抛出
     */
    public void fireEvent(@NotNull Event event) throws RuntimeException {
        if (event.getEventType() == null || event.getEventName().isEmpty()) {
            throw new RuntimeException("未知的Event");
        }
        boolean isAsync = event.isAsynchronous();
        if (handlers.containsKey(event.eventType)) {
            ArrayList<SimpleEntry<Method, Object>> methodEntryList = handlers.get(event.eventType);
            for (SimpleEntry<Method, Object> entry : methodEntryList) {
                Method method = entry.getKey();
                if (isAsync) {
                    CompletableFuture.runAsync(() -> {
                        try {
                            method.invoke(entry.getValue(), event);
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                } else {
                    try {
                        method.invoke(entry.getValue(), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
