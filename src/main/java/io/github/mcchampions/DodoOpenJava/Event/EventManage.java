package io.github.mcchampions.DodoOpenJava.Event;

import io.github.mcchampions.DodoOpenJava.Command.CommandTrigger;
import io.github.mcchampions.DodoOpenJava.Api.Version;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.events.EventException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 事件的相关方法（包含监听器注册等）
 * @author qscbm187531
 */
public class EventManage {

    @NotNull
    public static Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener) {
        Validate.notNull(listener, "Listener can not be null");

        Map<Class<? extends Event>, Set<RegisteredListener>> ret = new HashMap<>();
        Set<Method> methods;
        try {
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet<>(publicMethods.length + privateMethods.length, 1.0f);
            Collections.addAll(methods, publicMethods);
            Collections.addAll(methods, privateMethods);
        } catch (NoClassDefFoundError e) {
            return ret;
        }

        for (final Method method : methods) {
            final EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) continue;
            if (method.isBridge() || method.isSynthetic()) {
                continue;
            }
            final Class<?> checkClass;
            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                continue;
            }
            final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            Set<RegisteredListener> eventSet = ret.computeIfAbsent(eventClass, k -> new HashSet<>());

            for (Class<?> clazz = eventClass; Event.class.isAssignableFrom(clazz); clazz = clazz.getSuperclass()) {
                if (clazz.getAnnotation(Deprecated.class) != null) {
                    break;
                }
            }
            EventExecutor executor = (listener1, event) -> {
                try {
                    if (!eventClass.isAssignableFrom(event.getClass())) {
                        return;
                    }
                    method.invoke(listener1, event);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            };
            eventSet.add(new RegisteredListener(listener, executor, eh.priority()));
        }
        return ret;
    }

    private static boolean isInit = false;
    /**
     * 注册事件
     * @param listener 监听器
     * @param ad Authorization
     */
    public static void registerEvents(@NotNull Listener listener,@NotNull String ad,@NotNull Version version) {
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : createRegisteredListeners(listener).entrySet()) {
            getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
        }
        if (!isInit) {
            isInit = true;
            CommandTrigger.listenerConsole();
            EventTrigger.main(ad,version);
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
    public static void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor,@NotNull String ad,@NotNull Version version) {
        Validate.notNull(listener, "Listener cannot be null");
        Validate.notNull(priority, "Priority cannot be null");
        Validate.notNull(executor, "Executor cannot be null");
        Validate.notNull(ad, "Authorization cannot be null");
        Validate.notNull(version, "Version cannot be null");

        getEventListeners(event).register(new RegisteredListener(listener, executor, priority));
        if (!isInit) {
            isInit = true;
            CommandTrigger.listenerConsole();
            EventTrigger.main(ad,version);
        }
    }

    @NotNull
    private static HandlerList getEventListeners(@NotNull Class<? extends Event> type) {
        try {
            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (HandlerList) method.invoke(null);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static Class<? extends Event> getRegistrationClass(@NotNull Class<? extends Event> clazz) {
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

    public static void fireEvent(@NotNull Event event) throws EventException {
        HandlerList handlers = event.getHandlers();
        RegisteredListener[] listeners = handlers.getRegisteredListeners();

        for (RegisteredListener registration : listeners) {
            registration.callEvent(event);
        }
    }
}
