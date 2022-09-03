package io.github.mcchampions.DodoOpenJava.Event;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.events.CardMessageButtonClickEvent;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 事件的相关方法（包含监听器注册等）
 */
public class EventManage {

    @NotNull
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener,String ad) {
        Validate.notNull(listener, "Listener can not be null");

        Map<Class<? extends Event>, Set<RegisteredListener>> ret = new HashMap<Class<? extends Event>, Set<RegisteredListener>>();
        Set<Method> methods;
        try {
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet<Method>(publicMethods.length + privateMethods.length, 1.0f);
            Collections.addAll(methods, publicMethods);
            Collections.addAll(methods, privateMethods);
        } catch (NoClassDefFoundError e) {
            return ret;
        }

        for (final Method method : methods) {
            final EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) continue;
            // Do not register bridge or synthetic methods to avoid event duplication
            // Fixes SPIGOT-893
            if (method.isBridge() || method.isSynthetic()) {
                continue;
            }
            final Class<?> checkClass;
            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                continue;
            }
            final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            Set<RegisteredListener> eventSet = ret.computeIfAbsent(eventClass, k -> new HashSet<RegisteredListener>());

            for (Class<?> clazz = eventClass; Event.class.isAssignableFrom(clazz); clazz = clazz.getSuperclass()) {
                // This loop checks for extending deprecated events
                if (clazz.getAnnotation(Deprecated.class) != null) {
                    break;
                }
            }
            EventExecutor executor = new EventExecutor() {
                @Override
                public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
                    try {
                        if (!eventClass.isAssignableFrom(event.getClass())) {
                            return;
                        }
                        // Spigot start
                        boolean isAsync = event.isAsynchronous();
                        method.invoke(listener, event);
                        // Spigot end
                    } catch (InvocationTargetException ex) {
                        throw new EventException(ex.getCause());
                    } catch (Throwable t) {
                        throw new EventException(t);
                    }
                }
            };
            eventSet.add(new RegisteredListener(listener, executor, eh.priority(),ad));
        }
        return ret;
    }

    public void registerEvents(@NotNull Listener listener,String ad) {
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : createRegisteredListeners(listener,ad).entrySet()) {
            getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue(),ad);
        }
    }

    /**
     * 将指定的执行器注册至指定的事件类.
     * <p>
     * 原文:Registers the specified executor to the given event class
     *
     * @param event 要注册的事件类型
     * @param listener 要注册的监听器
     * @param priority 要注册的事件的优先级
     * @param executor 要注册的EventExecutor=
     */
    public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor,String ad) {
        Validate.notNull(listener, "Listener cannot be null");
        Validate.notNull(priority, "Priority cannot be null");
        Validate.notNull(executor, "Executor cannot be null");
        Validate.notNull(executor, "Authorization cannot be null");

        getEventListeners(event).register(new RegisteredListener(listener, executor, priority,ad));
    }

    @NotNull
    private HandlerList getEventListeners(@NotNull Class<? extends Event> type) {
        try {
            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (HandlerList) method.invoke(null);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private Class<? extends Event> getRegistrationClass(@NotNull Class<? extends Event> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlerList");
            return clazz;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null
                    && !clazz.getSuperclass().equals(Event.class)
                    && Event.class.isAssignableFrom(clazz.getSuperclass())) {
                return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
            }
            return null;
        }
    }

    public void fireEvent(@NotNull Event event) throws EventException {
        HandlerList handlers = event.getHandlers();
        RegisteredListener[] listeners = handlers.getRegisteredListeners();

        for (RegisteredListener registration : listeners) {
            registration.callEvent(event);
        }
    }
}
