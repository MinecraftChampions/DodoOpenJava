package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.utils.ClassUtil;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.events.EventException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 事件的相关方法（包含监听器注册等）
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
     * 注册事件监听器
     *
     * @param listener 监听器
     * @param an       Authorization
     */
    public static void registerEvents(@NotNull Listener listener, @NotNull String an) {
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : createRegisteredListeners(listener).entrySet()) {
            getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
        }
        if (!isInit) {
            isInit = true;
            init(an);
        }
    }

    /**
     * 通过反射获取使用了两者的哪个库
     *
     * @return 类
     */
    public static Class<?> getEventTriggerClass() {
        String wh = "io.github.minecraftchampions.dodoopenjava.event.webhook.EventTrigger";
        String ws = "io.github.minecraftchampions.dodoopenjava.event.websocket.EventTrigger";
        boolean isWh = false;
        boolean isWs = false;
        if (ClassUtil.classExists(wh)) {
            isWh = true;
        }
        if (ClassUtil.classExists(ws)) {
            isWs = true;
        }
        try {
            if (isWh && isWs) {
                System.out.println("检测到同时拥有 WebSocket 和 WebHook库，默认选择使用WebSocket库");
                return Class.forName(ws);
            } else if (isWh) {
                return Class.forName(wh);
            } else if (isWs) {
                return Class.forName(ws);
            } else {
                throw new RuntimeException("没有使用 event-websocket or event-webhook 库");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化事件监听
     *
     * @param an Authorization
     */
    public static void init(String an) {
        Class<?> clazz = getEventTriggerClass();
        try {
            if (clazz == Class.forName("io.github.minecraftchampions.dodoopenjava.event.websocket.EventTrigger")) {
                getEventTriggerClass().getMethod("main", String.class).invoke(null, an);
            } else {
                getEventTriggerClass().getMethod("main").invoke(null);
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定的执行器注册至指定的事件类.
     * <p>
     * 原文:Registers the specified executor to the given event class
     *
     * @param event    要注册的事件类型
     * @param listener 要注册的监听器
     * @param priority 要注册的事件的优先级
     * @param executor 要注册的事件触发器
     * @param an       Authorization
     */
    public static void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull String an) {
        Validate.notNull(listener, "Listener cannot be null");
        Validate.notNull(priority, "Priority cannot be null");
        Validate.notNull(executor, "Executor cannot be null");
        Validate.notNull(an, "Authorization cannot be null");

        getEventListeners(event).register(new RegisteredListener(listener, executor, priority));
        if (!isInit) {
            isInit = true;

            init(an);
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
            throw new RuntimeException("系统异常,clazz.getSuperclass=null");
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
