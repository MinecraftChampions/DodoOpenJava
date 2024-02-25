package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle.ChannelArticleCommentEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle.ChannelArticlePublishEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.*;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice.ChannelVoiceMemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice.ChannelVoiceMemberLeaveEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.gift.GiftSendEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.integral.IntegralChangeEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberLeaveEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.personal.PersonalMessageEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.shop.GoodsPurchaseEvent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

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
 *
 * @author qscbm187531
 */
@Slf4j
public class EventManager {
    private final Map<Class<? extends AbstractEvent>, List<SimpleEntry<Method, Object>>> handlers = new ConcurrentHashMap<>();

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
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) {
                continue;
            }
            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length > 1) {
                continue;
            }
            Class<?> checkClass = parameters[0];
            if (!AbstractEvent.class.isAssignableFrom(checkClass)) {
                continue;
            }
            final Class<? extends AbstractEvent> eventClass = checkClass.asSubclass(AbstractEvent.class);
            method.setAccessible(true);
            if (!handlers.containsKey(eventClass)) {
                handlers.put(eventClass, new ArrayList<>());
            }
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
            Set<Class<? extends AbstractEvent>> set = handlers.keySet();
            for (Class<? extends AbstractEvent> clazz : set) {
                List<SimpleEntry<Method, Object>> list = handlers.get(clazz).stream().
                        filter(e -> e.getKey().getDeclaringClass() == listener.getClass()).toList();
                handlers.get(clazz).removeAll(list);
            }
        }
    }

    /**
     * 解析并触发事件
     *
     * @param jsonObject jsonObject
     */
    public void parseAndFireEvent(@NonNull JSONObject jsonObject) {
        CompletableFuture.runAsync(() -> {
            switch (jsonObject.getJSONObject("data").getString("eventType")) {
                case "1001" -> fireEvent(new PersonalMessageEvent(jsonObject));
                case "2001" -> fireEvent(new MessageEvent(jsonObject));
                case "3001" -> fireEvent(new MessageReactionEvent(jsonObject));
                case "3002" -> fireEvent(new CardMessageButtonClickEvent(jsonObject));
                case "3003" -> fireEvent(new CardMessageFormSubmitEvent(jsonObject));
                case "3004" -> fireEvent(new CardMessageListSubmitEvent(jsonObject));
                case "4001" -> fireEvent(new MemberJoinEvent(jsonObject));
                case "4002" -> fireEvent(new MemberLeaveEvent(jsonObject));
                case "5001" -> fireEvent(new ChannelVoiceMemberJoinEvent(jsonObject));
                case "5002" -> fireEvent(new ChannelVoiceMemberLeaveEvent(jsonObject));
                case "6001" -> fireEvent(new ChannelArticlePublishEvent(jsonObject));
                case "6002" -> fireEvent(new ChannelArticleCommentEvent(jsonObject));
                case "7001" -> fireEvent(new GiftSendEvent(jsonObject));
                case "8001" -> fireEvent(new IntegralChangeEvent(jsonObject));
                case "9001" -> fireEvent(new GoodsPurchaseEvent(jsonObject));
                default -> log.warn("未知的事件！");
            }
        });
    }

    /**
     * 触发事件
     *
     * @param event 事件
     * @throws RuntimeException 当事件的 EventType 为 null 或 isEmpty 时抛出
     */
    public void fireEvent(@NonNull AbstractEvent event) throws RuntimeException {
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
    public static void fireEvent(@NonNull AbstractEvent event, @NonNull Map<Class<? extends AbstractEvent>, List<SimpleEntry<Method, Object>>> handlers) {
        boolean isAsync = event.isAsynchronous();
        if (!handlers.containsKey(event.eventType)) {
            return;
        }
        List<SimpleEntry<Method, Object>> methodEntryList = handlers.get(event.eventType);
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