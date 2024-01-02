package io.github.minecraftchampions.dodoopenjava.event;

import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 事件的相关方法（包含监听器注册等）
 */
public class EventManager {
    private final Map<Class<? extends Event>, ArrayList<SimpleEntry<Method, Object>>> handlers = new ConcurrentHashMap<>();

    /**
     * 注册事件监听器
     *
     * @param listeners 事件监听器
     */
    public void registerListeners(@NonNull Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * 注册事件监听器
     *
     * @param listener 事件监听器
     */
    public void registerListener(@NonNull Listener listener) {
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
            List<SimpleEntry<Method, Object>> list = handlers.get(eventClass);
            synchronized (list) {
                if (Modifier.isStatic(method.getModifiers())) {
                    list.add(new SimpleEntry<>(method, null));
                } else {
                    list.add(new SimpleEntry<>(method, listener));
                }
            }
        }
    }

    /**
     * 注销所有事件监听器
     */
    public synchronized void unregisterAllListeners() {
        synchronized (handlers) {
            handlers.clear();
        }
    }

    /**
     * 注销事件监听器
     *
     * @param listener listener
     */
    public void unregisterListeners(@NonNull Listener listener) {
        synchronized (handlers) {
            Set<Class<? extends Event>> set = handlers.keySet();
            for (Class<? extends Event> clazz : set) {
                List<SimpleEntry<Method, Object>> list = handlers.get(clazz).stream().filter(e -> e.getKey().getDeclaringClass() == listener.getClass()).toList();
                handlers.get(clazz).removeAll(list);
            }
        }
    }

    /**
     * 触发事件
     *
     * @param event 事件
     * @throws RuntimeException 当事件的 EventType 为 null 或 isEmpty 时抛出
     */
    public void fireEvent(@NonNull Event event) throws RuntimeException {
        if (event.getEventType() == null || event.getEventName().isEmpty()) {
            throw new RuntimeException("未知的Event");
        }
        synchronized (handlers) {
            fireEvent(event, handlers);
        }
    }

    /**
     * 触发事件
     *
     * @param event    事件
     * @param handlers 储存
     */
    public static void fireEvent(@NonNull Event event, @NonNull Map<Class<? extends Event>, ArrayList<SimpleEntry<Method, Object>>> handlers) {
        boolean isAsync = event.isAsynchronous();
        if (!handlers.containsKey(event.eventType)) {
            return;
        }
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
