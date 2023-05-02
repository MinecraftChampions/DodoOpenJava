package io.github.minecraftchampions.dodoopenjava.event;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.events.EventException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * �¼�����ط���������������ע��ȣ�
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
     * ע���¼�
     * @param listener ������
     * @param an Authorization
     */
    public static void registerEvents(@NotNull Listener listener,@NotNull String an) {
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : createRegisteredListeners(listener).entrySet()) {
            getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
        }
        if (!isInit) {
            isInit = true;
            init(an);
        }
    }

    /**
     * ͨ�������ȡʹ�������ߵ��ĸ���
     * @return ��
     */
    public static Class<?> getEventTriggerClass() {
        boolean webhook = false;
        boolean websocket = false;
        Class<?> wh = null;
        Class<?> ws = null;
        try {
            wh = Class.forName("io.github.minecraftchampions.dodoopenjava.event.webhook.EventTrigger");
            webhook = true;
        } catch (ClassNotFoundException ignored) {}
        try {
            ws = Class.forName("io.github.minecraftchampions.dodoopenjava.event.websocket.EventTrigger");
            websocket = true;
        } catch (ClassNotFoundException ignored) {}
        if (webhook && websocket) {
            System.out.println("��⵽ͬʱӵ�� WebSocket �� WebHook�⣬Ĭ��ѡ��ʹ��WebSocket��");
            return wh;
        } else if (webhook) {
            return wh;
        } else if (websocket) {
            return ws;
        } else {
            throw new RuntimeException("û��ʹ�� event-websocket or event-webhook ��");
        }
    }

    /**
     * ��ʼ���¼�����
     * @param an Authorization
     */
    public static void init(String an) {
        Class<?> clazz = getEventTriggerClass();
        try {
            if (clazz == Class.forName("io.github.minecraftchampions.dodoopenjava.event.websocket.EventTrigger")) {
                getEventTriggerClass().getMethod("main",String.class).invoke(null,an);
            } else {
                getEventTriggerClass().getMethod("main").invoke(null);
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ��ָ����ִ����ע����ָ�����¼���.
     * <p>
     * ԭ��:Registers the specified executor to the given event class
     *
     * @param event Ҫע����¼�����
     * @param listener Ҫע��ļ�����
     * @param priority Ҫע����¼������ȼ�
     * @param executor Ҫע���EventExecutor
     * @param an Authorization
     */
    public static void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor,@NotNull String an) {
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
